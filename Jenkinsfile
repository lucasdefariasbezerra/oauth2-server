pipeline {
    //agent { docker { image 'maven:3.3.3' } }
    agent any
    triggers {
       githubPush()
    }

    stages {
        stage('build') {
          steps {
             sshagent(['1db8cc9b-65c6-4edb-93fb-67125fcdf43f']) {
                sh 'ssh -o StrictHostKeyChecking=no ec2-user@3.134.87.44 odds.sh BUILD'
             }
          }
        }

        stage('deployment') {
            steps {
               sshagent(['1db8cc9b-65c6-4edb-93fb-67125fcdf43f']) {
                  sh 'ssh -o StrictHostKeyChecking=no ec2-user@3.134.87.44 odds.sh DEPLOY'
               }
            }
        }
    }
}