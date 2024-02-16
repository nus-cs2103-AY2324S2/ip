package common;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        showLine();
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
