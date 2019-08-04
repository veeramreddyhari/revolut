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
                     sh 'scp -o StrictHostKeyChecking=no target/*.war hari@3.88.232.149:/opt/apache-tomcat-9.0.22/webapps/'
                }
            }
        }
    }
}