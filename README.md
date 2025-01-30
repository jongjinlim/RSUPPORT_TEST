# 공지사항 API

## 1. 개요
### [기능 요구사항]
- 공지사항 등록, 수정, 삭제, 조회 API를 구현한다.
- 공지사항 등록시 입력 항목은 다음과 같다.
- 제목, 내용, 공지 시작일시, 공지 종료일시, 첨부파일 (여러개)
- 공지사항 조회시 응답은 다음과 같다.
- 제목, 내용, 등록일시, 조회수, 작성자

### [비기능 요구사항 및 평가항목]
- REST API로 구현.
- 개발 언어는 Java, Kotlin 중 익숙한 개발 언어로 한다.
- 웹 프레임 워크는 Spring Boot 을 사용한다.
- Persistence 프레임 워크는 Hibernate 사용시 가산점
- 데이터 베이스는 제약 없음
- 기능 및 제약사항에 대한 단위/통합테스트 작성
- 대용량 트래픽을 고려하여 구현할 것
- 핵심 문제해결 전략 및 실행 방법등을 README 파일에 명시

## 2. 문제 해결 전략

### AWS LocalStack 을 이용해 이미지 업로드
1. Docker 를 이용해 LocalStack 실행
- `docker run --rm -d -p 4566:4566 -p 4571:4571 localstack/localstack`
2. AWS CLI를 설치하고 LocalStack용 프로파일 추가
- `aws configure --profile localstack`
3. S3 버킷 생성
- `aws --endpoint-url=http://localhost:4566 s3 mb s3://my-bucket`
4. 생성된 버킷 확인
- `aws --endpoint-url=http://localhost:4566 s3 ls`

### 성능 최적화
- **캐싱처리**: `@Cacheable`을 이용해 공지사항 조회 시 DB 부하 감소
- **비동기 파일 업로드**: `@Async` 사용하여 S3 업로드 비동기 처리
- **조회수 증가 최적화**: `@Modifying`을 활용한 쿼리 업데이트

### 트랜잭션 관리
- **등록/수정/삭제 트랜잭션 적용**: `@Transactional`을 사용하여 데이터 무결성 유지
- **Batch 처리**: 파일 삭제 및 추가 작업을 `deleteAllInBatch`, `saveAll`로 일괄 처리

## 3. API 명세
- `POST /notice/create` - 공지사항 등록
- `PUT /notice/update/{id}` - 공지사항 수정
- `DELETE /notice/delete/{id}` - 공지사항 삭제
- `GET /notices/{id}` - 공지사항 조회
