package tiny;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Displays the startup message.
     */
    public void start() {
        System.out.println("   ____________________________________________________________\n");
        System.out.println("   Hello! I'm Tiny!\n" +
                "   What can I do for you?\n");
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
     * Displays the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading the data!");
    }

    /**
     * Displays the error message.
     */

    public void showError(String message) {
        System.out.println(message);
    }

}
