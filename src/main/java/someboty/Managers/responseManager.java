package someboty.Managers;

import java.util.Scanner;

/**
 * responseManager handles the input and responses to be printed onto the interface.
 * 
 * This class mainlt deal with how the messages are printed, such as having a line separator
 * above and below the input response, while the commandManager deals with the response of 
 * each input.
 */
public class responseManager {
    
    private static final String GREET = "Wassup! I'm someBOTy.\n"
                                    + "What are you here for?";

    private static final String LINE_SEPERATOR = 
        "================================================================";

    private Scanner scanner;
    private commandManager commandCenter;

    /**
     * Constructor for responseManager.
     * @param commandCenter commandCenter to forward inputs and receive response.
     */
    public responseManager(commandManager commandCenter) {
        scanner = new Scanner(System.in);
        this.commandCenter = commandCenter;
    }
    

    /**
     * Prints out a greeting message from the bot to user.
     */
    public static void printGreeting() {
        System.out.println(LINE_SEPERATOR);
        System.out.println(GREET);
        System.out.println(LINE_SEPERATOR);
    }

    /**
     * Prints out a farewell message from the bot to user.
     */
    public static void printExitMessage() {
        System.out.println("Aight. Imma head out.");
        printLine();
    }

    /**
     * Prompts user for input and forwards it to the command center.
     * Then receives a response from it and prints it onto interface.
     */
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
