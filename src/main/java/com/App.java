package com;

import java.util.Scanner;

/**
 * 애플리케이션의 메인 컨트롤러 클래스입니다.
 * 사용자 명령어를 파싱하고, 그에 따라 {@code QuoteController}의 정적 메서드를 호출하여
 * 명언 앱의 주요 기능을 실행하고 애플리케이션 흐름을 제어합니다.
 */
public class App {
    // 사용자 입력을 받는 스캐너 객체 (정적 필드: 클래스에 한 번만 생성되며 모든 인스턴스가 공유)
    Scanner sc = new Scanner(System.in);

    /**
     * 명언 앱의 주 실행 로직을 포함하는 메서드입니다.
     * 앱 시작 메시지를 출력하고, 사용자로부터 명령을 반복적으로 입력받아 처리합니다.
     */
    public void run() {
        System.out.println("==명언 앱=="); // 앱 시작 메시지 출력
        QuoteController quoteController = new QuoteController();

        // 애플리케이션의 메인 루프: 사용자가 '종료' 명령을 입력할 때까지 반복
        while (true) {
            System.out.print("명령) ");
            String order = sc.nextLine(); // 사용자 명령 입력

            // 각 명령에 따라 QuoteController의 해당 정적 메서드 호출
            if (order.equals("등록")) {
                quoteController.addQuote(); // QuoteController.addQuote() 호출
            } else if (order.equals("목록")) {
                quoteController.listQuotes(); // QuoteController.listQuotes() 호출
            } else if (order.startsWith("삭제?id=")) {
                quoteController.deleteQuote(order); // QuoteController.deleteQuote() 호출
            } else if (order.startsWith("수정?id=")) {
                quoteController.modifyQuote(order); // QuoteController.modifyQuote() 호출
            }
            else if (order.equals("종료")) { // '종료' 명령 처리: 루프를 종료하고 앱 종료 메시지 출력
                System.out.println("명언 앱을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 명령입니다."); // 알 수 없는 명령 처리
            }
        }
        sc.close(); // 앱 종료 시 스캐너 리소스 해제
    }
}