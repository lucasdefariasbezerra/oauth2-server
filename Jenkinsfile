pipeline {
    //agent { docker { image 'maven:3.3.3' } }
    agent any
    triggers {
       githubPush()
    }

    stages {
        stage('build/deployment') {
            steps {
               sh 'echo testing deployment'
               sh 'pwd'
               sshagent(['1db8cc9b-65c6-4edb-93fb-67125fcdf43f']) {
                  sh 'ssh -o StrictHostKeyChecking=no ec2-user@3.134.87.44 odds.sh && exit'
               }
            }
        }
    }
}