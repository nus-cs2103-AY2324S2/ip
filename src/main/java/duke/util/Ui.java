package duke.util;

import java.util.Scanner;

/**
 * The class representing the user interactions (prompts).
 * */
public class Ui {

    /**
     * Prints out a horizontal divider.
     * */
    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

    /**
     * Prints out the welcome message upon application launch.
     * */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm TheGiantPeach\nWhat can I do for you?");
        showLine();
    }

    /**
     * Reads the user-entered command.
     *
     * @return The command in string form.
     * */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param errMsg The error message to be displayed.
     * */
    public String showError(String errMsg) {
        return errMsg;
    }
}
