import java.util.Scanner;  // Import the Scanner class
public class Duke {
    // class variable
    static String CHATBOT_NAME = "ByteBuddy";
    static String solidLineBreak = "____________________________________________________________";
    static String START_MESSAGE = "Hello! I'm " + CHATBOT_NAME + "\n" + "\tWhat can I do for you?";
    static String BYE_MESSAGE = "Sad to see you leave :(";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // start
        printWithSolidLineBreak(START_MESSAGE);

        // repeating user commands
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                printWithSolidLineBreak(command);
            }
        }

        // bye
        printWithSolidLineBreak(BYE_MESSAGE);
    }

    public static void printWithSolidLineBreak(String s) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t" + s);
        System.out.println("\t" + solidLineBreak);
    }
}
