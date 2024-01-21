import java.util.Scanner;

/**
 * Class running the main chatbot program.
 */
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke.greet();
        Duke.echo(sc);
        Duke.exit();
    }

    /**
     * Greet behaviour of chatbot.
     */
    private static void greet() {
        System.out.println("Hello, I'm Ted.");
        System.out.println("What can I do for you today?");
    }

    /**
     * Echo behaviour of chatbot.
     * Chatbot will output user input word for word.
     * Behaviour will terminate only if user inputs a case-insensitive "bye" command.
     *
     * @param sc Scanner reading user inputs.
     */
    private static void echo(Scanner sc) {
        while (true) {
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(msg);
        }
    }

    /**
     * Exit behaviour of chatbot.
     */
    private static void exit() {
        System.out.println("Bye. See you again.");
    }
}