pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Rohith5547/web-application-testing.git'
            }
        }

        stage('Validate Nginx Config') {
            steps {
                sh 'sudo /usr/sbin/nginx -t -c $WORKSPACE/nginx/web-tier.conf'
            }
        }

        stage('Deploy Config') {
            steps {
                sh '''
                    sudo cp $WORKSPACE/nginx/web-tier.conf /etc/nginx/sites-available/web-tier
                    sudo ln -sf /etc/nginx/sites-available/web-tier /etc/nginx/sites-enabled/web-tier
                '''
            }
        }

        stage('Reload Nginx') {
            steps {
                sh 'sudo /usr/sbin/systemctl reload nginx'
            }
        }
    }
}

