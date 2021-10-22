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

## 카카오 로그인
<div style="width: 700px; margin: 20px auto" >
<img src = "https://media.vlpt.us/images/junseokoo/post/2e4ee263-f81b-4144-9d54-4c58c6a2b57b/qwert.PNG">
</div>

+ https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code
  프로트 서버에서 위 링크를 실행합니다. https://kauth.kakao.com/oauth/authorize 서버로 통신하여 client_id의 REST_API_KEY를
  확인 그리고 카카오계정을 확인하여 카카오서버에서 redirect_uri의 url로 통신합니다. 그리고 서버에서는 카카오 서버에서 보내온
  정보를 가지고 기존 회원이면 카카오id만 db에 저장하여 강제 로그인하고, 기존 회원이 아니면 회원가입과 강제 로그인을 합니다.여기서 Frontend와 Backend의 통신과정에서 카카오 로그인을 시도하면 계속 500에러가 떴었는데 구글도 찾아보고 했지만 도무지 방법을 알 수 없었을때
REDIRECT_URI이 기존 설계 경로와는 다르게 설정되어있었다는 것을 발견했었습니다. 작은 실수지만 이렇게 많은 시간을 잡아먹을 수 있다는것을 다시한번 느끼게 되었으며 앞으로는 수정,변경,작성시에 최대한 꼼꼼하게 살펴봐야겠다 라는 생각이 다시금 들게 되었습니다.


+ 위의 문제들은 해결했지만 소셜로그인이 정상 작동 하지않아서  제대로 구현하기로 마음먹고 시작했던 과정
  + 백엔드 강의에서 배웠던 강의의 내용을 기반으로 카카오 로그인 기능을 구현하려고 하였습니다. 백엔드 측에서 REST API 키를 받고 카카오 서비스 규약에 맞게 URI를 구성하여 링크를 만들면 유저는 해당 링크에 접속을 하여 카카오에서 선행적으로 로그인을 진행합니다. 카카오 로그인이
성공적으로 완료되면 자동적으로 Redirection Link에 인가코드와 함께 `GET` 요청을 보내게 되고, 서버는 그 인가코드를 통하여 카카오 서비스에 재요청 후 Access token과 Refresh Token을 받아 클라이언트에 제공합니다.
  + 처음 기능을 구현할 때 프론트와 백엔드 모두는 단순히 서비스 구현 URI를 링크로 담은 엘리먼트를 만들면 된다는 생각을 하였습니다. 엘리먼트를 만들고 그 URI에 실제로 접속한 순간 JSON만 보여줄 뿐, 클라이언트의 스코프로부터 완전히 벗어났습니다. 해당 문제에 관하여 백엔드와 프론트엔드가 토의 결과
  백엔드에서의 서버사이드 렌더링 로직을 클라이언트와의 통신에도 적용하고 있다는 논리적 오류를 발견하였습니다. 이 문제를 발견한 후 클라이언트에서 인가코드를 직접 보내는 로직을 작성하였습니다.
  + 클라이언트 측에서 카카오 로그인 기능을 구현하기 위해서는 앞서 말씀드렸던 규약 URI에 접속하도록 컴포넌트를 구성해야하지만, Redirection URI를 클라이언트 측으로 바꾸어야 했습니다. 클라이언트에서 직접 인가 코드를 받게되고 이를 서버에 `GET`을 통하여 전송한다면 클라이언트의
  스코프를 벗어나지도 않고, 서버에서는 인가코드를 받은 것으로 정상적으로 카카오에 요청을 한 후 **응답을 통해 토큰을 내려줄 수 있기 때문입니다.**
  + 서버에 정상적으로 인가코드를 전송하였으나 서버에서 카카오로 요청을 보내는 과정 속에서 오류가 발생하였는데, 이는 기존에 카카오에 요청할 때 보내는 endpoint를 클라이언트의 주소로 맞춰야 하는 규칙을 지키지 않았기 때문입니다. 해당 부분을 클라이언트의 요청 URI로 바꾸어 요청한 결과
  정상적으로 토큰이 발급되었고, 로그인을 구현하였습니다.
  + 카카오 로그인을 구현하면서 클라이언트와 서버 간의 커뮤니케이션 이슈를 겪었으며, 문제의 결정적인 원인은 강의에서 배운 내용으로만 소통을 하였기 때문이라는 생각을 가지게 되었습니다. 서버 사이드 렌더링과 REST API 통신은 엄연히 다른 경계에 속해있으므로, 해당 기능을 이야기 할 때
  정확하게 어떤 맥락에서 이야기를 하고있는지 알아야겠다는 생각을 하였습니다.




## JWT 구현

+ 처음 프로젝트에서 시도 했던 구현이 회원가입과 로그인 구현입니다. 로그인한 회원이
  정상적인 회원이라면 토큰을 발행하여 프로트 서버에 보냅니다. 그리고 프론트에서는
  http 헤더에 토큰을 저장하여 매번 통신을 할때 마다 서버는 토큰을 확인합니다.
<div style="width: 650px; margin: 20px auto" >
<img src= "https://media.vlpt.us/images/junseokoo/post/8c49c470-c19c-47cb-9106-d7a219bcd33c/asdf.PNG">
<br></br>
<img src = "https://media.vlpt.us/images/junseokoo/post/639091d3-3006-4fcb-a88f-f95ebdb79d5e/asdfgh.PNG">
</div>

+ 토큰을 완성 후 프론트에서 새로고침 후에 로그인이 풀리는 현상이 나타났습니다.
  원인은 서버에서 토큰을 만들고 통신하는 과정에서 하나의 토큰이 아닌
  accessToken과 refreshToken을 동시에 생성하여 프론트 서버에서 유효시간이 있는 토큰의
  삭제 여부에 따라 두 토큰을 비교하여 새로고침 후 로그인이 풀리거나 유지되게 해야 했습니다.
  서버에서 보낸토큰은 accessToken만 보내서 이런 현상이 발생하였습니다.
  이 현상을 발견한 시점이 프로젝트 종료 하루 전이라 refreshToken 생성은 못했지만,
  다음 프로젝트에서는 refreshToken 생성을 구현하여 이 같은 현상이 발생 안하도록 구현 하도록
  하겠습니다.

## TeamWork
+ 이번에 처음으로 Frontend와 Backend가 협력하여 프로젝트를 진행해보았는데 저희 조 같은경우는 우선적으로 `API설계`에 많은 시간을 투자했습니다. 이 과정 덕분에 오류 발생,수정,삭제부분이 생길시 다른조에  비해서 좀더 원활하게 진행이 되었다고 생각합니다. API설계의 중요성을 다시금 깨달았으며 매우 중요한 과정이란것을 알게 되었습니다.
그리고 Frontend와 Backend가 서로의 요구사항 및 변경사항을 당연히 모두가 100% 만족은 할 수 없었겠지만, `지속적인 소통`을 통해 서로의 만족을 채우려고 노력했던 Team이었다고 생각합니다.Frontend와 Backend의 `배려`가 너무 돋보이는 Team이었고 좋은 분위기 속에서 프로젝트를 진행하였기 때문에 목표 한 결과가 나왔다고 생각합니다.


</details>
