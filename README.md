

# 1. 시스템 아키텍처에 대한 설명
## 1차 아키텍쳐
WAS 하나에서 API 를 코루틴으로 병렬 처리
![kakao drawio](https://github.com/academey/kakaobank/assets/14977613/9e912281-9c42-473c-a12d-61ead51301dc)

## 2차 아키텍쳐
병렬 환경에서 수평적으로 전송하도록 Rabbit MQ 를 사용하도록 함 
![kakao-페이지-1의 복사본 drawio](https://github.com/academey/kakaobank/assets/14977613/c064bcf7-5a45-4d02-88fb-f6df9da80b5e)

# 2. 사용자 정보 및 알림 그룹 정보 테이블 스키마 - 구현한 API 명세
### 테이블 스키마
<img width="1502" alt="image" src="https://github.com/academey/kakaobank/assets/14977613/c56b3d08-c74f-4440-ab28-29e23f6643f4">

### API 명세
swagger 로 명시하고 싶었으나 시간이 부족했습니다.
4 -> 4 Postman 참고 부탁드립니다

# 3. 사용한 주요 외부 라이브러리 및 오픈 소스와 사용 목적을 명시
- 서킷 브레이커 : 문제가 발생한 지점을 감지하고 실패하는 요청을 계속하지 않도록 방지하며, 이를 통해 시스템의 장애 확산을 막고 장애 복구를 도와주며 유저는 불필요하게 대기하지 않게 됩니다.
- Rabbit MQ : 컴포넌트 간의 메시지 전달, 발행 및 구독 모델, 비동기 처리, 작업 큐 등을 쉽게 구현할 수 있으며, 이를 통해 시스템에 고가용성 및 로드 스파이크 처리, 전체 시스템의 지속적인 실행, Worker들을 이용한 동시성 등의 장점을 가져올 수 있습니다.

# 4. 시스템 동작에 필요한 주요 설정과 실행 방법에 대한 설명
## 주요 설정 
gradle 빌드 및 docker-compose 설정

## 실행 방법
1. docker-compose up
2. docker exec rabbitmq-stream rabbitmq-plugins enable rabbitmq_management
3. http://localhost:15672/#/queues 에 접속 후 queue-01 이름으로 queue 생성
4. gradle alert-api:bootRun
4. [Postman](https://www.postman.com/blue-spaceship-2858/workspace/kakao-bank) 에 접속해서 Environment 을 local 로 설정하고 API 를 호출해본다.


# TODO
조금 더 시간이 있었다면 구현했을 것들 
- 큐에 쌓는 작업을 청크 단위로 넣기
- Caching 을 이용해서 유저 그룹 정보를 저장
- AsyncRestTemplate 사용해서 동시성 극대화

references : 
- https://dkswnkk.tistory.com/732
