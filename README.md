# 14조 정육각

### Team
+ Frontend 개발자 : 고규식, 김기철, 노강표
+ Backend 개발자 : 권민혁, 박시준, 최선강
+ 개발기간 : 2021.10.18 ~ 2021.10.22
+ 개발언어
  + Frontend : HTML / CSS / JAVASCRIPT / REACT
  + Backend : JAVA / SPRINGBOOT / MYSQL / AWS

### Objective
1. Frontend와 Backend 다른환경에서 연동 (CORS)
2. 회원가입 & Spring에서 JWT 방식 로그인
3. 메뉴 카테고리별 조회 구현
4. 장바구니 구현(CRUD 적용)

### Project Collaboration Process
<details markdown = "1">
<summary>
API설계
</summary>

[NOTION](https://www.notion.so/f2d135f6a3a041c2927b8819cb6aff9d) 

 <div style="width:700px; margin: auto" >

### 
|URL　　　　　|Method|설명|
|---|---|---|
|/|GET|메인 리스트|
|/api/login|POST|로그인|
|/api/signup|POST|회원가입|
|/user/kakao/callback|GET|카카오 로그인|
|/api/login/check|POST|로그인 체크|
|/api/list?category={category}|GET|쇼핑하기 리스트|
|/api/detail?productId={productId}|GET|상품 단건 조회|
|/api/cart|GET|장바구니 조회|
|/api/cart|POST|장바구니 추가|
|/api/cart|PUT|장바구니 수량 변경|
|/api/cart|DELETE|장바구니 상품 삭제|

 </div></details>

<details markdown = "1">
<summary>
Diagrams
</summary>
<img src= "https://user-images.githubusercontent.com/42162127/138402585-76a4add3-c741-44a5-8dd0-aa004123fac4.PNG">
 </div></details>

<details markdown = "1">
<summary>
문제점 / 해결과정
</summary>

## ERROR_CONNECTION_REFUSED
+ Spring의 port와 aws의 port가 상이하여 나는 에러였다. Spring port를 8090으로 변경하여 배포 후 서버에서 실행, 웹사이트에서 테스트를 하면 ERROR_CONNECTION_REFUSED 오류가 나타났다.
  aws에서는 포트를 80, 8080, 3306의 포트만 오픈해서 port 불일치 때문이었다. Spring에서 8090 port 설정을 주석처리 후 재 빌드하고 서버에 배포하니 정상으로 동작하였다.
  다음부터는 빌드하기 전 Spring 설정이 제대로 되어있는지 확인 후 빌드를 해야겠습니다.


## api설계 변경 협의
+ 프로젝트 진행중 생각했던 api설계보다 필요한 데이터들이 테스트 과정에서 추가되어 변경사항이 생각보다 잦았습니다. 프론트엔드 개발자와 대화도 많이 필요한거 같고, api설계를 하는데 있어
  많은 경험도 필요하다는 생각을 가졌습니다. 


## TeamWork
+ 하나의 사이트를 클론코딩하며 실제 운영되는 사이트의 디테일한 부분까지 보며 어떻게 프로세스가 흐르는지 생각하는 과정이었습니다. 평소 단순하게 생각하던 장바구니 추가만 하더라도 많은 생각을 가지고 코딩을 하며, 디비테이블의 구조나 객체의 동작 하나하나 생각하는 과정을 체감할 수 있었습니다. 백엔드가 단순히 데이터만 관리하여 프론트엔드에 넘기는게 아니라 큰 구조를 보고 같이 생각하며 프론트엔드 분들과 같이 개발하며 하나의 팀으로 발전할 수 있는 경험을 가졌습니다.

</details>

[](https://www.youtube.com/watch?v=QefErMZS8lg)
