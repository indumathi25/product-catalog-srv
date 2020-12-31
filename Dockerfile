FROM openjdk:11
ADD target/product-catalog.jar product-catalog.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "product-catalog.jar"]