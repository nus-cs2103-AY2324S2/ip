package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("loading error occurred");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm MicroManager");
        System.out.println("What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
