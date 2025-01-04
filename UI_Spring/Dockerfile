#Build
FROM node:18.19.1-alpine as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install --force
COPY . .
EXPOSE 4200
CMD ["ng", "s"]

#Prod
#RUN npm run build --prod
#FROM nginx:stable-alpine as prod
#COPY --from=build /app/dist/ui-spring /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/nginx.conf
#EXPOSE 80
#CMD ["nginx", "-g", "daemon off;"]
