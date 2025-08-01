package com; // 패키지 선언

import java.util.ArrayList; // 동적 리스트(ArrayList) 사용
import java.util.Scanner;   // 사용자 입력(Scanner) 사용

/**
 * 명언 앱의 주 진입점(Entry Point) 클래스입니다.
 * 사용자 명령(등록, 목록, 수정, 삭제, 종료)을 처리하여 명언을 관리합니다.
 */
public class Main {
    /**
     * 프로그램 실행의 시작 지점입니다.
     * 사용자 입력을 받아 각 기능을 수행합니다.
     */
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in); // 사용자 입력 스캐너
        ArrayList<Quote> quoteList = new ArrayList<>(); // 명언 객체들을 저장할 리스트
        int quoteId = 1; // 새 명언에 부여할 고유 ID, 1부터 시작

        System.out.println("==명언 앱==");

        // 앱의 메인 루프: 사용자 명령을 계속 대기하고 처리
        while (true) {
            System.out.print("명령) ");
            String order = sc.nextLine();

            // '종료' 명령 처리
            if (order.equals("종료")) {
                System.out.println("명언 앱을 종료합니다.");
                break;
            }
            // '등록' 명령 처리
            else if (order.equals("등록")) {
                System.out.print("명언 : ");
                String saying = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();

                Quote newQuote = new Quote(quoteId, saying, author);
                quoteList.add(newQuote);

                System.out.println(quoteId + "번 명언이 등록되었습니다.");
                quoteId++; // 다음 ID 준비
            }
            // '목록' 명령 처리
            else if (order.equals("목록")) {
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
            // '삭제?id=' 명령 처리
            else if (order.startsWith("삭제?id=")) {
                int deleteId = -1;
                try {
                    String[] parts = order.split("=");
                    deleteId = Integer.parseInt(parts[1]);

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
                    // 해당 ID의 명언을 찾지 못한 경우
                    if (!found) {
                        System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID는 숫자로 입력해주세요."); // 숫자 변환 오류
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("명령어 형식이 틀렸습니다. 예: 삭제?id=1"); // 명령어 파싱 오류
                }
            }
            // '수정?id=' 명령 처리
            else if (order.startsWith("수정?id=")) {
                int modifyId = -1;
                try {
                    String[] parts = order.split("=");
                    modifyId = Integer.parseInt(parts[1]);

                    boolean found = false;
                    for (Quote quote : quoteList) {
                        if (quote.getId() == modifyId) {
                            found = true;
                            System.out.println("명언(기존) : " + quote.getSaying());
                            System.out.print("명언 : ");
                            String newSaying = sc.nextLine();
                            quote.setSaying(newSaying);

                            System.out.println("작자(기존) : " + quote.getAuthor());
                            System.out.print("작가 : ");
                            String newAuthor = sc.nextLine();
                            quote.setAuthor(newAuthor);

                            System.out.println(modifyId + "번 명언이 수정되었습니다.");
                            break;
                        }
                    }
                    // 해당 ID의 명언을 찾지 못한 경우
                    if (!found) {
                        System.out.println(modifyId + "번 명언은 존재하지 않습니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID는 숫자로 입력해주세요."); // 숫자 변환 오류
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("명령어 형식이 틀렸습니다. 예: 수정?id=1"); // 명령어 파싱 오류
                }
            }
            // 알 수 없는 명령 처리
            else {
                System.out.println("잘못된 명령입니다.");
            }
        }
        sc.close(); // 스캐너 리소스 해제
    }
}