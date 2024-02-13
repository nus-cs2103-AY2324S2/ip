package chipchat.ui;

import java.util.Scanner;

/**
 * Represents the UI of the app.
 */
public class Ui {
    private static final String WELCOME_MSG = "Hello! I'm Chipchat \nWhat can I do for you?";
    private static final String EXIT_MSG = "Good bye! Hope to see you again soon!";
    private final Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    public String readUserInput() {
        return reader.nextLine();
    }

    public void showWelcome() {
        System.out.println(WELCOME_MSG);
    }

    public void showBye() {
        System.out.println(EXIT_MSG);
    }

    public void showLine() {
        System.out.println("----------------------------");
    }

    public void showLoadingError() {
        return;
    }

    public void showMsg(String message) {
        System.out.println(message);
    }
}
