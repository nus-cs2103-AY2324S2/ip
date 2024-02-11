package duke.ui;

import duke.conversation.Conversation;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    // Default names and reserved commands
    private static String username;
    private static final String CHAT_BOT_NAME = "Shiro";
    private static final List<String> reservedCommands = Arrays.asList("list", "mark", "unmark",
            "todo", "deadline", "event", "delete", "delete all");

    // Common ui elements
    public static final String INDENTATION = "      ";
    public static final String LINE = "    --------------------------------------" +
            "---------------------------------------------------";

    public Ui() {
    }

    public void startConversation() {
        defaultStart();
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            while (isReservedCommand(input)) {
                System.out.println(LINE);
                System.out.println(INDENTATION + "  Invalid username. " +
                        "Please choose a different username.");
                System.out.println(LINE);
                input = scanner.nextLine();
            }
            username = input.toUpperCase();
        }
        Conversation conversation = new Conversation(username);
        conversation.printDialogue("starter");
    }

    private void defaultStart() {
        System.out.println(LINE);
        System.out.println(INDENTATION + "Hi there! I'm " + CHAT_BOT_NAME +
                " :)\n" + INDENTATION + "I'm your AI Assistant and I'm here" +
                " to help you with anything.");
        System.out.println(LINE);
        System.out.println(INDENTATION + "But first, let's start with " +
                "getting to know you a little bit.\n" + INDENTATION +
                "So, what's your name?");
        System.out.println(LINE);
    }

    public void defaultExit() {
        System.out.println(LINE);
        System.out.println(INDENTATION + "Bye bye! Take care and feel free " +
                "to come back if you need any other help!");
        System.out.println(LINE);
    }

    public String getUsername() {
        return username;
    }

    private static boolean isReservedCommand(String username) {
        return reservedCommands.contains(username.toLowerCase());
    }
}

