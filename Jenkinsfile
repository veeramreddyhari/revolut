pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                    sh 'mvn clean compile'
                }
        }

        stage ('Testing Stage') {

            steps {
                    sh 'mvn test'
                }
        }

        stage ('Building Stage') {
            steps {
                    sh 'mvn install'
                }
        }

        stage ('Deployment:Tomcat') {
            steps {
                sshagent(['tomcat-dev']) {
                     sh 'scp -o StrictHostKeyChecking=no target/*.war hari@172.31.95.241:/opt/apache-tomcat-9.0.22/webapps/'
                }
            }
        }
    }
}