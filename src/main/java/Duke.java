import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static final String chatbotName = "Sylvia";

    private static final String[] quitCommands = {"bye", "quit", "exit"};
    private static boolean isQuitCommand(String input) {
        for (String command : quitCommands) {
            if (input.toLowerCase().equals(command)) {
                return true;
            }
        }
        return false;
    }

    private static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Cya!");
        System.out.println("____________________________________________________________");
    }

    private static void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        greet();
        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.out.println("Sorry, I don't understand that.");
            }
            if (isQuitCommand(input)) {
                exit();
                break;
            } else {
                echo(input);
            }
        }
    }
}
