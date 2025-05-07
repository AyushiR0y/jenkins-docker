pipeline {
    agent any

    tools {
        gradle 'Gradle 7+'  // Ensure this is correctly set in Jenkins global tool config
    }

    environment {
        ARTIFACTORY_CREDENTIALS = credentials('artifactory-credential')  // Correct spelling
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/AyushiR0y/jenkins.git'
            }
        }

        stage('Build Services') {
            steps {
                script {
                    sh 'chmod +x user-service/gradlew'
                    sh 'chmod +x order-service/gradlew'
                    def services = ['user-service', 'order-service']
                    for (service in services) {
                        sh "./${service}/gradlew -p ${service} clean build"
                    }
                }
            }
        }

        stage('Publish to Artifactory') {
            steps {
                script {
                    def services = ['user-service', 'order-service']
                    for (service in services) {
                        sh """
                        ./${service}/gradlew -p ${service} publish \\
                        -Partifactory_user="$ARTIFACTORY_CREDENTIALS_USR" \\
                        -Partifactory_password="$ARTIFACTORY_CREDENTIALS_PSW"
                        """
                    }
                }
            }
        }
    }
}
