package skyler.main;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "------------------------------------------------------------";

    public static void printGreeting(String chatbotName) {
        System.out.println("   /\\_/\\");
        System.out.println("  ( o.o ) Hello! I'm " + chatbotName);
        System.out.println("   > ^ < What can I do for you?");
        System.out.println(LINE);
    }

    public static String getUserInput(Scanner scanner) {
        System.out.print("You: ");
        String userInput = scanner.nextLine();
        System.out.println(LINE);
        return userInput;
    }

    public static void printByeMessage() {
        System.out.println("Skyler: Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void printErrorMessage(String message) {
        System.out.println("Skyler: Woof, " + message);
        System.out.println(LINE);
    }
}
