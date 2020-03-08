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
    }
}