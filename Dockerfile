FROM maven:3.6.0-jdk-8-alpine

RUN mkdir /app
WORKDIR /app/
COPY . /app

CMD mvn test