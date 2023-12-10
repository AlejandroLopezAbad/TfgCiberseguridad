FROM gradle:7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:17
EXPOSE 8081:8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/TfgCiberseguridad-0.0.1-SNAPSHOT.jar /app/tfgciberseguridad.jar

ENTRYPOINT ["java","-jar","/app/tfgciberseguridad.jar"]







