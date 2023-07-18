FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11

EXPOSE 8080:8080

RUN mkdir /app
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/cosmo-backend.jar /app/cosmo-backend.jar
ENTRYPOINT ["java","-jar","/app/cosmo-backend.jar"]