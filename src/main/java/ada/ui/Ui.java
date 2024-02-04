package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public Ui() {
    }
    public void showWelcome() {
        System.out.println(
                "Hello! I'm Bob\n" +
                "What can I do for you?");
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public void showResult(String result) {
        System.out.println(result);
    }
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
