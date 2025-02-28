#----------------------------------
# Stage 1
#----------------------------------

# Import docker image with maven installed
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

# ตั้งค่า working directory ใน container
WORKDIR /app

# คัดลอกไฟล์ pom.xml และดาวน์โหลด dependencies
COPY . /app

# Build application and skip test cases
#RUN --mount=type=cache,target=/root/.m2,rw  mvn clean install -DskipTests=true
RUN --mount=type=cache,target=/root/.m2,rw  mvn clean install  -DskipTests=true

#--------------------------------------
# Stage 2
#--------------------------------------

# Import small size java image
FROM eclipse-temurin:21.0.6_7-jre-alpine-3.21 AS deployer

# Copy build from stage 1 (builder)
COPY --from=builder /app/target/*.jar /app/target/demo-api.jar

# เปิดพอร์ตที่แอปพลิเคชันจะใช้
EXPOSE 8080

# Start the application with JVM options
ENTRYPOINT ["java", "-jar", "/app/target/demo-api.jar", "-Djava.security.egd=file:/dev/./urandom"]
