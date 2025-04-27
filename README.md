# member-point-test

### API 명세서

### member-point-Rest-API
해당 API는 JPA를 이용한 REST API입니다. 다양한 방식의 회원조회 및 포인트 충전서비스를 제공하고 있습니다. 

### 기본정보
- API 이름 : member-point-Rest-API
- 버전 : v1
- 기본 URL : http://localhost:8090/point/v1
```
API 기술 스택

Framework : Spring boot 3.4.4
Language : Java 17
Database : MySQL 8.0.42
ORM : Spring Data JPA, QueryDSL
Tool : Visual Studio Code
```

```
테스트 환경

클라우드 : AWS EC2
OS : Ubuntu Server 24.04 LTS
Language : eclipse temurin Java 17
가상화 플랫폼 : Docker, Docker-compose
```

### Docker-compose 사용사유
Docker-compose를 쓴 이유는 아무래도 하나의 dockerfile로 애플리케이션을 실행할 수 있도록 구현해야 했기 때문입니다. API만 실행했다면 설정이 복잡하지 않았지만 MySQL 설치 및 관련 설정들로 인해 Dockerfile 내용이 복잡해졌고 사소한 오타 하나만으로 제대로 동작하지 않는 현상이 매번 발생했습니다. 그래서 실수를 줄이고자 다른 방법을 찾아보니 docker-compose를 통해 독립적인 블록으로 지정하여 관리하는 방법을 찾게 되었고 docker-compose를 도입한 결과 데이터베이스 설치, API 설치, SQL쿼리 작성을 별도로 관리하게 되니 실수를 줄일 수 있었고 컨테이너를 효율적으로 관리 할 수 있었습니다.


### 엔드포인트
(해당 문서의 응답예시는 모두 실제로 존재하지 않는 더미데이터 입니다.)
