FROM openjdk:11 as builder

COPY . .

RUN ./gradlew buildFatJar

FROM openjdk:11

COPY --from=builder /build/libs/cosmo-backend.jar ./cosmo-backend.jar

CMD ["java", "-jar", "cosmo-backend.jar"]