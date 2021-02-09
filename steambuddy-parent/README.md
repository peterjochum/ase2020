# Backend

The project uses Spring 2.1.4 .

## Development

Currently there are two Maven Profiles: Dev and Prod

For development please use the dev profile (add this parameter to your JVM arguments
"-Dspring.profiles.active=dev"). So it will load the right propertiers.

You will need a MySQL Database Server 5.7, for easier use please use Docker
(https://hub.docker.com/_/mysql).



## Building

### Building the docker image

The prod Profile build the application for the Docker-Image. A Dockerfile for
building the container as well as a docker-compose.yml is provided to start
whole backend application virtualized.

```shell
cd steambuddy-parent
DOCKER_BUILDKIT=1 docker build -t steambuddyapp:local .
```

### Building & running using docker-compose

```shell
COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker-compose build
docker-compose up
```


