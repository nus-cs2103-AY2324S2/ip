package util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void printGreetings() {
        printDivider();
        System.out.println("Hello! I'm Edgar.");
        System.out.println("What can I do for you?");
        printDivider();
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public String nextCommand() {
        return this.scanner.nextLine();
    }

    public void printMessage(String message) {
        System.out.println("\t" + message);
    }



}
