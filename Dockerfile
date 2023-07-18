FROM openjdk:11-jdk-slim

WORKDIR /src
COPY . /src

RUN apt-get update
RUN apt-get install -y dos2unix
RUN dos2unix gradlew

RUN bash gradlew fatJar

WORKDIR /run
RUN cp /src/build/libs/cosmo-backend.jar /run/cosmo-backend.jar

EXPOSE 8080

CMD java -jar /run/cosmo-backend.jar