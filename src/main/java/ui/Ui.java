package ui;

import java.util.Scanner;

/**
 * Handles text output to the CLI for Cal
 */
public class Ui {
    /**
     * show welcome banner
     */
    public static void showWelcome() {
        String name = "Cal";
        String logo = "  ____     _     _ \n"
                    + " / ___|   / \\   | | \n"
                    + "| |      / - \\  | | \n"
                    + "| |___  / --- \\ | |___ \n"
                    + (" \\____|/_/   \\_\\|_____|");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(logo);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * read command entered by user
     *
     * @param sc The scanner that takes in user input
     */
    public String readCommand(Scanner sc) {
        String input = sc.nextLine().toLowerCase();
        return input;
    }

    public void showErrorMsg(Exception e) {
        System.out.println(e.getMessage());
    }
}
