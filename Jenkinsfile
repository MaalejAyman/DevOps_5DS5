pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'building the application...'
            }
        }
     
      stage('Test') {
            steps {
                echo 'testing the application...'
            }
        }
        
        stage('deploy') {
            steps {
                echo 'deploying the application...'
            }
        }
        
           stage('GIT') {
            steps {
                echo 'Getting project from git' ;
                git "https://github.com/mouhebba/TP-Achat.git"
            }
        }
           stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
              
            }
        }
        
              stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
              
            }
        }
        
             stage("Maven Sonarqube") {
            steps {
                script {
                    sh "mvn -f'Spring/pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                }
            }
        }
    }
}
