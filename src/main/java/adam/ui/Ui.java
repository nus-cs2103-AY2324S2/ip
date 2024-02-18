package adam.ui;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public Ui() {
    }
    public String showWelcome() {
        return "Hello! I'm Adam\n" +
                "What can I do for you?";
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public String showResult(String... lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showError(String errorMessage) {
        return errorMessage;
    }
}
