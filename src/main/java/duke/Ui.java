package duke;

import java.util.Scanner;

public class Ui {
    private static final String NAME = "MR. WONG";
    private static final String LINE = "_________________________________";
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void showError(String message) {
        say(message);
    }

    public void showLoadingError() {
        showError("You do not have a task list saved yet. Let me create one :)");
    }

    public void say(String message) {
        System.out.println(message);
    }

    public void showList(TaskList list) {
        say("Here are the tasks in your list:\n" + list.toString());
    }

    public void showWelcome() {
        System.out.println("Hey man. I'm " + NAME +"\nWhat can I do for you?");
    }
    public void showLine() {
        System.out.println(LINE);
    }

}
