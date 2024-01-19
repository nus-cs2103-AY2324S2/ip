import java.util.ArrayList;
import java.util.Scanner;

public class ConvoBot {
    private static final String leftPadding = "    ";
    private static void printHorizontalLine(boolean newline) {
        System.out.println(leftPadding + "____________________________________________________________");
        if (newline) System.out.println();
    }
    private static void printWelcomeMsg() {
        System.out.println(leftPadding + " Hello! I'm ConvoBot");
        System.out.println(leftPadding + " What can I do for you?");
    }
    private static void printExitMsg() {
        System.out.println(leftPadding + " Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        printHorizontalLine(false);
        printWelcomeMsg();
        printHorizontalLine(true);

        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            printHorizontalLine(false);
            if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int index = i+1;
                    System.out.println(leftPadding + " " + Integer.toString(index) + ". " + list.get(i));
                }
            } else {
                list.add(userInput);
                System.out.println("     added: " + userInput);
            }
            printHorizontalLine(true);
            userInput = scanner.nextLine();
        }

        printHorizontalLine(false);        
        printExitMsg();
        printHorizontalLine(true);
    }
}
