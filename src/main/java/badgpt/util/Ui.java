package badgpt.util;

import badgpt.exceptions.BadException;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printException(BadException e) {
        System.err.println("____________________________________________________________\n"
                + e + "\n____________________________________________________________");
    }

    public void sayHi(String name) {
        printLine();
        System.out.println("Hello! I'm " + name + ".\n" + "What can I do for you?");
        printLine();
    }

    public void sayBye() {
        printLine();
        System.out.println("Smell ya later");
        printLine();
    }

    public String read() {
        return sc.nextLine();
    }
}
