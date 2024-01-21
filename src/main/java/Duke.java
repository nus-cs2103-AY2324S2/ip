import java.util.Scanner;

public class Duke {

    private static void printLine() {
        System.out.println("    +----------------------------------------------------------+");
    }

    private static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println("    Hello! I'm Bob, a personal assistant." );
        System.out.println("    How can I help you?");
        printLine();

        while (true) {
            String input = getUserInput(scanner);
            if (input.equals("bye")) break;

            printLine();
            System.out.println("    Command: " + input);
            printLine();
        }

        printLine();
        System.out.println("    Until next time! Goodbye!");
        printLine();
    }
}
