package fishstock;

import java.util.Scanner;

/**
 * Encapsulates a Ui.
 * Handles all input/output to the user.
 */
class Ui {
    private final Scanner scanner;

    protected Ui() {
        this.scanner = new Scanner(System.in);
    }

    protected void printMsg(String msg) {
        System.out.println(msg);
    }

    protected void printError(String error) {
        System.out.println(error);
    }

    protected String getInput() {
        return scanner.nextLine();
    }
}
