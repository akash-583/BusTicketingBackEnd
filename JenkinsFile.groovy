pipeline {
    agent any
    
    tools {
        // Define the Maven tool installation name as configured in Jenkins Global Tool Configuration
        maven 'Maven_Installation_Name'
        // Define the Git tool installation name as configured in Jenkins Global Tool Configuration
        git 'Git_Installation_Name'
    }

    environment {
        DEPLOY_DIR = "${WORKSPACE}/deployments"
    }

    stages {
        stage('Checkout') {
            steps {
                cleanWs()
                // Checkout the Git repository
                checkout([$class: 'GitSCM', 
                    branches: [[name: 'master']], 
                    userRemoteConfigs: [[url: 'https://github.com/akash-583/BusTicketingBackEnd.git']]
                ])
            }
        }
        
        stage('Build and Test Microservices') {
            parallel {
                stage('apigateway') {
                    steps {
                        dir('apigateway') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/apigateway.jar"
                        }
                    }
                }
                stage('booking-service') {
                    steps {
                        dir('booking-service') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/booking-service.jar"
                        }
                    }
                }
                stage('busservice') {
                    steps {
                        dir('busservice') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/busservice.jar"
                        }
                    }
                }
                stage('customerissue') {
                    steps {
                        dir('customerissue') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/customerissue.jar"
                        }
                    }
                }
                stage('eurekaserver') {
                    steps {
                        dir('eurekaserver') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/eurekaserver.jar"
                        }
                    }
                }
                stage('loginservice') {
                    steps {
                        dir('loginservice') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/loginservice.jar"
                        }
                    }
                }
                stage('paymentservice') {
                    steps {
                        dir('paymentservice') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/paymentservice.jar"
                        }
                    }
                }
                stage('registration') {
                    steps {
                        dir('registration') {
                            sh 'mvn clean package'
                            sh 'mvn test'
                            sh "cp target/*.jar ${DEPLOY_DIR}/registration.jar"
                        }
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                // Example deployment step
                echo 'Deploying...'
                // Add your deployment script or commands here
            }
        }
    }

    post {
        success {
            echo 'Pipeline successfully executed!'
            archiveArtifacts artifacts: 'deployments/*.jar', allowEmptyArchive: true
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
