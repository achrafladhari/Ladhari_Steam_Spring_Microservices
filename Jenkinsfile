pipeline {
    agent any
    tools {
        maven 'maven-399'
    }
    triggers {
        pollSCM('* * * * *')
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        IMAGE_NAME_CONFIG_SERVER = 'chrayef/config-server'
        IMAGE_NAME_DISCOVERY_SERVICE = 'chrayef/discovery-service'
        IMAGE_NAME_GATEWAY = 'chrayef/gateway'
        IMAGE_NAME_USER_SERVICE = 'chrayef/user-service'
        IMAGE_NAME_GAMES_SERVICE = 'chrayef/games-service'
        IMAGE_NAME_ORDER_SERVICE = 'chrayef/order-service'
        IMAGE_NAME_LIBRARY_SERVICE = 'chrayef/library-service'
        IMAGE_NAME_PAYMENT_SERVICE = 'chrayef/payment-service'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'git@github.com:achrafladhari/Ladhari_Steam_Spring_Microservices.git',
                    credentialsId: 'github'
            }
        }
        stage('Build Config Server Image') {
            steps {
                dir('config-server') {
                    script {
                        dockerImageConfigServer = docker.build("${IMAGE_NAME_CONFIG_SERVER}")
                    }
                }
            }
        }
        /*stage('Run Config Server') {
            steps {
                script {
                    sh '''
                        docker run -d \
                            --name config-server \
                            -p 8888:8888 \
                            -v config-server:/app \
                            --health-cmd="nc -z localhost 8888 || exit 1" \
                            --health-interval=30s \
                            --health-retries=3 \
                            --health-start-period=10s \
                            --health-timeout=10s \
                            ${IMAGE_NAME_CONFIG_SERVER}
                    '''
                }
            }
        }*/
        stage('Build Discovery Service Image') {
            steps {
                dir('discovery-service') {
                    script {
                        dockerImageDiscoveryService = docker.build("${IMAGE_NAME_DISCOVERY_SERVICE}")
                    }
                }
            }
        }
        /*stage('Run Discovery Service') {
            steps {
                                            withEnv([
                                'EUREKA_HOSTNAME_DISCOVERY=discovery',
                            ]) {
                script {
                    sh '''
                        docker run -d \
                            --name discovery-service \
                            -p 8761:8761 \
                            -v discovery-service:/app \
                            -e EUREKA_HOSTNAME_DISCOVERY=discovery-service \
                            -e CONFIG_SERVER_URL=optional:configserver:http://config-server:8888 \
                            --health-cmd="nc -z localhost 8761 || exit 1" \
                            --health-interval=30s \
                            --health-retries=3 \
                            --health-start-period=10s \
                            --health-timeout=10s \
                            --link config-server \
                            ${IMAGE_NAME_DISCOVERY_SERVICE}
                    '''
                }
            }
        }
        }*/
        stage('Test Gateway Image') {
            steps {
                dir('gateway') {
                    withEnv([
                        'EUREKA_HOSTNAME_GATEWAY=gateway',
                        'EUREKA_DEFAULT_ZONE=http://discovery-service:8761/eureka'
                    ]) {
                        script {
                            sh '''
                                mvn clean test verify sonar:sonar \
                                    -Dsonar.projectKey=gateway \
                                    -Dsonar.projectName='gateway' \
                                    -Dsonar.host.url=http://sonarqube:9000 \
                                    -Dsonar.token=sqp_5f02e6acce83a46036b3a1a051bc486ec5087ba7
                            '''
                        }
                    }
                }
            }
        }
                stage('Test Library Service Image') {
                    steps {
                        dir('library-service') {
                            withEnv([
                                'EUREKA_HOSTNAME_LIBRARY=library',
                                'EUREKA_DEFAULT_ZONE=http://discovery-service:8761/eureka'
                            ]) {
                                script {
                                    sh '''
                                        mvn clean test verify sonar:sonar \
                                            -Dsonar.projectKey=library \
                                            -Dsonar.projectName='library' \
                                            -Dsonar.host.url=http://sonarqube:9000 \
                                            -Dsonar.token=sqp_4e0bf7b4fd2550c0e7611f48ba31c9f2285b7c70
                                    '''
                                }
                            }
                        }
                    }
                }
    }
}
