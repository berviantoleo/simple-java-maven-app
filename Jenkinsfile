node {
  checkout scm
  docker.image('maven:3-alpine').inside {
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
    stage('Deliver') {
      sh './jenkins/scripts/deliver.sh'
    }
  }

}
