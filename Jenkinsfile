pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "172.10.0.140:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "Nexus-Creds"
    }
    stages {
        stage('Start') {
            steps {
                echo 'Start Deploy'
            script {
                    mvn deploy;
                }
            }
        }
    }
}
