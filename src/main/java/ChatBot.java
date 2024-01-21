import java.util.Scanner;

/**
 * ChatBot encapsulates the behaviour of a Chatbot.
 *
 * @author Titus Chew
 */
public class ChatBot {
    private final String chatBotName, INDENT = "    ";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Stores the user's tasks
     */
    TaskList userList = new TaskList();

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

        Action userAction = InputParser.parseInput(scanner.nextLine());
        while (userAction.getCommand() != Command.BYE) {
            executeAction(userAction);
            userAction = InputParser.parseInput(scanner.nextLine());
        }

        exit();
    }

    /**
     * Execute the command with the supplied arguments.
     * @param action the action with the command and arguments
     */
    private void executeAction(Action action) {
        switch (action.getCommand()) {
            case ADD:
                addTask(action);
                break;
            case LIST:
                listTasks();
                break;
            case MARK:
                markTask(action);
                break;
            case UNMARK:
                unmarkTask(action);
                break;
            case TODO:
                addTodo(action);
                break;
            case DEADLINE:
                addDeadline(action);
                break;
            case EVENT:
                addEvent(action);
                break;
        }
    }

    /**
     * Add a task to the user's list.
     * @param action the action of the task to perform
     */
    private void addTask(Action action) {
        userList.addTask(action.findDefaultArgument());
        printAddTaskMessage();
    }

    /**
     * Add a to-do to the user's list.
     * @param action the action of the task to perform
     */
    private void addTodo(Action action) {
        userList.addTodo(action.findDefaultArgument());
        printAddTaskMessage();
    }

    /**
     * Add a deadline task to the user's list.
     * @param action the action of the task to perform
     */
    private void addDeadline(Action action) {
        String name = action.findDefaultArgument(),
                by = action.findArgument("by");
        userList.addDeadline(name, by);
        printAddTaskMessage();
    }

    /**
     * Add an event to the user's list.
     * @param action the action of the task to perform
     */
    private void addEvent(Action action) {
        String name = action.findDefaultArgument(),
                from = action.findArgument("from"),
                to = action.findArgument("to");
        userList.addEvent(name, from, to);
        printAddTaskMessage();
    }

    /**
     * Prints the message when a task is added.
     */
    private void printAddTaskMessage() {
        printMessage("Got it. I've added this task:\n  "
                + userList.getNewestTask() + "\n"
                + "Now you have " + userList.size() + " tasks in the list"
        );
    }

    /**
     * Prints the user's list.
     */
    private void listTasks() {
        printMessage("Here are the tasks in your list:\n" + userList.toString());
    }

    /**
     * Marks and prints the task.
     * @param action the action of the task to perform
     */
    private void markTask(Action action) {
        int index = Integer.parseInt(action.findDefaultArgument()) - 1;
        userList.markTask(index);
        printMessage("Nice! I've marked this task as done:\n  " + userList.getTask(index));
    }

    /**
     * Unmarks and prints the task.
     * @param action the action of the task to perform
     */
    private void unmarkTask(Action action) {
        int index = Integer.parseInt(action.findDefaultArgument()) - 1;
        userList.unmarkTask(index);
        printMessage("OK, I've marked this task as not done yet:\n  " + userList.getTask(index));
    }
}
