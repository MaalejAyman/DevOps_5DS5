FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN curl -u admin:nexus -o tpachat.jar "http://172.10.0.140:8081/service/rest/v1/search/assets/download?sort=version&repository=nexus-repo-devops&maven.groupId=com.esprit.examen&maven.artifactId=tpAchatProject&maven.baseVersion=1.0&maven.extension=jar" -L
ADD target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0.jar"]
EXPOSE 8089
