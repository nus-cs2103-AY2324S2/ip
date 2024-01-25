import java.util.Scanner;

/**
 * The main class representing my chatbot application.
 */
public class Rochin {
    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        chatbot.start();
    }
}

/**
 * Represents a simple chatbot with the ability to process some user commands.
 */
class Chatbot {

    /**
     * Starts the chat.
     */
    public void start() {
        greetMessage();
        processCommands();
        exitMessage();
    }

    private void greetMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Rochin.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private void processCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String userInput = scanner.nextLine();

            CommandProcessor commandProcessor = new CommandProcessor(userInput);
            commandProcessor.process();

            if (commandProcessor.isExitCommand()) {
                break;
            }
        }

        scanner.close();
    }
}

/**
 * A class responsible for processing user commands and echoing them.
 */
class CommandProcessor {

    private final String command;
    private boolean isExitCommand;

    /**
     * Constructs a CommandProcessor with the given user command.
     *
     * @param command The user command to be processed.
     */
    public CommandProcessor(String command) {
        this.command = command;
    }

    /**
     * Processes the user command, echoing it if not an exit command.
     */
    public void process() {
        if (!isExitCommand) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + command);
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Checks if the user command is an exit command.
     *
     * @return true if the user command is "bye", false otherwise.
     */
    public boolean isExitCommand() {
        isExitCommand = command.equalsIgnoreCase("bye");
        return isExitCommand;
    }
}
