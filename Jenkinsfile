node {
  checkout scm
  docker.image('maven:3.8-openjdk-11-slim').inside {
    stage('Build') {
      sh 'mvn --version'
      sh 'mvn -B -DskipTests clean package'
    }
    stage('Test') {
      try {
        sh 'mvn test'
      } finally {
        junit 'target/surefire-reports/*.xml'
      }
    }
    stage('Manual Approval') {
      input message: 'Lanjutkan ke tahap Deploy?'
    }
    stage('Deploy') {
      sh 'chmod +x ./jenkins/scripts/deliver.sh && ./jenkins/scripts/deliver.sh'
      sh 'sleep 60'
      sh 'chmod +x ./jenkins/scripts/kill.sh && ./jenkins/scripts/kill.sh'
    }
  }

}
