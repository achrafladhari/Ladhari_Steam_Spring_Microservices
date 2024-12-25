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
           // when { changeset "config-server/*"}
            steps {
                dir('config-server') {
                    script {
                        dockerImageConfigServer = docker.build("${IMAGE_NAME_CONFIG_SERVER}")
                    }
                }
            }
        }
        stage('Build Discovery Service Image') {
           // when { changeset "discovery-service/*"}
            steps {
                dir('discovery-service') {
                    script {
                        dockerImageDiscoveryService = docker.build("${IMAGE_NAME_DISCOVERY_SERVICE}")
                    }
                }
            }
        }
        stage('Test Gateway Image') {
            //when { changeset "gateway/*"}
            steps {
                dir('gateway') {
                    script{
                        sh '''mvn clean verify sonar:sonar \
                                -Dsonar.projectKey=gateway \
                                -Dsonar.projectName='gateway' \
                                -Dsonar.host.url=http://sonarqube:9000 \
                                -Dsonar.token=sqp_5f02e6acce83a46036b3a1a051bc486ec5087ba7 \
                            '''
                    }
                }
            }
        }
       /* stage('Build Server Image') {
            when { changeset "server/*"}
            steps {
                dir('server') {
                    script {
                        dockerImageServer = docker.build("${IMAGE_NAME_SERVER}")
                    }
                }
            }
        }

        stage('Build Client Image') {
            when { changeset "client/*"}
            steps {
                dir('client') {
                    script {
                        dockerImageClient = docker.build("${IMAGE_NAME_CLIENT}")
                    }
                }
            }
        }

        stage('Scan Server Image') {
            when { changeset "server/*"}
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                    -e TRIVY_DB_REPO=ghcr.io/aquasecurity/trivy-db \\
                    aquasec/trivy:latest image --exit-code 0 --severity LOW,MEDIUM,HIGH,CRITICAL \\
                    ${IMAGE_NAME_SERVER}
                    """
                }
            }
        }

        stage('Scan Client Image') {
            when { changeset "client/*"}
            steps {
                script {
                    sh """
                    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \\
                    -e TRIVY_DB_REPO=ghcr.io/aquasecurity/trivy-db \\
                    aquasec/trivy:latest image --exit-code 0 --severity LOW,MEDIUM,HIGH,CRITICAL \\
                    ${IMAGE_NAME_CLIENT}
                    """
                }
            }
        }

        stage('Push Server Image to Docker Hub') {
            when { changeset "server/*"}
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        dockerImageServer.push()
                    }
                }
            }
        }
        stage('Push Client Image to Docker Hub') {
            when { changeset "client/*"}
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        dockerImageClient.push()
                    }
                }
            }
        }*/

    }
    /*post {
        always {
            script {
                echo 'Cleanup phase!'
                if (sh(script: "docker images -q aquasec/trivy", returnStdout: true).trim()) {
                    sh 'docker rmi aquasec/trivy'
                }
                if (sh(script: "docker images -q ${IMAGE_NAME_SERVER}", returnStdout: true).trim()) {
                    sh "docker rmi ${IMAGE_NAME_SERVER}"
                }
                if (sh(script: "docker images -q ${IMAGE_NAME_CLIENT}", returnStdout: true).trim()) {
                    sh "docker rmi ${IMAGE_NAME_CLIENT}"
                }
                echo 'Cleanup Succefully done!'
            }
        }
    }*/
}