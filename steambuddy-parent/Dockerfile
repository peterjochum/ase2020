# syntax = docker/dockerfile:1.0-experimental

###
## Build
###
FROM maven:3-openjdk-11 as build
WORKDIR /build

# Copy rest of the project and build
COPY . .
# ! The maven cache during build requires DOCKER_BUILDKIT=1 during docker build
RUN --mount=type=cache,target=/root/.m2 mvn -B package --file pom.xml
RUN find

####
## Run
####
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /build/steambuddy-app/target/steambuddy-app-*.jar steambuddy.jar
CMD ["java","-jar", "-Dspring.profiles.active=prod", "steambuddy.jar"]