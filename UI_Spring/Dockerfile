#Build
FROM node:18.19.1-alpine AS build
WORKDIR /app
RUN npm install -g @angular/cli
COPY package.json package-lock.json ./
RUN npm install --force
COPY . .
RUN ng build --configuration=production
#Prod
FROM nginx:stable-alpine AS prod
COPY --from=build /app/dist/ui-spring/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
