FROM openjdk:8-jre-alpine
RUN mkdir /app
COPY cosmo-backend.jar /app/cosmo-backend.jar
ENV LOOP_REPO_PATH /data/
WORKDIR /app
CMD ["java", \
     "-server", \
     "-XX:MaxGCPauseMillis=100", \
     "-XX:+UseStringDeduplication", \
     "-jar", \
     "cosmo-backend.jar"]