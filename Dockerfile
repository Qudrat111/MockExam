FROM node:25.0.0-bookworm

RUN apt-get update -y  && \
    apt-get install -y software-properties-common

# Create a non-root user
RUN adduser --disabled-password --gecos '' appuser
USER appuser


RUN mkdir /home/user/app
WORKDIR /home/user/app
COPY package.json /home/user/app/
RUN npm install express --save


COPY . .
EXPOSE 3000
ENTRYPOINT [ "node", "app.js"]
