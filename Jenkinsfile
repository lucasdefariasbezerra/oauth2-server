pipeline {
    //agent { docker { image 'maven:3.3.3' } }
    agent any
    triggers {
        githubPush() // Enabling being build on Push
      }
    stages {
        stage('build') {
            steps {
                sh 'echo hello world'
                sh 'gradle -v'
            }
        }
    }
}