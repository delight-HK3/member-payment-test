# 베이스 이미지 설정
FROM eclipse-temurin:17 AS jre-build

# Gradle Wrapper 파일 복사 및 실행 권한 부여
COPY ./gradlew .
COPY ./gradle gradle
COPY ./build.gradle . 
COPY ./settings.gradle .
COPY ./src src

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# 베이스 이미지 설정
FROM eclipse-temurin:17

# 파일 복사
COPY --from=jre-build build/libs/*.jar point.jar

# 포트 설정
EXPOSE 8090

# 커맨드 실행
ENTRYPOINT ["java","-jar", "/point.jar"]