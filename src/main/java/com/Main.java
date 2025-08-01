package com;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 명언 앱의 주 진입점(Entry Point) 클래스입니다.
 * 사용자 명령(등록, 목록, 수정, 삭제, 종료)을 처리하여 명언을 관리합니다.
 */
public class Main {
    // 앱의 상태를 저장하는 변수들 (실제로는 별도의 클래스에서 관리하는 것이 더 좋지만, 이 규모에서는 Main에서 관리)
    private static ArrayList<Quote> quoteList = new ArrayList<>(); // 명언 객체들을 저장할 리스트
    private static int nextQuoteId = 1; // 새 명언에 부여할 고유 ID, 1부터 시작

    // 사용자 입력을 위한 스캐너 객체 (프로그램 전반에서 사용되므로 static으로 선언)
    private static Scanner sc = new Scanner(System.in);

    /**
     * 프로그램 실행의 시작 지점입니다.
     * 사용자 입력을 받아 각 기능을 수행하는 메인 루프를 관리합니다.
     */
    public static void main(String[] arg) {
        System.out.println("==명언 앱==");

        // 앱의 메인 루프: 사용자 명령을 계속 대기하고 처리
        while (true) {
            System.out.print("명령) ");
            String order = sc.nextLine();

            // '종료' 명령 처리
            if (order.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break; // 루프 종료
            }

            // 명령어에 따라 적절한 메서드 호출
            if (order.equals("등록")) {
                addQuote();
            } else if (order.equals("목록")) {
                listQuotes();
            } else if (order.startsWith("삭제?id=")) {
                deleteQuote(order);
            } else if (order.startsWith("수정?id=")) {
                modifyQuote(order);
            } else {
                System.out.println("잘못된 명령입니다.");
            }
        }
        sc.close(); // 스캐너 리소스 해제
    }

    /**
     * '등록' 명령을 처리합니다.
     * 새로운 명언 내용과 작가를 입력받아 명언 리스트에 추가합니다.
     */
    private static void addQuote() {
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        Quote newQuote = new Quote(nextQuoteId, saying, author);
        quoteList.add(newQuote);

        System.out.println(nextQuoteId + "번 명언이 등록되었습니다.");
        nextQuoteId++; // 다음 ID를 위해 증가
    }

    /**
     * '목록' 명령을 처리합니다.
     * 현재 등록된 모든 명언을 번호, 작가, 명언 순으로 출력합니다.
     */
    private static void listQuotes() {
        if (quoteList.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        } else {
            System.out.println("번호 / 작가 / 명언");
            System.out.println("----------------------");
            for (Quote quote : quoteList) {
                System.out.printf("%d / %s / %s%n", quote.getId(), quote.getAuthor(), quote.getSaying());
            }
        }
    }

    /**
     * '삭제?id=' 명령을 처리합니다.
     * 입력된 ID에 해당하는 명언을 찾아 리스트에서 삭제합니다.
     * Iterator를 사용하여 리스트 순회 중 요소를 안전하게 제거합니다.
     *
     * @param command 사용자 입력 명령 문자열 (예: "삭제?id=5")
     */
    private static void deleteQuote(String command) {
        int deleteId = parseIdFromCommand(command); // 명령에서 ID 추출

        if (deleteId == -1) { // ID 추출 중 오류가 있었던 경우
            System.out.println("명령어 형식이 틀렸습니다. 예: 삭제?id=1");
            return;
        }

        boolean found = false;
        // 역순 반복하여 삭제 시 인덱스 변화 문제 방지
        for (int i = quoteList.size() - 1; i >= 0; i--) {
            if (quoteList.get(i).getId() == deleteId) {
                quoteList.remove(i);
                found = true;
                System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                break;
            }
        }

        if (!found) { // 해당 ID의 명언을 찾지 못한 경우
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }

    /**
     * '수정?id=' 명령을 처리합니다.
     * 입력된 ID에 해당하는 명언을 찾아 내용과 작가를 수정합니다.
     *
     * @param command 사용자 입력 명령 문자열 (예: "수정?id=5")
     */
    private static void modifyQuote(String command) {
        int modifyId = parseIdFromCommand(command); // 명령에서 ID 추출

        if (modifyId == -1) { // ID 추출 중 오류가 있었던 경우
            System.out.println("명령어 형식이 틀렸거나 ID가 유효하지 않습니다. 예: 수정?id=1");
            return;
        }

        boolean found = false;
        for (Quote quote : quoteList) { // 향상된 for문으로 명언 순회
            if (quote.getId() == modifyId) {
                found = true;
                System.out.println("명언(기존) : " + quote.getSaying());
                System.out.print("명언 : ");
                String newSaying = sc.nextLine();
                quote.setSaying(newSaying); // 명언 내용 수정

                System.out.println("작가(기존) : " + quote.getAuthor());
                System.out.print("작가 : ");
                String newAuthor = sc.nextLine();
                quote.setAuthor(newAuthor); // 작가 이름 수정

                System.out.println(modifyId + "번 명언이 수정되었습니다.");
                break; // 찾아서 수정했으므로 반복 중단
            }
        }

        if (!found) { // 해당 ID의 명언을 찾지 못한 경우
            System.out.println(modifyId + "번 명언은 존재하지 않습니다.");
        }
    }

    /**
     * "명령어?id=숫자" 형태의 명령에서 ID(숫자)를 추출하는 헬퍼 메서드입니다.
     * 파싱 오류 발생 시 -1을 반환합니다.
     *
     * @param command ID가 포함된 명령 문자열
     * @return 추출된 ID (정수), 오류 발생 시 -1
     */
    private static int parseIdFromCommand(String command) {
        try {
            String[] parts = command.split("=");
            // 명령어가 "삭제?id="까지만 있고 ID가 없는 경우
            if (parts.length < 2) {
                return -1;
            }
            return Integer.parseInt(parts[1]); // 추출된 문자열을 정수로 변환하여 반환
        } catch (NumberFormatException e) {
            // 숫자로 변환할 수 없는 경우 (예: 삭제?id=abc)
            return -1;
        }
    }
}