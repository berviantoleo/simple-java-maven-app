# Steps

## Jenkins

1. Setup Network: `docker network create jenkins`
2. Run Jenkins.

  * Linux

    ```sh
    docker run \
      --name jenkins-docker \
      --rm \
      --detach \
      --privileged \
      --network jenkins \
      --network-alias docker \
      --env DOCKER_TLS_CERTDIR=/certs \
      --volume jenkins-docker-certs:/certs/client \
      --volume jenkins-data:/var/jenkins_home \
      --publish 2376:2376 \
      --publish 3000:3000 \
      docker:dind \
      --storage-driver overlay2
    ```

  * Windows

    ```sh
    docker run --name jenkins-docker --detach ^
      --privileged --network jenkins --network-alias docker ^
      --env DOCKER_TLS_CERTDIR=/certs ^
      --volume jenkins-docker-certs:/certs/client ^
      --volume jenkins-data:/var/jenkins_home ^
      --publish 3000:3000 --publish 2376:2376 ^
      docker:dind
     ```

3. Build image : `docker build -t myjenkins-blueocean:2.346.1-1 .`

4. Run jenkins.

  * Linux

    ```sh
    docker run \
      --name jenkins-blueocean \
      --detach \
      --network jenkins \
      --env DOCKER_HOST=tcp://docker:2376 \
      --env DOCKER_CERT_PATH=/certs/client \
      --env DOCKER_TLS_VERIFY=1 \
      --publish 49000:49000 \
      --publish 50000:50000 \
      --volume jenkins-data:/var/jenkins_home \
      --volume jenkins-docker-certs:/certs/client:ro \
      --volume "$HOME":/home \
      --restart=on-failure \
      --env JAVA_OPTS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true" \
      myjenkins-blueocean:2.346.1-1
    ```

  * Windows

    ```sh
    docker run --name jenkins-blueocean --detach ^
      --network jenkins --env DOCKER_HOST=tcp://docker:2376 ^
      --env DOCKER_CERT_PATH=/certs/client --env DOCKER_TLS_VERIFY=1 ^
      --volume jenkins-data:/var/jenkins_home ^
      --volume jenkins-docker-certs:/certs/client:ro ^
      --volume "%HOMEDRIVE%%HOMEPATH%":/home ^
      --restart=on-failure ^
      --env JAVA_OPTS="-Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true" ^
      --publish 49000:49000 --publish 50000:50000 myjenkins-blueocean:2.346.1-1
    ```

## NGINX

* Use `nginx.txt` for the configuration.
