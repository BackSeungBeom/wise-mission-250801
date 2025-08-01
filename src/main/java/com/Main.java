package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        //ArrayList<String> saying_list = new ArrayList<String>();
        //ArrayList<String> author_list = new ArrayList<String>();
        ArrayList<Quote> quoteList = new ArrayList<>();
        int quoteId = 1;

        System.out.println("==명언 앱==");

        while (true) {
            System.out.print("명령) ");
            String order = sc.nextLine();

            if (order.equals("종료")) {
                break;
            }
            else if (order.equals("등록")) {
                System.out.print("명언 : ");
                String saying = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();

                Quote newQuote = new Quote(quoteId, saying, author);
                quoteList.add(newQuote);

                System.out.println(quoteId + "번 명언이 등록되었습니다.");
                quoteId++;
            } else if (order.equals("목록")) {
                if(quoteList.isEmpty()){
                    System.out.println("등록된 명언이 없습니다.");
                }
                else{
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    for(Quote quote : quoteList){
                            System.out.println(quote.getId() + " / " + quote.getAuthor()+ " / " + quote.getSaying());
                    }
                }
            } else if (order.startsWith("삭제?id=")) {
                try{
                    String[] parts = order.split("=");
                    int index = Integer.parseInt(parts[1]);
                    boolean flag = false;

                    for(int i=0; i<quoteList.size(); i++){
                        if(quoteList.get(i).getId() == index){
                            flag = true;
                            quoteList.remove(i);
                            System.out.println(index + "번 명언이 삭제되었습니다.");
                            break;
                        }
                        if(!flag)
                            System.out.println(index + "번 명언은 존재하지 않습니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID를 숫자로 입력해주세요.");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("명령어 형식이 틀렸습니다. ex) 삭제?id=1");
                }
            } else if (order. startsWith("수정?id=")) {
                try{
                    String[] parts = order.split("=");
                    int index = Integer.parseInt(parts[1]);
                    boolean flag = false;

                    for(int i=0; i<quoteList.size(); i++){
                        if(quoteList.get(i).getId() == index){
                            flag = true;
                            System.out.println("명언(기존) : " + quoteList.get(i).getSaying());
                            System.out.print("명언 : ");
                            String saying = sc.nextLine();
                            quoteList.get(i).setSaying(saying);

                            System.out.println("작자(기존) : " + quoteList.get(i).getAuthor());
                            System.out.print("작가 : ");
                            String author = sc.nextLine();
                            quoteList.get(i).setAuthor(author);

                            System.out.println(index + "번 명언이 수정되었습니다.");
                            break;
                        }
                    }
                    if(!flag)
                        System.out.println(index + "번 명언은 존재하지 않습니다.");
                } catch (NumberFormatException e) {
                    System.out.println("ID를 숫자로 입력해주세요.");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("명령어 형식이 틀렸습니다. ex) 수정?id=1");
                }
            }else{
                System.out.println("잘못된 명령입니다.");
            }
        }
        sc.close();
    }
}
