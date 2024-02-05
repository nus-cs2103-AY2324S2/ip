import java.util.Scanner;
public class Duke {
    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGreetings! I'm Barry.\n" + "\tHow could I assist you?");
        System.out.println("\t____________________________________________________________\n");
    }

    private static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye, see you next time! :)");
        System.out.println("\t____________________________________________________________\n");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // String input
            String inputText = scanner.nextLine().strip();
            if (inputText.equals("bye")) {
                break;
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + inputText);
                System.out.println("\t____________________________________________________________\n");
            }
        }
    }
    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
