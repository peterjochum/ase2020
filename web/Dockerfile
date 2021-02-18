###
# STAGE1 1: Codegen
###
FROM openjdk as swagger-codegen
WORKDIR /swagger/
# Install swagger codegen
RUN curl -o swagger-codegen-cli-2.4.17.jar https://repo1.maven.org/maven2/io/swagger/swagger-codegen-cli/2.4.17/swagger-codegen-cli-2.4.17.jar
COPY src/assets/swagger/* ./
# Generate the clients
RUN java -jar swagger-codegen-cli-2.4.17.jar generate -i swagger-steambuddy.json -o integration -c config.json -l typescript-angular

###
# STAGE 2: Build
###
FROM node:15-alpine AS build
WORKDIR /usr/src/app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy source
COPY src src

# Copy build/test support files
COPY angular.json karma.conf.js tsconfig.* ./

# Build the project
COPY --from=swagger-codegen /swagger/integration src/app/

# RUN npm run test
RUN npm run build -- --prod

###
# STAGE 3: Run
###
FROM nginx:mainline-alpine
#COPY nginx.conf /etc/nginx/nginx.conf
COPY docker-entrypoint.d /docker-entrypoint.d
COPY --from=build /usr/src/app/dist/web /usr/share/nginx/html
