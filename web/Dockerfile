###
# STAGE 1: Build
###
FROM node:15-alpine AS build
WORKDIR /usr/src/app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy rest of the project
COPY src src
COPY angular.json tsconfig.* ./

# Build the project
RUN npm run build -- --prod

###
# STAGE 2: Run
###
FROM nginx:mainline-alpine
#COPY nginx.conf /etc/nginx/nginx.conf
COPY docker-entrypoint.d /docker-entrypoint.d
COPY --from=build /usr/src/app/dist/web /usr/share/nginx/html
