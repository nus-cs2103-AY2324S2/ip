package linus;

import java.util.Scanner;

/**
 * Represents all the interactions with the user.
 */
public class Ui {
    /**
     * Returns the next line of user input read from the Scanner.
     *
     * @param scanner Scanner object to read input.
     * @return Next line's String representation.
     */
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Prints out the input String message.
     *
     * @param message Input String message to be printed out.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
