package duke;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user by reading user input and printing to console.
 */
public class Ui {
    private Scanner scanner;
    private boolean isActive;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     * The Ui object is activated upon construction.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
    }

    /**
     * Sends system messages to the console.
     *
     * @param strs the messages to be sent
     */
    public void sendSystemMessage(String... strs) {
        for (String s: strs) {
            System.out.println(s);
        }
    }

    /**
     * Checks if the Ui object is active.
     *
     * @return true if the Ui object is active, false otherwise
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Exits the Ui object by closing the scanner and deactivating it.
     */
    public void exit() {
        this.scanner.close();
        this.isActive = false;
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return the trimmed input string
     */
    public String readNextLine() {
        return this.scanner.nextLine().trim();
    }


}
