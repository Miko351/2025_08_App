package org.example.controller;

import org.example.Motivation;

import java.util.*;

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

        if (cmd.split(" ").length > 2) {
            System.out.println("delete 뒤에는 숫자 하나만 입력 가능합니다.");
            return;
        }

        int id = -1;

        try {
            id = Integer.parseInt(cmd.split(" ")[1]);
        } catch (NumberFormatException e) {
            System.out.println("delete 뒤에는 숫자만 입력 가능합니다.");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("사용할 수 없는 명령어입니다.");
            return;
        }

        int foundIndex = -1;

        Motivation foundMotivation = null;
        for (int i = 0; i < motivationList.size(); i++) {
            foundMotivation = motivationList.get(i);
            if (foundMotivation.getId() == id) {
//                System.out.println(foundMotivation.toString());
                foundIndex = i;
            }
        }

        if (foundMotivation == null) {
            System.out.println(id + "번 글은 없습니다.");
            return;
        }

        motivationList.remove(foundIndex);
        System.out.println(id + "번 글이 삭제되었습니다.");

    }

    public void newdelete(String cmd) {
        String[] cmdBits = cmd.split("\\?"); // 예) "delete" 와 "id=1"

        String actionMethod = cmdBits[0]; // "delete"

        String[] paramBits = cmdBits[1].split("="); // 예) "id" 와 "1"

        Map<String, String> params = new HashMap<>();
        String key = paramBits[0];
        String value = paramBits[1];
        params.put(key, value);

        System.out.println("actionMethod: " + actionMethod);
        System.out.println("paramBits: " + paramBits[0] + paramBits[1]);
        System.out.println("params: " + params);

        int id = Integer.parseInt(params.get("id"));

        int foundIndex = -1;

        Motivation foundMotivation = null;
        for (int i = 0; i < motivationList.size(); i++) {
            foundMotivation = motivationList.get(i);
            if (foundMotivation.getId() == id) {
//                System.out.println(foundMotivation.toString());
                foundIndex = i;
            }
        }

        if (foundMotivation == null) {
            System.out.println(id + "번 글은 없습니다.");
            return;
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
