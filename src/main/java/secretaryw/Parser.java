package secretaryw;

import java.util.Scanner;

/**
 * Parses user input for the SecretaryW application.
 */
public class Parser {
    private Scanner scanner;

    /**
     * Constructs a new Parser object.
     */
    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retrieves the next command entered by the user.
     *
     * @return Array of strings representing the parsed command.
     */
    public String[] getNextCommand() {
        return scanner.nextLine().trim().split("\\s+", 2);
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }
}
