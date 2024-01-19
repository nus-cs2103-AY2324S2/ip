import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printIntroMessage();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            userInput = scanner.nextLine();
            printHorizontalLine();
            switch (userInput) {
                case "bye":
                    break;
                default:
                    System.out.println(userInput);
                    printHorizontalLine();
                    break;
            }
        } while (!userInput.equals("bye"));
        printExitMessage();

    }

    public static void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm SoyBot!");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public static void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("\t_________________________________________________");
    }


}
