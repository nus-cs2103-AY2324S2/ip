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


    public Ui() {
    }

    public void startConversation() {
        defaultStart();
        String input = scanner.nextLine();
        if (!input.isBlank()) {
            while (isReservedCommand(input)) {
                System.out.println("  Invalid username. " +
                        "Please choose a different username.");
                input = scanner.nextLine();
            }
            username = input.toUpperCase();
        }
        Conversation conversation = new Conversation(username);
        conversation.printDialogue("starter");
    }

    private void defaultStart() {

        System.out.println( "Hi there! I'm " + CHAT_BOT_NAME +
                " :)\n"+ "I'm your AI Assistant and I'm here" +
                " to help you with anything.");

        System.out.println("But first, let's start with " +
                "getting to know you a little bit.\n"  +
                "So, what's your name?");

    }

    public void defaultExit() {

        System.out.println( "Bye bye! Take care and feel free " +
                "to come back if you need any other help!");

    }

    public String getUsername() {
        return username;
    }

    private static boolean isReservedCommand(String username) {
        return reservedCommands.contains(username.toLowerCase());
    }
}

