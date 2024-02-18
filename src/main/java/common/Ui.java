package common;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine().strip();
    }

    public void showWelcome() {
        String welcomeMessage = "Hello! I'm NextGenerationJarvis.\n" +
                "What can I do for you?";
        System.out.println(welcomeMessage);
        showLine();
    }
    
    public static void showLine() {
        String line = "________________________________________\n";
        System.out.println(line);
    }
}
