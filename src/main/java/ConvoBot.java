import java.util.Scanner;

public class ConvoBot {
    private static void printHorizontalLine(boolean newline) {
        System.out.println("    ____________________________________________________________");
        if (newline) System.out.println();
    }
    private static void printWelcomeMsg() {
        System.out.println("     Hello! I'm ConvoBot");
        System.out.println("     What can I do for you?");
    }
    private static void printExitMsg() {
        System.out.println("     Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        printHorizontalLine(false);
        printWelcomeMsg();
        printHorizontalLine(true);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            printHorizontalLine(false);
            System.out.println("     " + userInput);
            printHorizontalLine(true);
            userInput = scanner.nextLine();
        }

        printHorizontalLine(false);        
        printExitMsg();
        printHorizontalLine(true);
    }
}
