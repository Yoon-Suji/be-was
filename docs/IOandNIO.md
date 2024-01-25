# Java IO vs. NIO 패키지
## IO vs. NIO
JAVA에는 입출력(I/O)을 지원하는 io와 nio 두 가지 패키지가 있다.

| JAVA IO | JAVA NIO |
| --- | --- |
| java.io.package | java.nio.package |
| Stream oriented | Buffer oriented |
| Blocking IO operation | Non-Blocking IO operation |
| Channels are not available | Channels are available |
| It deals with data in Stream | It deals with data in Blocks |
| Doesn’t contain the concept of Selectors | Contains the concept of Selectors |

### Stream oriented vs. Buffer oriented

#### **io: Stream-oriented**

![io](https://github.com/Yoon-Suji/be-was/assets/70956926/ecd0eb61-22d7-48cd-b5f5-3c0b681d122c)

- 데이터를 일련의 연속적인 흐름으로 처리
- 단방향 통신이기 때문에 `InputStream`과 `OutputStream`이 각각 필요하다.
    - `InputStream`, `OutputStream`: 바이트 기반 입력 및 출력
    - `Reader`, `Writer`: 문자 기반 입력 및 출력
    - `BufferedReader`, `BufferedWriter`: 버퍼를 사용하여 한 번에 여러 바이트를 읽거나 쓸 수 있음

#### **nio: Buffer-oriented**

![nio](https://github.com/Yoon-Suji/be-was/assets/70956926/6411b642-3792-4e40-bafc-46f1f0b5a2e3)

- `Channel` 및 `Buffer`를 사용하여 입출력을 수행
    - `Channel`: 파일, 소켓 등과의 연결을 나타내는 채널 인터페이스로, 양방향 입출력을 지원
    - `Buffer`: 데이터를 담는 버퍼를 나타내는 클래스로, 입출력 데이터를 저장하고 읽어올 수 있음
- 버퍼를 통해 데이터를 일괄적으로 읽거나 쓸 수 있으며, 비동기 I/O 작업에서도 효율적으로 사용
- 양방향 통신

### Blocking vs. Non-Blocking

#### io: Blocking I/O

- **데이터를 읽거나 쓰는 동안 해당 스레드가 대기 상태(block)가 된다**

#### nio: Non-Blocking I/O

- **스레드가 I/O 작업을 기다리지 않고 다른 작업을 처리할 수 있다**

> 🚨 Java NIO의 모든 클래스가 non-blocking 방식으로 동작하는 것은 아님! NIO 패키지에는 블로킹 및 non-blocking I/O를 지원하는 클래스가 혼합되어 있다

### Selector

- 하나의 스레드로 여러 채널의 I/O 상태를 모니터링 하여 선택할 수 있음
- 효율적인 리소스 사용이 가능

### IO 패키지와 NIO 패키지의 장단점
#### io
- Pros
  - 간편한 사용법
  - Blocking I/O를 사용하므로 개발자가 동기화에 대한 고려를 덜 해도 됨
- Cons:
    - 성능 이슈: Blocking I/O는 여러 연결을 동시에 처리하는 데 어려움이 있음
    - 확장성: 많은 수의 연결을 관리하기 위해서는 많은 수의 스레드가 필요

### nio
- Pros
  - Non-blocking 모델 - 비동기 입출력 지원, 
  - 적은 수의 스레드로 많은 연결을 다룰 수 있어 높은 확장성 가짐
- Cons: 복잡한 API

> ### 💡 IO vs. NIO
>
> ####  IO가 유리한 상황
> 
> * 파일 읽기/쓰기와 같은 단순한 입출력 작업이 필요할 때
> * 클라이언트-서버 애플리케이션에서 연결 수가 많지 않을 때
> * 개발자가 스트림 기반의 입출력 모델에 더 익숙한 경우
>
> #### NIO가 유리한 상황:
> * 대규모 네트워크 연결을 효율적으로 관리해야 할 때 (예: 고성능 서버)
> * 비동기 입출력을 사용하여 자원 사용을 최적화하고자 할 때
> * 파일 시스템의 메타데이터를 다루거나 파일 시스템의 효율적인 접근이 필요할 때 (예: 메모리 맵 파일)


> ### 💡 Sync/Async vs Blocking/Non-Blocking
>
> #### Sync/Async: 요청한 작업에 대해 완료 여부를 신경 써서 작업을 순차적으로 수행할지 아닌지에 대한 관점
> - Sync: 요청과 응답의 순서 보장
> - Async: 요청과 응답의 순서가 보장되지 않음
> 
> #### Blocking/Non-Blocking: 현재 작업이 block(차단, 대기) 되느냐 아니냐에 따라 다른 작업을 수행할 수 있는지에 대한 관점
> - 호출된 함수(callee)가 호출한 함수(caller)에게 제어권을 바로 주느냐 안주느냐

***

## Reference
- [Difference between Java IO and Java NIO - GeeksforGeeks](https://www.geeksforgeeks.org/difference-between-java-io-and-java-nio/)

- [동기비동기-블로킹논블로킹-개념-정리](https://inpa.tistory.com/entry/👩‍💻-동기비동기-블로킹논블로킹-개념-정리)
