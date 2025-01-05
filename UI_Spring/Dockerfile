#Build
FROM node:18.19.1-alpine AS build
WORKDIR /app
RUN npm install -g @angular/cli
COPY package.json package-lock.json ./
RUN npm install --force
COPY . .
RUN ng build --configuration=production
#EXPOSE 4200
#CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "4200"]

#Prod
#RUN npm run build --prod
FROM nginx:stable-alpine AS prod
COPY --from=build /app/dist/ui-spring/browser /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
