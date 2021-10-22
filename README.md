# 14조 정육각

### Team
+ Frontend : 고규식, 김기철, 노강표 (REACT)
+ Backend : 권민혁, 박시준, 최선강 (SPRING)

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

### 로그인/회원가입
|URL　　　　　|Method|설명|
|---|---|---|
|/api/login|POST|로그인|
|/api/signup|POST|회원가입|
|/user/kakao/callback|GET|카카오 로그인|
|/api/login/check|POST|로그인 체크|

### 메인페이지
|URL　　　　　|Method|설명|
|---|---|---|
|/|GET|메인 리스트|
 
### 쇼핑하기 페이지
|URL　　　　　|Method|설명|
|---|---|---|
|/api/list?category={category}|GET|쇼핑하기 리스트|

### 상세페이지
|URL　　　　　|Method|설명|
|---|---|---|
|/api/detail?productId={productId}|GET|상품 단건 조회|
 
 ### 장바구니 페이지
|URL　　　　　|Method|설명|
|---|---|---|
|/api/cart|GET|장바구니 조회|
|/api/cart|POST|장바구니 추가|
|/api/cart|PUT|장바구니 수량 변경|
|/api/cart|DELETE|장바구니 상품 삭제|

 </div></details>
