# 베이스 이미지 설정
FROM openjdk:17-jre-slim AS builder

# 작업 디렉토리 설정
WORKDIR /pointproject

# Gradle Wrapper 파일 복사 및 실행 권한 부여
COPY gradlew gradlew.bat .
COPY gradle gradle
RUN chmod +x gradlew gradlew.bat

RUN ./gradlew clean build -x test

# 복사할 파일 위치 설정
ARG JAR_FILE=/build/libs/point.jar

# 베이스 이미지 설정
FROM openjdk:17-jre-slim

# 작업 디렉토리 설정
WORKDIR /pointproject

# 파일 복사
COPY --from=builder ${JAR_FILE} /point.jar

# 포트 설정
EXPOSE 8090

# 커맨드 실행
ENTRYPOINT ["java","-jar", "/point.jar"]
