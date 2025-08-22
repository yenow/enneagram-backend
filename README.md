# 에니어그램 Backend

이 프로젝트는 에니어그램 테스트 및 관련 기능을 제공하는 백엔드 서버입니다.

## ✨ 주요 기능

- 에니어그램 질문 및 답변 API

## 🛠️ 사용된 기술

- **Backend:** Java 17, Spring Boot 3.x
- **Database:** MariaDB
- **ORM:** Spring Data JPA, QueryDSL
- **Build Tool:** Gradle
- **Containerization:** Docker, Docker Compose
- **API Documentation:** Springdoc OpenAPI (Swagger)

## 🚀 시작하기

### 사전 요구사항

- JDK 17
- Docker 및 Docker Compose
- 실행 중인 MariaDB 인스턴스

### 1. 프로젝트 클론

```bash
git clone https://github.com/your-username/enneagram-backend.git
cd enneagram-backend
```

### 2. 데이터베이스 설정

`src/main/resources/application.yaml` 파일에서 본인의 MariaDB 환경에 맞게 데이터베이스 연결 정보를 수정합니다.

```yaml
spring:
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: url # 본인의 DB 주소로 변경
    username: username   # 본인의 DB 유저명으로 변경
    password: password  # 본인의 DB 비밀번호로 변경
```

### 3. 애플리케이션 실행

이 프로젝트를 실행하는 방법에는 두 가지가 있습니다.

#### 방법 1: Gradle 사용

터미널에서 다음 명령어를 실행합니다.

```bash
./gradlew bootRun
```

#### 방법 2: Docker Compose 사용

Docker가 설치되어 있고 실행 중인지 확인한 후, 프로젝트 루트 디렉토리에서 다음 명령어를 실행합니다. 이 방법은 로컬에 MariaDB가 실행 중이어야 합니다.

```bash
docker-compose up --build
```

애플리케이션이 시작되면 `http://localhost:8080` 에서 접속할 수 있습니다.

컨테이너를 중지하고 싶을 때는 다음 명령어를 사용합니다.

```bash
docker-compose down
```

## Docker 이미지 빌드 및 실행
도커 이미지 만들기
```bash
  docker build -t enneagram-backend-local:latest .
 ```
도커 이미지 리스트
```bash
   docker images enneagram-backend-local
 ```
도커로 이미지 실행
```bash
   # Backend
   docker run -d --rm -p 8080:8080 --name enneagram-backend-local -e SPRING_PROFILES_ACTIVE=docker enneagram-backend-local:latest
   # LLM
   docker run -d --rm -p 8000:8000 --name enneagram-llm-local enneagram-llm-local:latest
 ```

## 📚 API 문서

애플리케이션을 실행한 후, 다음 주소에서 API 문서를 확인할 수 있습니다.

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

