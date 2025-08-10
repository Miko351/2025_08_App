package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        int listId = 0;

        List<Motivation> motivationList = new ArrayList<>();

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== 명언 앱 종료 ==");
                break;
            } else if (cmd.equals("add")) {
                System.out.print("명언 : ");
                String body = sc.nextLine().trim();
                System.out.print("저자 : ");
                String author = sc.nextLine().trim();
                ++listId;

                Motivation addMotiv = new Motivation(listId, body, author);

                motivationList.add(addMotiv);

                System.out.println(listId + "번 명언이 등록되었습니다.");
            } else if (cmd.equals("list")) {
                System.out.println("=".repeat(50));
                System.out.println("     번호     /     저자     /     명언     ");

                if (motivationList.size() == 0) {
                    System.out.println("등록된 명언이 없습니다.");
                } else {

                    Collections.reverse(motivationList);
                    for (Motivation m : motivationList) {
                        System.out.println(m.getId() + "     "
                                + m.getBody() + "     "
                                + m.getAuthor() + "     ");
                    }
                }

            } else {
                System.out.println("사용할 수 없는 명령어입니다.");

            }
        }
    }
}