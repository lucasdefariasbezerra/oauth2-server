pipeline {
    //agent { docker { image 'maven:3.3.3' } }
    agent any
    triggers {
            githubPush()
    stages {
        stage('build') {
            steps {
                sh 'echo building jar file'
                sh './gradlew clean build -x test'
            }
        }

        stage('deployment') {
            steps {
               sh 'echo testing deployment'
               sh 'pwd'
               sshagent(['1db8cc9b-65c6-4edb-93fb-67125fcdf43f']) {
                  sh '''
                    ssh ec2-user@3.17.70.52 pwd
                  '''
               }
            }
        }
    }
}