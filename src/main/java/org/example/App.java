package org.example;

import org.example.controller.MotivationController;
import org.example.system.SystemController;

import java.util.*;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController(sc);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                systemController.exit();
                break;
            } else if (cmd.equals("add")) {
                motivationController.add();
            } else if (cmd.equals("list")) {
                motivationController.list();
            } else if (cmd.startsWith("delete")) {
                motivationController.delete(cmd);
            } else if (cmd.equals("clear") || cmd.equals("cls")) {
                motivationController.clear();
            } else {
                System.out.println("사용할 수 없는 명령어입니다.");
            }

        }
    }
}