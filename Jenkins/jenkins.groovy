pipeline {
    agent any
    
    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir() // Clean the workspace
            }
        }
        
        stage('Checkout') {
            steps {
              script {
                    withCredentials([string(credentialsId: '', variable: 'PAT')]) {
                        git branch: 'main', credentialsId: '', url: 'https://github.com/Harishpandu43/Mental-health-assistance.git'
                    }
                }
            }
        }
      
        stage('Deploy to Development') {
            steps {
                script {
                    sh 'ssh user@development_ec2_instance "cd /path/to/project && ./start_application.sh"'
                }
            }
            post {
                always {
                    input 'Deploy to Testing?' // Wait for user input to proceed to testing deployment
                }
            }
        }
        
        stage('Deploy to Testing') {
            steps {
                script {
                    sh 'ssh user@testing_ec2_instance "cd /path/to/project && ./start_application.sh"'
                }
            }
            post {
                always {
                    input 'Deploy to Production?' // Wait for user input to proceed to production deployment
                }
            }
        }
        
        stage('Deploy to Production') {
            steps {
                script {
                    sh 'ssh user@production_ec2_instance "cd /path/to/project && ./start_application.sh"'
                }
            }
        }
    }
}
