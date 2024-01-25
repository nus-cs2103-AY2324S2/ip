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
 * Represent a simple chatbot with the ability to process some user commands.
 */
class Chatbot {
    private final String[] tasks;
    private int taskCount;

    /**
     * Construct a Chatbot and initialize the task storage array.
     */
    public Chatbot() {
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    /**
     * Start the chat.
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
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private void processCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String userInput = scanner.nextLine();

            CommandProcessor commandProcessor = new CommandProcessor(userInput);

            if (commandProcessor.isExitCommand()) {
                break;
            }

            commandProcessor.process();
        }

        scanner.close();
    }

    /**
     * Add a task to the task storage array and displays a message.
     *
     * @param task The task to be added.
     */
    private void addTask(String task) {
        tasks[taskCount++] = task;
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + task);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Display the list of stored tasks.
     */
    private void listTasks() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Represents a command processor for processing user commands and managing tasks.
     */
    private class CommandProcessor {

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
         * Processes the user command, either adding a task or displaying the task list.
         */
        public void process() {
            if (!isExitCommand) {
                if (command.startsWith("list")) {
                    listTasks();
                } else {
                    addTask(command);
                }
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
}
