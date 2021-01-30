###
# STAGE 1: Build
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
# TODO: test during build (requires Chrome Headless)
# RUN npm run test
RUN npm run build -- --prod

###
# STAGE 2: Run
###
FROM nginx:mainline-alpine
#COPY nginx.conf /etc/nginx/nginx.conf
COPY docker-entrypoint.d /docker-entrypoint.d
COPY --from=build /usr/src/app/dist/web /usr/share/nginx/html
