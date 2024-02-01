package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Lucifer\nWhat can I do for you?");
        System.out.println("______________________________________________________");
    }

    public void divider() {
        System.out.println("______________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTask(String task) {
        System.out.println(" " + task);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");
    }

}

