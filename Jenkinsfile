pipeline {
    //agent { docker { image 'maven:3.3.3' } }
    agent any
    triggers {
       githubPush()
    }

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
                  sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/oauth-build-pipeline_master/build/libs/tokenserver-0.0.1-SNAPSHOT.jar ec2-user@3.20.232.211:/odds/odds-jar'
                  sh 'ssh -o StrictHostKeyChecking=no ec2-user@3.20.232.211 pwd'
               }
            }
        }
    }
}