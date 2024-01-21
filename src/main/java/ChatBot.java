import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.StringBuilder;

/**
 * ChatBot encapsulates the behaviour of a Chatbot.
 *
 * @author Titus Chew
 */
public class ChatBot {
    private final String chatBotName;
    private final Scanner scanner = new Scanner(System.in);
    private static final String
            COMMAND_BYE = "bye",
            COMMAND_LIST = "list",
            COMMAND_ADD = "add",
            INDENT = "    ";

    /**
     * Possible user commands
     */
    private enum Command {
        /**
         * Ends the chat
         */
        BYE,
        /**
         * List the stored text
         */
        LIST,
        /**
         * Adds item to stored text
         */
        ADD,
        /**
         * Invalid command
         */
        INVALID
    }

    /**
     * Stores the task entered by the user.
     */
    private final List<Task> userList = new ArrayList<>();

    /**
     * Class constructor.
     * @param chatBotName the name of the chatbot
     */
    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * Greets the user when entering the application.
     */
    private void greet() {
        printMessage(String.format(
                "Hello! I'm %s!\n" +
                "What can I do for you?",
                chatBotName));
    }

    /**
     * Greets the user when exiting the application.
     */
    private void exit() {
        printMessage("Bye! Hope to see you again soon!");
    }

    /**
     * Inserts a horizontal line.
     */
    private void insertLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Prints an indented message to the console.
     * @param message the message to print in the console
     */
    private void printMessage(String message) {
        insertLine();
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(INDENT + line);
        }
        insertLine();
    }

    /**
     * Reads and parses user input for commands.
     */
    public void run() {
        greet();

        String[] parsedInput = parseInput(scanner.nextLine());
        Command userCommand = parseCommand(parsedInput[0]);
        while (userCommand != Command.BYE) {
            executeCommand(userCommand, parsedInput[1]);

            parsedInput = parseInput(scanner.nextLine());
            userCommand = parseCommand(parsedInput[0]);
        }

        exit();
    }

    /**
     * Parse the input string into it's command and arguments.
     * @param input the console input
     * @return An array containing the command as the first element and argument as the second element.
     */
    private String[] parseInput(String input) {
        if (input.startsWith(COMMAND_BYE)) return new String[] {COMMAND_BYE, ""};
        if (input.startsWith(COMMAND_LIST)) return new String[] {COMMAND_LIST, ""};
        return new String[] {COMMAND_ADD, input};
    }

    /**
     * Parses the command into an Enum.
     * @param command the command from the parsed input
     * @return the command
     */
    private Command parseCommand(String command) {
        switch (command) {
            case COMMAND_BYE:
                return Command.BYE;
            case COMMAND_LIST:
                return Command.LIST;
            case COMMAND_ADD:
                return Command.ADD;
        }
        return Command.INVALID;
    }

    /**
     * Execute the command with the supplied arguments.
     * @param command the command
     * @param argument the argument
     */
    private void executeCommand(Command command, String argument) {
        switch (command) {
            case ADD:
                userList.add(new Task(argument));
                printMessage("added: " + argument);
                break;
            case LIST:
                printUserList();
                break;
        }
    }

    /**
     * Prints the user's list.
     */
    private void printUserList() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < userList.size(); i++) {
            message.append(String.format("%d. %s\n", i + 1, userList.get(i)));
        }
        printMessage(message.toString());
    }
}
