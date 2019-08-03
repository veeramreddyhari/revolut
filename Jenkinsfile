pipeline {
    agent any

    stages {
        stage ('Compile Stage') {
            git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'
            steps {
                withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn test'
                }
            }
        }

        stage ('Building Stage') {
            steps {
                withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn install'
                }
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