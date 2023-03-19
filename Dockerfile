FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/exam-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-Duser.timezone=Asia/Tokyo","-jar","/app.war"]