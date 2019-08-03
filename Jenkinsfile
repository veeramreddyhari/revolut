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

        stage ('Deployment To Tomcat') {
            steps {
                sshagent(['tomcat-dev']) {
                     sh 'scp ssh -o StrictHostKeyChecking=no target/*.war ec2-user@172.31.95.241:/opt/apache-tomcat-9.0.22/webapps/'
                }
            }
        }
    }
}