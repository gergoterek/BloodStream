# stage 1
FROM node:10-alpine as node
RUN mkdir -p /app

WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build --prod
#CMD ["npm", "run", "ng", "serve", "--host", "0.0.0.0"]

# stage 2
FROM nginx:1.18.0-alpine
COPY --from=node /app/dist/bloodstream-client  /usr/share/nginx/html

#COPY nginx.conf /etc/nginx/nginx.conf 
#CMD ["nginx", "-g", "daemon off;"]

