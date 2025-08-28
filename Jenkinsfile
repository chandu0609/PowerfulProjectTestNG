pipeline {
    agent any //{ label 'windows' } // or just "any" if no windows agent configured

    environment {
        BASE_URL      = 'https://dev.onion.gnapitech.org'
        CONTROLS_URL  = 'https://chandu0609.github.io/SeleniumAllControlsRepo/login.html'
        BROWSER       = 'chrome'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Set up JDK 21') {
            steps {
                echo "Setting up JDK 21"
                // Jenkins must have JDK installed or use a tool config
                tool name: 'jdk21', type: 'jdk'
            }
        }

        stage('Build with Maven TestNG (headless)') {
            steps {
                echo "Running Maven tests"
                bat """
                    mvn clean verify -Dheadless=true -DBaseurl=%BASE_URL% -DBrowser=%BROWSER% -Dthreadcount=3 \
                    -Dtestgroups="regression"
                """
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo "Generating Allure report"
                bat "mvn allure:report"
            }
        }

        stage('Archive Reports') {
            steps {
                echo "Archiving Extent and Allure reports"
                archiveArtifacts artifacts: 'reports/ExtentReport.html', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/allure-report/**', allowEmptyArchive: true
            }
        }
    }
        post {
            always {
                echo "Pipeline finished. Reports archived."
    
                // Publish Allure report
                allure([
                    includeProperties: false,
                    jdk: '',  // leave empty to use default
                    results: [[path: 'target/allure-results']]
                ])
                 // Publish Extent report as HTML tab (requires HTML Publisher plugin)
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report',
                    allowMissing: true,
                    keepAll: true
                ])
            }
    }
}


/*pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                echo "✅ Jenkinsfile detected correctly"
            }
        }
        stage('Build') {
            steps {
                echo "Running build..."
            }
        }
    }
}*/
