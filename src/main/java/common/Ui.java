package common;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Reads the user input.
     */
    public String readCommand() {
        return scanner.nextLine().strip();
    }

    /**
     * Shows the start up message upon successful loading of the program.
     */
    public void showWelcome() {
        String welcomeMessage = "Hello! I'm NextGenerationJarvis.\n" 
                + "What can I do for you?";
        System.out.println(welcomeMessage);
        showLine();
    }
    
    /**
     * Shows the separating line for different messages.
     */
    public static void showLine() {
        String line = "________________________________________\n";
        System.out.println(line);
    }
}
