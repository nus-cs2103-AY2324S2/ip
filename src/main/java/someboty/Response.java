package someboty;

import java.util.Scanner;

import someboty.Managers.commandManager;
import someboty.Managers.taskManager;

public class Response {
    
    private static final String GREET = "Wassup! I'm someBOTy.\n"
                                    + "What are you here for?";

    private static final String LINE_SEPERATOR = 
        "================================================================";

    private Scanner scanner;
    private commandManager commandCenter;

    // CONSTRUCTOR
    public Response(taskManager taskList) {
        scanner = new Scanner(System.in);
        commandCenter = new commandManager(taskList);
    }
    

    public static void printGreeting() {
        System.out.println(LINE_SEPERATOR);
        System.out.println(GREET);
        System.out.println(LINE_SEPERATOR);
    }

    public static void printExitMessage() {
        System.out.println("Aight. Imma head out.");
        printLine();
    }

    public static void printLine() {
        System.out.println(LINE_SEPERATOR);
    }

    public static void breakLine() {
        System.out.println("");
    }

    public void parseInput() {
        breakLine();
        String input = scanner.nextLine();
        printLine();

        String response = commandCenter.parse(input);

        System.out.println(response);
        printLine();
    }
}
