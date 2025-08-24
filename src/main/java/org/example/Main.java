package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Container.init();
//        Scanner sc = new Scanner(System.in);

        new App().run();
//        new App(sc).run();

        Container.close();
//        sc.close();

    }
}