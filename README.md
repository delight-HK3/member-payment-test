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

### Docker 실행방법
Docker 및 Docker-compose 설치 후 dockerfile이 위치해 있는 폴더에서 ```docker-compose up -d``` 명령어를 입력하고 실행하면 MySQL설치 및 API가 빌드 및 실행됩니다.

### 엔드포인트
(해당 문서의 응답예시는 모두 실제로 존재하지 않는 더미데이터 입니다.)

### 기능별 목록
- [회원 관련 기능](#회원-관련-기능)
- [포인트충전 관련 기능](#포인트충전-관련-기능)
- [성공메세지](#성공메세지)
- [에러메세지](#에러메세지)

---
### 회원 관련 기능

#### **GET** /point/v1/member
- 설명 : 현재 존재하는 회원목록을 조건에 맞게 페이지로 조회합니다.


**요청 파라미터**
- sort (선택) : 정렬기준

| 조건 | 설명 | 
| --- | --- |
| name | 회원이름 가나다순 정렬 | 
| viewcnt | 회원 조회수 내림차순 |
| createdt | 회원 등록일 내림차순 | 
| default | 회원 ID 기준 내림차순 | 

- pageno (선택) : 페이지 번호

<br>

**응답 파라미터**

| Header / Body | 파라미터 | 타입 | 설명 | 
| --- | --- | --- | --- |
| Header | code | String | 요청결과 코드 | 
|  | message | String | 요청결과 메세지 | 
| Body | id | Long | 회원 고유번호 |
|  | name | String | 회원 이름 |
|  | viewCount | int | 회원 프로필 상세 조회수 |
|  | amountPoint | int | 회원이 소지하고 있는 포인트 |
|  | createDt | LocalDateTime | 회원 초기 등록날짜 |

응답 예시
```json
{
    "response": {
        "header": {
            "code": "SUCCESS_GET",
            "message": "successfully get"
        },
        "body": {
            "data": [
                {
                    "id": 3,
                    "name": "김기명",
                    "viewCount": 0,
                    "amountPoint": 0,
                    "createDt": "2025-04-26 16:17:39"
                },
                {
                    "id": 2,
                    "name": "김길동",
                    "viewCount": 0,
                    "amountPoint": 0,
                    "createDt": "2025-04-26 16:17:38"
                },
                {
                    "id": 1,
                    "name": "홍길동",
                    "viewCount": 8,
                    "amountPoint": 120000,
                    "createDt": "2025-04-26 16:17:36"
                }
            ],
            "pageNo": 2
        }
    }
}
```

<br>

#### **GET** /point/v1/member/{memberid}
- 설명 : 특정 ID의 회원 조회 및 조회수 1증가
- 요청 파라미터 
    - memberid (필수) : 회원 번호 

<br>

**응답 파라미터**

| Header / Body | 파라미터 | 타입 | 설명 | 
| --- | --- | --- | --- |
| Header | code | String | 요청결과 코드 | 
|  | message | String | 요청결과 메세지 | 
| Body | id | Long | 회원 고유번호 |
|  | name | String | 회원 이름 |
|  | viewCount | int | 회원 프로필 상세 조회수 |
|  | amountPoint | int | 회원이 소지하고 있는 포인트 |
|  | createDt | LocalDateTime | 회원 초기 등록날짜 |

응답 예시
```json
{
    "response": {
        "header": {
            "code": "SUCCESS_GET",
            "message": "successfully get"
        },
        "body": {
            "data": {
                "id": 2,
                "name": "김길동",
                "viewCount": 1,
                "amountPoint": 0,
                "createDt": "2025-04-26 16:17:38"
            }
        }
    }
}
```

[목록으로 이동하기](#기능별-목록)

<br>

### 포인트충전 관련 기능
포인트 충전 기능은 토스페이먼츠API를 사용했습니다. 결제 신청을 시각적으로 확인이 가능하도록 클라이언트 부분도 같이 구현했습니다.

#### GET /point/v1/main
토스페이먼츠 메인 페이지가 출력됩니다.

![tosspay](https://github.com/user-attachments/assets/9b666b4f-f85a-4c31-b699-ca51d3af80ef)

토스페이 버튼을 클릭하고 결제 과정을 진행하고 요청이 성공적으로 이루어지면 /success 페이지로 이동합니다.

#### GET /point/v1/success
토스페이먼츠 성공시에 출력되는 페이지가 출력됩니다.

![tosspaysuccess](https://github.com/user-attachments/assets/5eeb6c85-62b9-4222-8f8f-121486dd846b)

(하단의 Response는 원래 토스페이먼츠 Response는 다른 값을 가지고 있습니다. 하지만 주문번호를 임의로 생성했기에 "결제 시간이 만료되어 결제 진행 데이터가 존재하지 않습니다."라는 메세지가 리턴됩니다. 그래도 원래목적인 API연동은 완수했기에 Success로 리턴하도록 제작했습니다.)

[목록으로 이동하기](#기능별-목록)

<br>

### 성공메세지
API에 요청을 하여 정상적으로 완료되면 다음과 같은 응답을 받습니다.

| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| OK | SUCCESS_GET | successfully get | GET 요청을 성공적으로 마치면 출력되는 메세지 입니다. |
| CREATED | SUCCESS_POST | successfully post | POST 요청을 성공적으로 마치면 출력되는 메세지 입니다.  |

[목록으로 이동하기](#기능별-목록)

<br>

### 에러메세지
API에 요청을 하여 비정상적인 요청 및 동작을 했을경우 다음과 같은 응답을 받습니다.

클라이언트 요청 관련 오류 응답메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| BAD_REQUEST | NO_REQUIRED_ARGUMENT | A required parameter is missing. | 필수 파라미터가 누락되었습니다. |
| BAD_REQUEST | NO_VALUE_MISMATCH_ARGUMENT | The value of the parameter is incorrect. | 파라미터의 값이 올바르지 않습니다.  |
| BAD_REQUEST | NO_TYPE_MISMATCH_ARGUMENT | The parameter type is incorrect. | 파라미터 타입이 올바르지 않습니다.  |
| NOT_FOUND | NOT_FOUND_PAGE | The request does not exist. | 존재하지 않는 요청입니다.  |
| METHOD_NOT_ALLOWED | NO_MATCH_METHOD | This method is not allowed. | 허용되지 않는 메서드 입니다.  |

데이터베이스 관련 메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| CONFLICT | USER_CONFLICT | There has been a conflict with another user. Please try terrorizing again. | 다른 사용자와 충돌이 발생했습니다. 잠시 후 다시 시도해주세요. |

결제관련 메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | NO_SEARCH_PAYMENT | This payment platform is not supported. | 지원하지 않는 결제 플랫폼 입니다. |

회원관련 메세지
| HTTP STATUS | 코드 | 메세지 | 설명 |
| --- | --- | --- | --- |
| NOT_FOUND | NO_SEARCH_MEMBER | This member does not exist. | 존재하지 않는 회원 입니다. |
| NOT_FOUND | MEMBER_EMPTY | There is no member list | 회원목록이 없습니다. |

[목록으로 이동하기](#기능별-목록)
