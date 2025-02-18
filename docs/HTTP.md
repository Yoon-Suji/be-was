# WEB
## Web Client & Server

### The Web (Internet)

- massive distributed client/server information system
- In order for proper communication to take place between the client and the server, **applications that running concurrently over the Web must agree on a specific application-level protocol** such as HTTP, FTP, SMTP, POP, and etc.

### 브라우저에서 서버에 요청을 보내고 응답을 받아 화면에 출력하는 과정

![](https://firebasestorage.googleapis.com/v0/b/nextstep-real.appspot.com/o/lesson-attachments%2F-KpOwxr2PBZkaRejxU49%2Fclient-server1.PNG?alt=media&token=037675f8-6a53-49a7-be00-07c6d519c880)

- 네트워크와 연결되어 있는 서버는 고유한 IP를 가짐 → 연결할 웹 서버는 도메인이 아니라 IP를 활용해 접근

![](https://firebasestorage.googleapis.com/v0/b/nextstep-real.appspot.com/o/lesson-attachments%2F-KpOwxr2PBZkaRejxU49%2Fclient-server2.PNG?alt=media&token=882a86cb-3af0-4bf7-957b-e915e9bebdc3)

- 브라우저는 연결할 웹 서버의 IP 주소와 PORT Number를 활용해 Connection을 맺는다 (기본 port는 80)
    - 웹 서버에 Request
    - 웹 서버는 HTML을 생성한 후 Response
    - 브라우저는 Response를 받은 후 웹 서버와 연결을 끊음
    - 브라우저는 응답 Header와 HTML을 읽은 후 웹 자원(js, css, image ..)에 대한 재요청

# HTTP (HyperText Transfer Protocol)
![](https://www3.ntu.edu.sg/home/ehchua/programming/webprogramming/images/HTTP_Steps.png)

## HTTP

- **Protocol**: 데이터를 주고받기 위해 클라이언트와 서버 간 약속한 형식
- **HTTP**: 웹 서버와 클라이언트 간에 약속한 형식
    - **pull** protocol: client pulls information from the server
    - **stateless** protocol: current request does not know what has been done in the previous requests
    - HTTP permits negotiating of data type and representation → 서로 다른 시스템 간에도 효율적으로 통신 가능

> Quoting from the RFC2616: "**The Hypertext Transfer Protocol (HTTP)** is an **application-level protocol** for distributed, collaborative, hypermedia information systems. It is a **generic**, **stateless** protocol which can be used for many tasks beyond its use for hypertext, such as name servers and distributed object management systems, through extension of its request methods, error codes and headers."

## URL (Uniform Resource Locator)

- **URI(Uniform Resource Identifier)**는 URL의 상위 개념이지만 혼용해서 사용하고 있고 같은 것으로 이해해도 무방

```text
protocol://hostname:port/path-and-file-name

ex) http://slipp.net/questions/539
```

- **스킴(scheme), Protocol**: 리소스를 획득하기 위한 방법 (`http`)
- **호스트명**: 리소스가 존재하는 컴퓨터 이름, 도메인 또는 IP (`slipp.net`)
- **Port**: TCP port number
    - port number was not specified in the URL, and takes on the **default number**, which is **TCP port 80 for HTTP**
- **경로명**: 호스트로 지정된 컴퓨터에서의 자원의 위치 (`questions/539`)

## HTTP Request

- 브라우저에서 URL을 입력하면 브라우저가 특정 프로토콜에 따라 URL을 Request Message로 translate해서 서버에게 전송

![](https://www3.ntu.edu.sg/home/ehchua/programming/webprogramming/images/HTTP_RequestMessageExample.png)

### HTTP Request Line

```text
request-method-name request-URI HTTP-version

ex) GET /user/create?userId=javajigi&password=password HTTP/1.1
```

- **Path**: `/user/create?userId=javajigi&password=password`
- **Query String**: `userId=javajigi&password=password`
    - URI 뒤에 쿼리 스트링으로 보낼 수 있는 데이터의 크기에는 한계가 있고, 브라우저의 주소 박스에 노출된다는 단점
    - POST 메소드는 Request Message의 body에 정보를 전달하고 데이터의 크기에 한계가 없음

### HTTP Request Headers

```text
request-header-name: request-header-value1, request-header-value2, ...

ex) 
Host: www.xyz.com
Connection: Keep-Alive
Accept: image/gif, image/jpeg, */*
Accept-Language: us-en, fr, cn
```

- Request Header는 `name: value` 쌍으로 이루어짐: multiple value 가능

## HTTP Method
![http-header-functions (1)](https://github.com/Yoon-Suji/be-was/assets/70956926/67f0d190-550b-4af6-b560-22e8c9997fd5)

### GET vs. POST

- `GET:` Select적인 성향, 서버에서 어떤 데이터를 가져와서 보여준다거나 하는 용도이지 서버의 값이나 상태등을 바꾸지 않음(ex. 게시판의 리스트라던지 글보기 기능)
- `POST`: 서버의 값이나 상태를 바꾸기 위해서 사용 (ex. 글쓰기를 하면 글의 내용이 디비에 저장이 되고 수정을 하면 디비값이 수정)

### GET
* 특정한 리소스를 가져오도록 요청, 데이터를 가져올 때만 사용해야 함
* GET 요청에는 Request Body를 담지 않는 것이 바람직하다 -> HTTP 명세를 위반하기 때문

### POST
* 서버로 데이터를 전송
* `PUT`과의 차이는 멱등성: `PUT`은 한 번을 보내도, 여러 번을 연속으로 보내도 같은 효과
  * **POST-redirect-GET 패턴**: 중복 submit을 방지하기 위한 패턴
  * POST 요청 시, 요청 본문에 고유한 식별자를 포함시켜 서버에서 중복 요청을 인식해 멱등성을 보장하는 방법
  * 클라이언트 측에서 중복 요청을 제거하는 방법
* 웹 브라우저가 웹 폼(form)을 통해 `POST` 요청을 보낼 때 기본 인터넷 미디어 타입(Content-Type)은 `application/x-www-form-urlencoded`
  * 각각의 키-값 쌍은 '&' 문자로 구분되며 각 키는 '=' 문자의 값과 구분된다. 키와 값들은 둘 다 공백을 '+' 문자로 대체하며 영숫자가 아닌 그 밖의 모든 문자는 퍼센트 인코딩 처리
  ```text
  userId: 1
  password: hello
  name: suji
  email: example@softeer.com
  ```
  ```text
  userId=1&password=hello&name=suji&email=example%40softeer.com
  ```

## HTTP Response

### HTTP Request를 받은 서버가 동작하는 방법

- Request를 해석해서 파일 경로에 위치한 파일을 클라이언트에게 리턴
- Request를 해석해서 서버 안에 위치한 프로그램을 실행시켜 나온 output을 클라이언트에게 리턴
- 잘못된 Request의 경우 에러 메세지 리턴

![](https://www3.ntu.edu.sg/home/ehchua/programming/webprogramming/images/HTTP_ResponseMessageExample.png)

### HTTP Response Status Line

```text
HTTP-version status-code reason-phrase

ex) HTTP/1.1 200 OK
```

- `reason-pharase`: gives a short explanation to the status code

### HTTP Response Headers

```text
response-header-name: response-header-value1, response-header-value2, ...

ex)
Content-Type: text/html
Content-Length: 35
Connection: Keep-Alive
Keep-Alive: timeout=15, max=100
```

- Response Headers는 `name: value` 쌍으로 이루어짐: multiple value 가능

### HTTP Status Code

| Code | reason-pharase                    |
|------|---------------------|
| 200  | OK                  |
| 201  | Created             |
| 302  | Found (HTTP 1.0)    |
| 304  | Not Modified        |
| 401  | Unauthorized        |
| 404  | Not Found           |
| 500  | Internel Server Error |
| 503  | Service Unavailable |
- **2xx**: 성공. 클라이언트가 요청한 동작을 수신하여 이해했고 승낙했으며 성공적으로 처리
- **3xx**: 라다이렉션 완료. 클라이언트는 요청을 마치기 위해 추가 동작이 필요함.
- **4xx**: 요청 오류. 클라이언트에 오류가 있음
- **5xx**: 서버 오류. 서버가 유효한 요청을 명백하게 수행하지 못했음

> 💡 Java Backend 웹 프로그래밍에서 forward(200으로 응답)와 redirect(302으로 응답) 방식으로 응답할 때의 차이점은?
>
> - **Forward (200, OK)**: 웹 서버에서 처리되는 내부이동으로 클라이언트는 동작을 감지하지 못함; 원래의 요청 정보가 유지되기 때문에 클라이언트는 동일한 URL 유지; 주로 서버 내부에서 다양한 리소스 간에 데이터를 공유하고자 할 때 사용
> - **Redirect (302 Found)**: 클라이언트에게 새로운 URL로 이동하라는 메세지를 전달하는 방식; 클라이언트는 새로운 URL로 다시 요청을 보내야 함; 주로 서버 외부로의 이동 또는 새로운 리소스로의 이동에 사용


> 💡 웹 브라우저에서 POST 요청을 처리하는 경우: **POST-Redirect-GET 패턴**
> 
> - 웹 애플리케이션에서 중복으로 데이터를 제출하는 문제를 방지하기 위한 디자인 패턴 (사용자가 폼을 제출한 후에 브라우저를 새로 고침하거나 뒤로가기를 눌렀을 때 중복으로 데이터가 제출되는 것을 방지)
> - 클라이언트가 POST 요청을 서버에게 보내고 서버가 해당 요청을 처리한 후에, 중복 제출을 방지하기 위해 클라이언트에게 리다이렉트할 것을 알리는 응답을 보냄: HTTP 1.1 스펙에서는 303 See Other 상태 코드를 권장하지만 현재까지는 대부분 302 Found 코드 사용
> - 클라이언트는 리다이렉트 응답을 받으면, 지정된 URL로 GET 요청을 보냄: 브라우저에서 새로고침하더라도 GET 요청이 발생하므로 중복으로 데이터가 제출되지 않음
>
> 🚨 모든 중복 submit을 완벽하게 막을 수 있는 것은 아님
> - 리다이렉트 응답을 받기 전에 사용자가 브라우저를 닫는 경우
> - 사용자가 여러 탭이나 창을 사용하여 동시에 submit하는 경우
> - 네트워크 지연 문제


## Content Negotiation

- 클라이언트는 추가적인 Request Headers를 통해서 서버에게 처리할 수 있거나 선호하는 컨텐츠 타입을 알릴 수 있음

### Content-Type Negotiation (MIME type)

- 서버는 주로 요청된 리소스의 확장자를 기반으로 **MIME Configuration 파일**(`conf\mime.types`)을 이용해서 파일 확장을 특정 media type에 매핑
    - ex) file extensions `.html` → MIME type `text/html` , `.jpg`, `.jpeg` → `image/jpeg`
- 서버에 여러 타입의 파일이 존재하는 경우, 클라이언트의 요청 헤더의 `Accept` 값에 따라 선호하는 타입을 보내준다
- 서버의 Default MIME-Type 은 서버마다 다른데, 2.2.7 이전의 Apache Web Server의 경우에는 `text/plain` 혹은 `application/octet-stream`을 알 수 없는 컨텐트 타입에 사용하였고, Apache Web Server 최근 버전에서는 `none` 을 사용한다. Nginx는 `text/plain`을 default content type으로 사용한다.

#### Content-Type을 왜 지정해주어야 할까?
* 웹 서버에서 잘못된 Content-Type을 지정해주거나 Content-Type을 지정해주지 않으면 웹 브라우저는 서버의 의도를 알 수 없기 때문에 예상하지 못한 동작이 발생할 수 있음
* Loss of Control: 브라우저에서 Content-Type을 추측하게 되면 웹 어플리케이션의 작성자가 자신의 컨텐트가 어떻게 처리되는지에 대한 control 권한을 잃어버리게 된다
* Security: 일부 Content-Type은 본질적으로 안전하지 않기 때문에 브라우저에서의 수행하는 작업을 제안해야 한다

## Non-Persistent HTTP vs. Persistent HTTP

### Non-Persistent HTTP

- 서버가 응답을 전송하면 TCP 연결 close → TCP 연결 하나 당 최대 하나의 요청만 서비스함
- 대부분의 HTML 페이지는 추가적인 리소스에 대한 하이퍼링크가 포함되어 있으므로 브라우저는 동일한 서버에 대해 매번 리소스를 요구할 때마다 TCP 연결을 설정해야 함 → 효율적 X
- HTTP/1.0 의 기본 연결 방식
- non-persistent HTTP response time = 2RTT * 객체의 수 + file transmission time

### Persistent HTTP

- 클라이언트는 서버와 협상하여 응답을 전달한 후 연결을 닫지 않도록 서버에 요청하여 동일한 연결을 통해 다른 요청을 보낼 수 있음
- 클라이언트는 응답을 전달받은 후 연결을 닫도록 서버에 요청하기 위해 `Connection: Close` 헤더를 포함해서 요청을 보낼 수 있음
- 많은 작은 인라인 이미지와 기타 관련 데이터가 있는 웹 페이지에 매우 유용 → 클라이언트가 추가로 TCP 연결을 설정할 필요 없이 바로 요청할 수 있어서 응답이 더 빠름
- HTTP/1.1 의 기본 연결 방식: `Connection: Keep-alive`
- persistent HTTP response time = 1RTT + (1RTT * 객체의수) + file transmission time

## Cookie & Session
### HTTP == Stateless 프로토콜
* 각각의 요청이 서로 독립적이며, 이전 요청이나 응답에 대한 정보를 서버가 유지하지 않음
  * WebSocket은 양방향 통신을 가능케 하는 프로토콜로, 한 번 연결된 후에는 계속 연결 상태를 유지함으로써 Stateless의 단점을 보완하고 상태(Stateful)를 지원하기 위한 기술
  
  👉 HTTP는 유저의 상태 정보를 어떻게 유지할까?

### Cookie
* HTTP 쿠키(웹 쿠키, 브라우저 쿠키)는 서버가 사용자의 웹 브라우저에 전송하는 작은 데이터 조각
  * 서버는 **Set-Cookie** header 값으로 상태를 유지하고 싶은 값을 클라이언트에 전송
* 브라우저는 그 데이터 조각들을 저장해 놓았다가, 동일한 서버에 재 요청 시 저장된 데이터를 함께 전송
  * 클라이언트는 Set-Cookie에 담겨 있는 값을 꺼내 이후에 발생하는 모든 요청의 **Cookie** header로 다시 서버에 전송
* 서버는 클라이언트에서 전송한 Cookie header 값에 따라 이전에 접속한 사용자인지를 판단

![](https://s3.ap-northeast-2.amazonaws.com/lucas-image.codesquad.kr/1642377928644cookie.jpg)

#### Cookie의 단점
* 쿠키는 모든 HTTP 요청에 자동으로 포함되기 때문에, 모든 요청마다 중복된 데이터가 전송되어 네트워크 성능에 부담을 줄 수 있음
* 쿠키에 저장할 수 있는 데이터 크기에 제한이 있음
* 쿠키는 사용자의 브라우저에 저장되기 때문에 보안에 취약

> 최근에는 웹 스토리지 API와 IndexedDB 같은 Modern Storage APIs를 사용하여 클라이언트 측에서 정보를 저장하는 것이 권장됨

#### Cookie 만들기
* **Set-Cookie** HTTP 응답 헤더를 사용해서 서버로부터 클라이언트로 전달
  ```text
  HTTP/1.0 200 OK
  Content-type: text/html
  Set-Cookie: yummy_cookie=choco
  Set-Cookie: tasty_cookie=strawberry
  
  [page content]
  ```
* 브라우저는 Cookie 헤더를 사용하여 서버로 이전에 저장했던 모든 쿠키들을 회신
  ```text
  GET /sample_page.html HTTP/1.1
  Host: www.example.org
  Cookie: yummy_cookie=choco; tasty_cookie=strawberry
  ```
  
#### 쿠키의 라이프 타임
* **세션 쿠키(Session cookie)**: 현재 세션이 끝날 때 삭제; 브라우저는 "현재 세션"이 끝나는 시점을 정의하며, 어떤 브라우저들은 재시작할 때 세션을 복원해 세션 쿠키가 무기한 존재할 수 있도록 한다
* **영속적인 쿠키(Permanent cookie)**: Expires 속성에 명시된 날짜에 삭제되거나, Max-Age 속성에 명시된 기간 이후에 삭제됨

#### Cookie Attribute
* `Domain=<domain-value>`: 쿠키가 전송되게 될 호스트들을 명시; 명시하는 경우 서브 도메인도 포함된다; 생략하는 경우 현재 도메인이 기본값으로 설정되고 서브 도메인은 포함하지 않는다
  * ex) `Domain=mozilla.org`이 설정되면, 쿠키들은 `developer.mozilla.org`와 같은 서브도메인 상에 포함
* `Path=<path-value>`: 헤더를 전송하기 위하여 요청되는 URL 내에 반드시 존재해야 하는 URL 경로
  * ex) `Path=/docs` -> `/docs`, `/docs/Web/`, `/docs/Web/HTTP` 모두 매치됨
* `Expires=<date>`: HTTP-date timestamp 타입으로 쿠키의 최대 라이프타임 명시; 생략하면 쿠키는 Session Cookie가 됨
* `Max-Age=<number>`: 쿠키가 만료되기까지 남은 초(seconds)를 명시; 0이거나 음수인 경우 쿠키를 즉시 만료시킴
  * `Expires`와 `Max-Age`가 동시에 설정되어 있는 경우 `Max-Age`가 우선순위를 가짐
* `HttpOnly`: `Document.cookie` 등의 프로퍼티로 JavaScript에서 쿠키에 접근하는 것을 제하여 against cross-site scripting (XSS) 공격을 방지
  * **XSS**: 웹 애플리케이션에서 발생하는 보안 취약점 중 하나로, 공격자가 악의적인 스크립트를 삽입하여 사용자의 브라우저에서 실행되도록 하는 공격
* `Secure`: HTTPS 프로토콜 상에서 암호화된(encrypted) 요청일 경우에만 쿠키 전송

### Session
* 세션은 기본으로 Cookie를 사용하는 방법은 동일하지만 클라이언트와 서버간에 저장하고 싶은 데이터를 서버에 저장한 후 이 값에 대한 유일한 ID 값(Session ID)을 발급해서 Cookie로 전달한다
* 클라이언트와 서버 사이는 Cookie를 통해 Session Id만 주고 받는다.
* 서버는 클라이언트가 전송한 Session Id를 활용해 서버에 저장된 값을 꺼내와 사용한다.
* 보안성이 높고, 민감한 정보를 안전하게 저장할 수 있다는 장점

![](https://i1.wp.com/4.bp.blogspot.com/-FnbFMxnKkV4/WV565AN7ifI/AAAAAAAAQZg/5_p-m1oxBqUx2CCqyqS3Y9JAUwmGO34nQCLcBGAs/s1600/1.png?w=687&ssl=1)

# MVC (Model View Controller)

## MVC의 등장

### 초기 웹 개발

- PHP, JSP, ASP와 같은 기술 활용해 웹 애플리케이션 개발
- HTML과 프로그래밍 언어가 혼재되어 프로그래밍
- 초기 학습 비용이 낮고, 초반 개발 속도는 빠른 경향

> 🚨 웹 애플리케이션의 복잡도가 증가, 소스 코드 복잡도 증가하면서 자연스럽게 유지보수 비용이 많이 발생
>
>👉 한 곳에서 많은 로직을 처리하기보다는 역할을 분담하기 위해 등장한 것이 MVC

## MVC

### MVC의 역할 분담

- **Controller**: 최초 진입 지점. 사용자의 입력 값이 유효한 지를 검증하고, 사용자가 입력한 데이터를 Model에 전달하고, Model의 처리 결과에 따라 이동할 View를 결정하는 역할
- **Model**: 실질적인 비즈니스 로직을 구현하는 역할을 담당함. 비즈니스 로직 처리 결과를 DB에 저장하고 조회하는 역할. 애플리케이션의 엔진이라 할 수 있음.
- **View**: Controller에 의해 전달된 데이터를 단순히 출력하는 역할

### 프레임워크의 등장

- MVC 기반으로 개발한 결과 구현할 코드량도 많아지고 개발 생산성이 떨어지는 단점이 발생
- 이 같은 단점을 보완하기 위해 MVC 기반 개발을 지원하는 프레임워크가 등장
- 많은 기반 코드를 구현해 제공함으로써 개발자들이 구현할 부분을 최소화해 생산성을 높이는 효과

## Spring MVC 패턴
![](https://velog.velcdn.com/images/choidongkuen/post/04e2d8a0-5bcf-4dbe-b430-420d4bad9f11/image.png)

### Spring MVC 동작 패턴
1. 클라이언트의 요청이 DispatcherServlet에 도착
2. DispatcherServlet은 Handler Mapping을 통해 해당 요청을 처리할 컨트롤러를 탐색
3. 찾은 컨트롤러의 메서드가 실행되어 비즈니스 로직이 수행
4. 컨트롤러에서는 모델을 업데이트하고, 뷰의 이름을 반환
5. DispatcherServlet은 View Resolver를 통해 해당 뷰를 찾고, 모델 데이터를 뷰에 전달
6. 뷰는 최종적인 응답을 생성하여 클라이언트에 반환

### Layered Architecture
![](https://camo.githubusercontent.com/d0ab6054d2169349e92ce94d8349a49f77b717e6288c541a80c282f03e16f87d/68747470733a2f2f637068696e662e707374617469632e6e65742f6d6f6f632f32303138303231395f3238332f3135313930303931323134383675334c6b445f504e472f322e706e67)
* 소프트웨어를 여러 계층으로 나누어 각 계층이 특정 역할을 수행하도록 설계하는 소프트웨어 아키텍처 디자인 패턴

#### Service
* 비지니스 메소드를 별도의 Service 객체에서 구현하도록 하고 Controller는 Service 객체를 사용
* Service: 비지니스 로직(Business logic)을 수행하는 메소드를 가지고 있는 객체 
  * 보통 하나의 비지니스 로직은 하나의 트랜잭션으로 동작

#### Repository
* 데이터 액세스 메소드를 구현하는 객체, Service는 Repository 객체를 사용

#### Layered Architecture 장점
* 모듈화와 유지보수성: 각 계층을 모듈화하여 코드의 유지보수성을 향상시키고 특정 계층의 변경이 다른 계층에 미치는 영향을 최소화
* 재사용성: 각 계층이 독립적으로 구성되어 있어서 특정 계층을 다른 프로젝트나 모듈에서 재사용하기 쉬움
* 테스트 용이성: 각 계층을 독립적으로 테스트 가능

***
## Reference
[HTTP (HyperText Transfer Protocol) - Basics](https://www3.ntu.edu.sg/home/ehchua/programming/webprogramming/HTTP_Basics.html)
[Properly configuring server MIME types - MDN](https://developer.mozilla.org/en-US/docs/Learn/Server-side/Configuring_server_MIME_types)
[HTTP Cookie - MDN](https://developer.mozilla.org/ko/docs/Web/HTTP/Cookies)
