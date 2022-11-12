pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.10.0.140:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "Nexus-Creds"
        DOCKER_CREDENTIAL_ID = "Docker-Creds"
        VERSION= "1.${env.BUILD_NUMBER}"
    }
    stages {
        
        stage("Maven Clean") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' clean -DskipTests=true -Drevision=${VERSION}"
                }
            }
        }
        stage("Maven Compile") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' compile -DskipTests=true -Drevision=${VERSION}"
                }
            }
        }
        stage("Maven test") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' test -Drevision=${VERSION}"
                }
            }
        }
        stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=Admin -Drevision=${VERSION}"
                }
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' package -DskipTests=true -Drevision=${VERSION}"
                }
                echo ":$BUILD_NUMBER"
            }
        }
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "Spring/pom.xml";
                    filesByGlob = findFiles(glob: "Spring/target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${VERSION}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: VERSION,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "Spring/pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
        stage('Login to DockerHub') {
                    steps{
                        dir('Spring'){
                            withCredentials([usernamePassword(credentialsId: 'Docker-Creds', passwordVariable: 'dockerKey', usernameVariable: 'dockerUser')]){
                                sh 'docker login -u dockerUser -p dockerKey'
                            }
                        }
                    }
        }
    }
}
