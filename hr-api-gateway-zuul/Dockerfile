FROM maven:3.9.9-amazoncorretto-11-alpine AS builder
WORKDIR /usr/builder
COPY . .
RUN mvn clean package -DskipTests


FROM amazoncorretto:11.0.26-al2023-headless AS runner
WORKDIR /app
COPY --from=builder /usr/builder/target/*.jar ./app.jar
RUN curl https://raw.githubusercontent.com/vishnubob/wait-for-it/refs/heads/master/wait-for-it.sh > wait-for-it.sh
RUN chmod +x wait-for-it.sh
EXPOSE 8765
CMD ["java", "-jar", "app.jar"]