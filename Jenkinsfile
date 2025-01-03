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
        IMAGE_NAME_FRONTEND = 'chrayef/client'
        BUILD_ID = "${env.BUILD_ID}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'git@github.com:achrafladhari/Ladhari_Steam_Spring_Microservices.git',
                    credentialsId: 'github'
            }
        }

        // Build Docker Images
        stage('Build Docker Images') {
            parallel {
                stage('Build Config Server Image') {
                    steps {
                        dir('config-server') {
                            script {
                                dockerImageConfigServer = docker.build("${IMAGE_NAME_CONFIG_SERVER}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Discovery Service Image') {
                    steps {
                        dir('discovery-service') {
                            script {
                                dockerImageDiscoveryService = docker.build("${IMAGE_NAME_DISCOVERY_SERVICE}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Gateway Image') {
                    steps {
                        dir('gateway') {
                            script {
                                dockerImageGateway = docker.build("${IMAGE_NAME_GATEWAY}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build User Service Image') {
                    steps {
                        dir('user-service') {
                            script {
                                dockerImageUserService = docker.build("${IMAGE_NAME_USER_SERVICE}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Games Service Image') {
                    steps {
                        dir('games-service') {
                            script {
                                dockerImageGamesService = docker.build("${IMAGE_NAME_GAMES_SERVICE}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Order Service Image') {
                    steps {
                        dir('order-service') {
                            script {
                                dockerImageOrderService = docker.build("${IMAGE_NAME_ORDER_SERVICE}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Library Service Image') {
                    steps {
                        dir('library-service') {
                            script {
                                dockerImageLibraryService = docker.build("${IMAGE_NAME_LIBRARY_SERVICE}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Payment Service Image') {
                    steps {
                        dir('payment-service') {
                            script {
                                dockerImagePaymentService = docker.build("${IMAGE_NAME_PAYMENT_SERVICE}:${BUILD_ID}")
                            }
                        }
                    }
                }
                stage('Build Frontend Image') {
                    steps {
                        dir('UI_Spring') {
                            script {
                                dockerImageFront = docker.build("${IMAGE_NAME_FRONTEND}:${BUILD_ID}")
                            }
                        }
                    }
                }
            }
        }

        // Trivy Scan Stages
        stage('Trivy Scan Docker Images') {
            parallel {
                stage('Scan Config Server Image') {
                    steps {
                        script {
                            sh """
                            docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
                            -v /cache/.trivy:/root/.cache/trivy \
                            -e TRIVY_DB_REPO=ghcr.io/aquasecurity/trivy-db \
                            aquasec/trivy:latest image --timeout 10m --exit-code 0 --update --scanners vuln --no-progress --severity LOW,MEDIUM,HIGH,CRITICAL \
                            ${IMAGE_NAME_CONFIG_SERVER}:${BUILD_ID}
                            """
                        }
                    }
                }
                stage('Scan Discovery Service Image') {
                    steps {
                        script {
                            sh """
                            docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
                            -v /cache/.trivy:/root/.cache/trivy \
                            -e TRIVY_DB_REPO=ghcr.io/aquasecurity/trivy-db \
                            aquasec/trivy:latest image --timeout 10m --exit-code 0 --update --scanners vuln --no-progress --severity LOW,MEDIUM,HIGH,CRITICAL \
                            ${IMAGE_NAME_DISCOVERY_SERVICE}:${BUILD_ID}
                            """
                        }
                    }
                }
                stage('Scan Gateway Image') {
                    steps {
                        script {
                            sh """
                            docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
                            -v /cache/.trivy:/root/.cache/trivy \
                            -e TRIVY_DB_REPO=ghcr.io/aquasecurity/trivy-db \
                            aquasec/trivy:latest image --timeout 10m --exit-code 0 --update --scanners vuln --no-progress --severity LOW,MEDIUM,HIGH,CRITICAL \
                            ${IMAGE_NAME_GATEWAY}:${BUILD_ID}
                            """
                        }
                    }
                }
                stage('Scan User Service Image') {
                    steps {
                        script {
                            sh """
                            docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
                            -v /cache/.trivy:/root/.cache/trivy \
                            -e TRIVY_DB_REPO=ghcr.io/aquasecurity/trivy-db \
                            aquasec/trivy:latest image --timeout 10m --exit-code 0 --update --scanners vuln --no-progress --severity LOW,MEDIUM,HIGH,CRITICAL \
                            ${IMAGE_NAME_USER_SERVICE}:${BUILD_ID}
                            """
                        }
                    }
                }
                // Add other services similarly...
            }
        }

        // Push Docker Images
        stage('Push Docker Images to Docker Hub') {
            parallel {
                stage('Push Config Server Image') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                                dockerImageConfigServer.push()
                            }
                        }
                    }
                }
                // Add push stages for other services similarly...
            }
        }
    }

    post {
        always {
            script {
                echo 'Cleanup phase!'
                def imagesToCleanup = [
                    'aquasec/trivy',
                    "${IMAGE_NAME_CONFIG_SERVER}",
                    "${IMAGE_NAME_DISCOVERY_SERVICE}",
                    "${IMAGE_NAME_GATEWAY}",
                    "${IMAGE_NAME_USER_SERVICE}",
                    "${IMAGE_NAME_GAMES_SERVICE}",
                    "${IMAGE_NAME_ORDER_SERVICE}",
                    "${IMAGE_NAME_LIBRARY_SERVICE}",
                    "${IMAGE_NAME_PAYMENT_SERVICE}",
                    "${IMAGE_NAME_FRONTEND}"
                ]
                imagesToCleanup.each { imageName ->
                    def imageIds = sh(script: "docker images --filter=reference='${imageName}:*' -q", returnStdout: true).trim()
                    if (imageIds) {
                        imageIds.split('\n').each { imageId ->
                            sh "docker rmi -f ${imageId}"
                        }
                    }
                }
                echo 'Cleanup Successfully done!'
            }
        }
    }
}
