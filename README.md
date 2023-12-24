# 1. 시스템 아키텍처에 대한 설명
캐시 -> 디비 -> 리얼타임의 우선순위로 요청하고 갱신하도록 되어 있습니다.

![kakaobank drawio](https://github.com/academey/blog-search-server/assets/14977613/db28583a-f846-41a8-a19b-51e6b9e5ed3c)


# 2. 사용한 주요 외부 라이브러리 및 오픈 소스와 사용 목적을 명시
- 레디스 : 레디스는 메모리 내에 데이터를 저장하므로 빠른 응답 속도를 제공합니다. 이는 데이터베이스 쿼리나 계산이 필요한 데이터를 미리 계산해 두어 다시 계산하지 않고 바로 사용할 수 있도록 함으로써 웹 애플리케이션의 응답 시간을 향상시킬 수 있습니다.

# 3. 시스템 동작에 필요한 주요 설정과 실행 방법에 대한 설명
## 주요 설정 
gradle 빌드 및 docker-compose 설정

## 실행 방법
1. docker-compose up 
2. gradle blog-search-api:bootRun
3. [Postman](https://www.postman.com/blue-spaceship-2858/workspace/kakao-bank) 에 접속해서 Environment 을 local 로 설정하고 API 를 호출해본다.

# TODO
조금 더 시간이 있었다면 구현했을 것들 
- Caching 을 Cachable로 사용해서 AOP 관점으로 개선
- application.yml 에 그냥 박아놓은 크레덴셜 값들 환경변수로 빼도록 개선
- RealtimeSearchRepository 에서 실패할 경우 서킷브레이커를 넣어서 요청하지 않도록 개선
- RealtimeSearchRepository 에서 datetime Parsing 하도록 변경
