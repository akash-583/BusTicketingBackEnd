pipeline {
    agent any

    environment {
        DEPLOY_DIR = "${WORKSPACE}/deployments"
    }

    stages {
        stage('Prepare Deployment Directory') {
            steps {
                script {
                    sh "mkdir -p ${DEPLOY_DIR}"
                }
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
