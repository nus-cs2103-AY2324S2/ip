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
 * Represent a chatbot with the ability to store, display, mark, and unmark tasks.
 */
class Chatbot {
    private final Task[] tasks;
    private int taskCount;

    /**
     * Construct a Chatbot and initialize the task storage array.
     */
    public Chatbot() {
        this.tasks = new Task[100];
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
     * @param description The description of the task to be added.
     */
    private void addTask(String description) {
        Task newTask = new Task(description);
        tasks[taskCount++] = newTask;
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + newTask.getDescription());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Display the list of stored tasks with their status.
     */
    private void listTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks[i];
            System.out.println("     " + (i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Mark the specified task as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    private void markTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            Task taskToMark = tasks[taskIndex - 1];
            taskToMark.markAsDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       [" + taskToMark.getStatusIcon() + "] " + taskToMark.getDescription());
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Mark the specified task as not done.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    private void unmarkTaskAsDone(int taskIndex) {
        if (isValidTaskIndex(taskIndex)) {
            Task taskToUnmark = tasks[taskIndex - 1];
            taskToUnmark.markAsNotDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       [" + taskToUnmark.getStatusIcon() + "] " + taskToUnmark.getDescription());
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Check if the task index is valid.
     *
     * @param taskIndex The index of the task to be checked.
     */
    private boolean isValidTaskIndex(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= taskCount) {
            return true;
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Invalid task index. Please enter a valid index.");
            System.out.println("    ____________________________________________________________");
            return false;
        }
    }

    /**
     * Represent a command processor for processing user commands and managing tasks.
     */
    private class CommandProcessor {

        private final String command;
        private boolean isExitCommand;

        /**
         * Construct a CommandProcessor with the given user command.
         *
         * @param command The user command to be processed.
         */
        public CommandProcessor(String command) {
            this.command = command;
        }

        /**
         * Processe the user command.
         */
        public void process() {
            if (!isExitCommand) {
                if (command.startsWith("list")) {
                    listTasks();
                } else if (command.startsWith("mark")) {
                    int taskIndex = getTaskIndex("mark");
                    markTaskAsDone(taskIndex);
                } else if (command.startsWith("unmark")) {
                    int taskIndex = getTaskIndex("unmark");
                    unmarkTaskAsDone(taskIndex);
                } else {
                    addTask(command);
                }
            }
        }

        private int getTaskIndex(String operation) {
            try {
                String[] splitCommand = command.split("\\s+");
                return Integer.parseInt(splitCommand[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Invalid " + operation + " command. Please enter a valid task index.");
                System.out.println("    ____________________________________________________________");
                return -1;
            }
        }

        /**
         * Check if the user command is an exit command.
         *
         * @return true if the user command is "bye", false otherwise.
         */
        public boolean isExitCommand() {
            isExitCommand = command.equalsIgnoreCase("bye");
            return isExitCommand;
        }
    }
}

/**
 * Represent a task with a description and a status.
 */
class Task {

    private final String description;
    private boolean isDone;

    /**
     * Construct a task with the given description and set its status to not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon representing whether the task is done or not done.
     *
     * @return The status icon ('X' if done, ' ' if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }
}
