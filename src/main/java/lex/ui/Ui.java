package lex.ui;

import java.util.Scanner;

public class Ui {
    public static final String SEPERATOR = "____________________________________________________________";

    private final Scanner scanner;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public void welcome() {
        System.out.println(SEPERATOR);
        System.out.print("Hello! I'm lex.Lex\nWhat can I do for you?\n");
    }

    public void print(String message) {
        System.out.println(message);
    }

    public String read() {
        System.out.println(SEPERATOR);
        return scanner.nextLine();
    }

    public void dispose() {
        scanner.close();
    }
}
