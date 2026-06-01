FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY src ./src
RUN find src -name "*.java" > sources.txt && javac -d out @sources.txt
CMD ["java", "-cp", "out", "MainDemo"]
