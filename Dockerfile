FROM openjdk:17-jdk-slim
COPY target/Quiz-Internship-0.0.1-SNAPSHOT.jar Quiz-Internship-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Quiz-Internship-0.0.1-SNAPSHOT.jar"]
