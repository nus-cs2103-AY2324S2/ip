package someboty.Managers;

import java.util.Scanner;

public class responseManager {
    
    private static final String GREET = "Wassup! I'm someBOTy.\n"
                                    + "What are you here for?";

    private static final String LINE_SEPERATOR = 
        "================================================================";

    private Scanner scanner;
    private commandManager commandCenter;

    // CONSTRUCTOR
    public responseManager(taskManager taskList) {
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

    public void parseInput() {
        breakLine();
        String input = scanner.nextLine();
        printLine();

        String response = commandCenter.parse(input);

        System.out.println(response);
        printLine();
    }

    protected static void printLine() {
        System.out.println(LINE_SEPERATOR);
    }

    protected static void breakLine() {
        System.out.println("");
    }
}
