FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY src ./src
RUN javac -encoding UTF-8 -d out $(find src -name "*.java")

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/out ./out
ENV LANG=C.UTF-8
CMD ["java", "-Dstdout.encoding=UTF-8", "-p", "out", "-m", "lpoo_c2.a1/lpoo_c2.a1.Main"]
