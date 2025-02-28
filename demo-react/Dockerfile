FROM node:23-alpine3.20 AS step1
WORKDIR /xyz
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.27.4-alpine
COPY --from=step1 /xyz/dist /usr/share/nginx/html