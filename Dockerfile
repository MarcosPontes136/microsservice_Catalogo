FROM openjdk:23
ADD ./docker-spring-boot.jar docker-spring-boot.jar
EXPOSE 8081
ENTRYPOINT [“java”, “-jar”, “docker-spring-boot.jar”]