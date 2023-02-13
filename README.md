# Spring

## 스프링이란?
    스프링은 오픈소스 프레임워크다.

### IoC (Inversion of Control)    
    제어의 역전(주도권이 스프링에게 있다)
    
    class - 설계도 ( 캐릭터 )
    object - 실체화가 가능한 것 ( 베이가 )
    instance - 실체화된 것 ( 실제 게임에 있는 베이가 )

    여러 오브젝트를 heap메모리에 띄우면 스프링이 이것들을 관리하는 것을 IoC라고 함

### DI (Dependency Injection)
    의존성 주입
    
    싱글톤 (IoC에 뜬 오브젝트는 공유해서 어느 메소드에서든 사용 가능)
    이렇게 IoC에 뜬 오브젝트를 가져가서 쓰는 것을 DI라고 함

### Filter
    스프링은 엄청나게 많은 필터를 가지고 있다. (검열 기능)
    Tomcat, Spring contianer 등의 문지기 역할
        - Tomcat의 필터: filter(web.xml)
        - Spring Container의 필터: Interceptor(AOP) 
    

### Annotation
    컴파일러가 뭔가를 체킹할 수 있게 힌트를 주는 주석
    Compile Checking

    ex)

    Animal() {
        run();
    }

    Dog 상속 Animal() {
        @Override
        run();
    }
    
    >> @Override를 보고 Animal이라는 부모가 run이라는 메소드가 있을 것이라고 예상
    >> 없는 메소드를 Override하면 컴파일 체킹시에 에러가 남

    스프링에선 annotation으로 객체 생성을 함
    @Component -> 클래스 메모리에 로딩
    @Autowired -> 로딩된 객체를 해당 변수에 집어넣어라, 없으면 null

    리플렉션 : 클래스 내에 어떤게 있는지 분석하고 핸들링하는 기법, 런타임때 분석
    어떤것들:
        - 메서드
        - 필드
        - 어노테이션

### MessageConverter
    스프링은 메세지컨버터를 제공한다. ( Jackson )
    중간언어로 JSON을 사용한다.
    ex) 자바 <--> 파이썬 object 전달 용이하게 하기 위해 JSON 사용함. 
    JSON 데이터를 요청/응답할 때 JSON데이터를 JAVA오브젝트로 바꿔줌

    유니코드 : utf-8 ( 3byte )

    InputSteam : 바이트를 받음 -> 따로 처리해야함
    InputStreamReader : 배열로 여러개 문자 받을 수 있으나 배열의 경우 크기를 정해야 함
    BufferedReader : 가변길이의 문자를 받을 수 있음
    PrintWriter : BufferedWriter랑 똑같은데 print()와 println() 둘 다 제공

    request.getReader() // BufferedReader로 처리해줌
    
    스프링에서:
    @ResponseBody // BufferedWriter 작동
    @RequestBody // BufferedReader 작동

### JPA (Java Persistence Application programming interface)
    영속성(Persistence) Api이다
    RAM - 휘발성 데이터
    HDD(File system) - 비휘발성
    DBMS(DataBase Management System)이 하드디스크에서 데이터 관리함
    

### 부정합
    A가 데이터를 SELECT(10000)하여 10초가 걸리는 연산을 한다.
    B가 10000을 20000으로 업데이트한다.
    A의 연산 중 중간에 다시 SELECT(20000)한다. -> 부정합

    부정합을 막기 위해 @Transactional 한다.
    

    1. Non-Transactional:

        A: SELECT(10000)
        A: 연산.. 
        B: UPDATE(20000)
        A: SELECT(20000) 

    >> 부정합 발생
    

    2. Transactional:

    Transactional(
        A: SELECT(10000)
        A: 연산.. 
        B: UPDATE(20000)
        A: SELECT(10000) 
    )

    >> Transactional중엔 바깥에서 update해도 원본 유지

    부정합의 한계

    새로운 데이터를 INSERT하는 경우는 못막음
    DATA : {월: 10000}
    A : SELECT(월: 10000)
    A : 연산.. 
    B : INSERT(화: 20000)
    A : SELECT(주중데이터의 합: 30000)

    >> 팬텀 현상