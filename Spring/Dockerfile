FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD /var/lib/jenkins/res/tpachat.jar tpachat.jar
ENTRYPOINT ["java","-jar","/tpachat.jar"]
