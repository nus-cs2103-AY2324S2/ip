package tiny;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the user interface of the program.
 */
public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Displays the startup message to the user.
     */
    public void start() {
        System.out.println("   ____________________________________________________________\n");
        System.out.println("   Hello! I'm Tiny!\n"
                + "   What can I do for you?\n");
        System.out.println("   ____________________________________________________________\n");
    }

    /**
     * Reads the user input.
     *
     * @return The input from the user.
     */
    public String readCommand() throws IOException {
        return br.readLine();
    }

    /**
     * Displays the loading error message to the user.
     */
    public void showLoadingError() {
        System.out.println("Unable to load the save file! Creating a new one.");
    }

    /**
     * Displays the error message to the user.
     */

    public void showError(String message) {
        System.out.println(message);
    }

}
