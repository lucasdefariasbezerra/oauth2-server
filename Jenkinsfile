pipeline {
    agent { docker { image 'gradle:jdk11' } }
    //agent any
    stages {
        stage('build') {
            steps {
                sh 'echo hello world'
                sh 'gradle -v'
            }
        }
    }
}