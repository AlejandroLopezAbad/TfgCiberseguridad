FROM gradle:7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildJar --no-daemon

FROM openjdk:17
EXPOSE 8081:8081
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/tfgciberseguridad.jar /app/tfgciberseguridad.jar
ENTRYPOINT ["java","-jar","/app/tfgciberseguridad.jar"]