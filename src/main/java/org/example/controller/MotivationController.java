package org.example.controller;

import org.example.Motivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MotivationController {

    private Scanner sc;
    private int listId = 0;
    private List<Motivation> motivationList = new ArrayList<>();

    public MotivationController(Scanner sc) {
        this.sc = sc;
    }

    public void add() {
        System.out.print("명언 : ");
        String body = sc.nextLine().trim();
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        ++listId;

        Motivation addMotiv = new Motivation(listId, body, author);

        motivationList.add(addMotiv);

        System.out.println(listId + "번 명언이 등록되었습니다.");
    }

    public void list() {
        System.out.println("=".repeat(50));
        System.out.println("     번호     /     명언     /     저자     ");

        if (motivationList.size() == 0) {
            System.out.println("등록된 명언이 없습니다.");
        } else {
            List<Motivation> copy = new ArrayList<>(motivationList);
            Collections.reverse(copy);

            for (Motivation m : copy) {
                if (m.getBody().length() < 5) {
                    System.out.println(m.getId() + "               "
                            + m.getBody() + "          "
                            + m.getAuthor() + "     ");
                } else {
                    System.out.println(m.getId() + "               "
                            + m.getBody().substring(0, 5) + " ..." + "          "
                            + m.getAuthor() + "     ");
                }
            }
        }
    }

    public void delete(String cmd) {

        int id = Integer.parseInt(cmd.split(" ")[1]);

        int foundIndex = -1;
        Motivation m = null;
        for (int i = 0; i < motivationList.size(); i++) {
            m = motivationList.get(i);
            if (m.getId() == id) {
//                System.out.println(m.toString());
                foundIndex = i;
            }
        }

        motivationList.remove(foundIndex);
        System.out.println(id + "번 글이 삭제되었습니다.");
        
    }

    public void clear() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");  // CMD·PowerShell
            } else {
                pb = new ProcessBuilder("clear");             // Linux·macOS
            }
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            // 실패 시 대체
            for (int i = 0; i < 100; i++) System.out.println();
        }
    }
}
