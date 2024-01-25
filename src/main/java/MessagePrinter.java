/* Handles printing messages  */

import java.util.ArrayList;

public class MessagePrinter {
    // Prints the List message with the given list of strings
    public static void PrintStorage(ArrayList<String> _strings) {
        System.out.println("| Here's everything that I'd stored!");
        int i = 1;
        for(String s : _strings) {
            System.out.println("| " + i + ". " + s);
            i++;
        }
    }

    // Prints the storing message
    public static void PrintStoring() {
        System.out.println("| Sure I'll store it right away!");
    }

    // Prints the invalid message
    public static void PrintInvalid() {
        System.out.println("| I'm not sure what that command is...");
    }

    // Prints the echo of a given string
    public static void Echo(String _input) {
        System.out.println("| Echoing input \n| " + _input);
    }

    // Prints the greeting message when the user enters
    public static void PrintGreeting() {
        String greetingMessage = "| Welcome! I'm JAV\n"
                            + "| How may I sprinkle a bit of happiness into your day today?";

        System.out.println(greetingMessage);
    }

    // Prints the farewell message when the user exits
    public static void PrintExit() {
        String greetingMessage = "| Farewell!\n"
                            + "| May your days be filled with laughter and warmth!";

        System.out.println(greetingMessage);
    }
}