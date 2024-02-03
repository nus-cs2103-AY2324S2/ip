package duke;

import java.util.Scanner;

public class Ui {
    private static final String NAME = "Arctic";
    private static final Character BORDER_CHAR = '_';
    private static final Integer BORDER_LEN = 60;

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(String.valueOf(BORDER_CHAR).repeat(BORDER_LEN));
    }

    public void showWelcome() {
        this.showMessage(String.format("Hello! I'm %s\nWhat can I do for you?", NAME));
    }

    public void showError(String error) {
        this.showMessage(error);
    }

    public void showMessage(String message) {
        this.showLine();
        System.out.println(message);
        this.showLine();
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
}
