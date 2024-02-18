package duke;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Ui {
    static final String CHATBOT_NAME = "Echo";
    static final int CELL_WIDTH = 70;

    /**
     * Prints the banner.
     */
    public void printBanner() {
        // Banner somewhat inspired from SageMath's banner.
        System.out.println("┌" + new String(new char[CELL_WIDTH - 2]).replace("\0", "─") + "┐");
        // Java 8 compatible, for now.
        String firstLine = CHATBOT_NAME + ", the chatbot. Version 0.0.0.";
        System.out.println(String.format("│ %-" + (CELL_WIDTH - 4) + "s │", firstLine));
        String secondLine = "Using Java " + System.getProperty("java.version") + ".";
        System.out.println(String.format("│ %-" + (CELL_WIDTH - 4) + "s │", secondLine));
        System.out.println("└" + new String(new char[CELL_WIDTH - 2]).replace("\0", "─") + "┘");
    }

    /**
     * Asks the user for a prompt.
     *
     * @throws NoSuchElementException if the user presses Ctrl-D.
     * @return String of the user's input.
     */
    public String askForPrompt() {
        System.out.print(">>> ");
        String result = sc.nextLine();
        // We print out the prompt if input is redirected to make the expected output more readable.
        if (System.console() == null) {
            System.out.println(result);
        }
        return result;
    }

    /**
     * Prints a file that indicates the storage file is corrupted.
     *
     * @param e The exception object.
     */
    public void printCorruptedFileMessage(DukeException e) {
        System.err.println("Stored tasks file is corrupted: " + e.getMessage());
        System.err.println("Deleted the file.");
    }

    Scanner sc = new Scanner(System.in);
}
