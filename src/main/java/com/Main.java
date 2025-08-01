package com;

/**
 * 애플리케이션의 시작 지점을 담당하는 메인 클래스입니다.
 * 앱 구동에 필요한 핵심 로직을 포함하는 {@code App} 객체를 생성하고 실행합니다.
 */
public class Main {
    /**
     * Java 애플리케이션의 주 진입점(Entry Point) 메서드입니다.
     * {@code App} 인스턴스를 초기화하고 애플리케이션의 주 실행 흐름을 시작합니다.
     *
     * @param arg 프로그램 실행 시 전달되는 명령줄 인자 (여기서는 사용되지 않음).
     */
    public static void main(String[] arg) {
        // 애플리케이션의 주요 기능을 캡슐화한 App 클래스의 인스턴스를 생성합니다.
        App app = new App();

        // 생성된 'app' 인스턴스의 run() 메서드를 호출하여 애플리케이션의 실행을 시작합니다.
        app.run();
    }
}