# stage 1
FROM node:10-alpine as node
RUN mkdir -p /app

WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build --prod

# stage 2
FROM nginx:1.17.1-alpine
COPY --from=node /app/dist/bloodstream-client /usr/share/nginx/html



