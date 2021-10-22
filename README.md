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
 <div style="width:700px; margin: auto" >

### 로그인/회원가입
|기능　　　　　|Method|URL|Request|Response|
|---|---|---|---|---|
|로그인|POST|/api/login|{ email: email< String >,<br>pw:pw < String >}|{email:email< String >, <br>nickname:nickname< String >,<br>result:"success"< String >,<br>token:token< String >,<br>nickname:nickname < String >}<br>{result:fail < String >,<br>errorMessage:""< String >,<br>httpStatus:"BAD_REQUEST"< String > }
|회원가입|POST|/signup|{email:email< String > ,<br>pw:pw< String > pwCheck:pwCheck< String > <br>nickname:nickname< String >}|{result:success< String > <br> result:fail< String >}|
|아이디 중복확인|POST|/signup/duplicate_id|{ email:email < String > }|{result:success< String > ,<br> result:fail< String >}|
|닉네임 중복확인|POST|signup/duplicate_nickname|{ nickname:nickname < String > }|{reslu:success< String>}<br>{result:fail< String > }|
|카카오|GET|/kakao/callback|-|-|

### 메인페이지
|기능　　　　　|Method|URL|Request|Response|
|---|---|---|---|---|
|게시글목록|GET|/posts/{page}|-| [ content : { <br> insertDt : insertDt< String >,<br>modifiedDt : modifiedDt< String >,<br>id : id< String >,<br>category : category< String >,<br>titile : titile< String >,<br>author : author< String >,<br>nickname : nickname< String >,<br>contents : contents< String >},<br>pageable : {<br>pageSize : 10,<br>pageNumber : 1},<br>last : last< String >,<br>totalPages : totalPages< Number >,<br>totalElements : totalElements< Number >,<br>size : size< Number >,<br>number : number< Number >,<br>first : < Boolean >,<br>last:  < Boolean >,<br>numberOfElements : numberOfElements< Number >,],<br>...<br>]|
|카테고리 게시글 목록|GET|/posts/{category}/{page}|-|[content : {<br>insertDt : insertDt< String >,<br>modifiedDt : modifiedDt< String >,<br>id : id< String >,<br>category : category< String >,<br>titile : titile< String >,<br>author : author< String >,<br>nickname : nickname< String >,<br>contents : contents< String ><br>},<br>pageable : {<br>pageSize : 10,<br>pageNumber : 1<br>},<br>last : last< String >,<br>totalPages : totalPages< Number >,<br>totalElements : totalElements< Number >,<br>size : size< Number >,<br>number : number< Number >,<br>first : first< String >,<br>numberOfElements : numberOfElements< Number >,],<br>...<br>]|

### 상세페이지
|기능　　　　　|Method|URL|Request|Response|
|---|---|---|---|---|
|게시글,댓글가져오기|GET|/post/{id}|{id : postid< String >}|{<br>category: category< String >,<br>title : title< String >,<br>author : userEmail< String >,<br>contents : contents< String >,<br>insertDt : insertDt< String >,<br>nickname : nickname< String >,<br>comments: []< List ><br>}}
|게시글 수정|POST|/post/{id}|{<br>id : postId< Number >,<br>title : title< String >,<br>contents : contents< String >,<br>category: category< String ><br>}| {<br>author: email< String >,<br>category: category< String >,<br>contents: newContents< String >,<br>id: postId< Number >,<br>insertDt: insertDt< String >,<br>nickname: nickname< String >,<br>title: newTitle< String >,<br>}|
|게시글 삭제|DELETE|/post/{id}|-|{result : 'success'< String >},<br>{result : 'fail'< String >}|
|댓글삭제|DELETE|/comment/{id}|-|{reslut:success< String>}<br>{result:fail< String > }|
|댓글수정|POST|/comment/{id}|{<br>id : commentId< String >,<br>comment : comment< String >,<br>}|{<br>result: 'success'< String >,<br>comment: {<br>comment: "sasd"< String >,<br>id: 42< Long >,<br>insertDt: "2021-10-14T22:15:58.574136"< String >,<br>modifiedDt: "2021-10-14T22:16:19.074515"< String >,<br>nickname: "오세명오세명"< String >,<br>post: {<br>author: userEmail< String >,<br>category: category< String >,<br>contents: content< String >,<br>id: 17< Long >,<br>insertDt: isoString< String >,<br>modifiedDt: isoSTring< String >,<br>nickname: userNickname< String >,<br>title: title< String >,<br>}<br>}<br>}|
|댓글작성|POST|/comment|{<br>comment : comment< String >,<br>postId: unique post id< Number ><br>}|-|

### 게시글 작성
|기능　　　　　|Method|URL|Request|Response|
|---|---|---|---|---|
|게시글등록|POST|/post|{<br>category: category< String ><br>title:title< String >,<br>contents:contents< String >,<br>}|{<br>id : id< String >,<br>category: category< String >,<br>title : title< String >,<br>author : userEmail< String >,<br>contents : contents< String >,<br>insertDt : insertDt< String >,<br>nickname : nickname< String >,<br>comments: []< List ><br>}<br>{<br>result: 'fail'<br>}|

 </div></details>
