package com;

import java.util.ArrayList; // 동적 배열 리스트 사용
import java.util.Scanner;   // 사용자 입력을 위한 스캐너 사용

/**
 * 명언 앱의 컨트롤러 역할을 하는 클래스입니다.
 * 명언 관련 사용자 명령을 처리하고, 애플리케이션의 데이터를 관리하는 정적(static) 메서드들을 포함합니다.
 * 모든 메서드와 필드가 정적이기 때문에, 객체 생성 없이 클래스 이름을 통해 직접 호출하여 사용합니다.
 */
public class QuoteController {
    // 모든 명언 객체를 저장하는 리스트 (애플리케이션 전역에서 공유되는 정적 필드)
    private static final ArrayList<Quote> quoteList = new ArrayList<>();
    // 다음에 부여될 명언 ID (애플리케이션 전역에서 공유되는 정적 필드)
    private static int nextQuoteId = 1;
    // 사용자 입력을 받는 스캐너 객체 (애플리케이션 전역에서 공유되는 정적 필드)
    Scanner sc = new Scanner(System.in);

    /**
     * 새로운 명언을 등록하는 기능을 수행합니다.
     * 사용자로부터 명언 내용과 작가 이름을 입력받아 리스트에 추가합니다.
     */
    public void addQuote() {
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        Quote newQuote = new Quote(nextQuoteId, saying, author);
        quoteList.add(newQuote);

        System.out.printf("%d번 명언이 등록되었습니다.%n", nextQuoteId);
        nextQuoteId++;
    }

    /**
     * 현재까지 등록된 모든 명언 목록을 출력합니다.
     * 명언이 없을 경우 메시지를 표시합니다.
     */
    public void listQuotes() {
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
     * 특정 ID의 명언을 삭제합니다.
     * 명령 문자열에서 ID를 파싱하고, 해당하는 명언을 리스트에서 찾아서 제거합니다.
     *
     * @param command 사용자 입력 명령 문자열 (예: "삭제?id=1" )
     */
    public void deleteQuote(String command) {
        // 명령 문자열에서 ID를 추출합니다.
        int deleteId = parseIdFromCommand(command);

        // ID 파싱 또는 형식 오류 시 처리
        if (deleteId == -1) {
            System.out.println("명령어 형식이 틀렸습니다. 예: 삭제?id=1");
            return;
        }

        boolean found = false;

        // 리스트를 역순으로 반복하여 요소 삭제 시 발생할 수 있는 인덱스 문제를 방지합니다.
        for (int i = quoteList.size() - 1; i >= 0; i--) {
            if (quoteList.get(i).getId() == deleteId) {
                quoteList.remove(i); // 해당 명언 제거
                found = true;
                System.out.printf("%d번 명언이 삭제되었습니다.%n", deleteId);
                break; // 삭제 후 반복 중단
            }
        }

        // 해당 ID의 명언을 찾지 못한 경우 메시지 출력
        if (!found) {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", deleteId);
        }
    }

    /**
     * 특정 ID의 명언 내용을 수정합니다.
     * 명령 문자열에서 ID를 파싱하고, 해당 명언을 찾아 내용과 작가를 새롭게 입력받아 업데이트합니다.
     *
     * @param command 사용자 입력 명령 문자열 (예: "수정?id=1")
     */
    public void modifyQuote(String command) {
        // 명령 문자열에서 ID를 추출합니다.
        int modifyId = parseIdFromCommand(command);

        // ID 파싱 또는 형식 오류 시 처리
        if (modifyId == -1) {
            System.out.println("명령어 형식이 틀렸습니다. 예: 수정?id=1");
            return;
        }

        boolean found = false;
        // 리스트를 순회하며 수정할 명언을 찾습니다.
        for (Quote quote : quoteList) {
            if (quote.getId() == modifyId) {
                found = true;
                System.out.printf("명언(기존) : %s%n", quote.getSaying());
                System.out.print("명언 : ");
                String newSaying = sc.nextLine();
                quote.setSaying(newSaying); // 명언 내용 업데이트

                System.out.printf("작가(기존) : %s%n", quote.getAuthor());
                System.out.print("작가 : ");
                String newAuthor = sc.nextLine();
                quote.setAuthor(newAuthor); // 작가 이름 업데이트

                System.out.printf("%d번 명언이 수정되었습니다.%n", modifyId);
                break; // 수정 후 반복 중단
            }
        }

        // 해당 ID의 명언을 찾지 못한 경우 메시지 출력
        if (!found) {
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", modifyId);
        }
    }

    /**
     * "명령어?id=숫자" 형식의 문자열에서 숫자 ID를 추출하는 내부 헬퍼 메서드입니다.
     * ID가 유효하지 않거나 파싱 오류가 발생하면 -1을 반환합니다.
     *
     * @param command ID가 포함된 명령 문자열
     * @return 추출된 정수 ID, 또는 오류 시 -1
     */
    private int parseIdFromCommand(String command) {
        try {
            String[] parts = command.split("=");
            // 명령어가 "삭제?id="까지만 있고 실제 ID 부분이 없는 경우 처리
            if (parts.length < 2) {
                return -1;
            }
            return Integer.parseInt(parts[1]); // ID 부분을 정수로 변환하여 반환
        } catch (NumberFormatException e) {
            // ID 부분이 숫자가 아닌 경우의 예외 처리
            return -1;
        }
    }
}