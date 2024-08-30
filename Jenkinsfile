pipeline {
    agent any

    stages {
        stage('Sanity Check') {
            steps {
                echo 'Hello World'
                bat 'ping  96.84.175.78 -n 5 '
            }
             
        }
        stage('Run Tests'){
            steps {
                echo 'Hello in Run Tests Stage '
                git branch: 'main', url: 'https://github.com/sudheer51/pluto_mmp.git' 
                dir('pluto') {
                         bat 'mvn clean test'
                    }
            }
        }
        
    }
}
