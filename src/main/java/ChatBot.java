import java.util.Scanner;

/**
 * ChatBot encapsulates the behaviour of a Chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot {
    private final String chatBotName;
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
        Printer.printMessages(
                "Hello! I'm " + chatBotName + "!",
                "What can I do for you?"
        );
    }

    /**
     * Greets the user when exiting the application.
     */
    private void exit() {
        Printer.printMessages("Bye! Hope to see you again soon!");
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
            case LIST:
                listCommand();
                break;
            case MARK:
                markCommand(action);
                break;
            case UNMARK:
                unmarkCommand(action);
                break;
            case ADD_TODO:
                addTodoCommand(action);
                break;
            case ADD_DEADLINE:
                addDeadlineCommand(action);
                break;
            case ADD_EVENT:
                addEventCommand(action);
                break;
            case INVALID:
                invalidCommand();
                break;
        }
    }

    /**
     * Add a to-do to the user's list.
     * @param action the action of the task to perform
     */
    private void addTodoCommand(Action action) {
        String name = action.findDefaultArgument();

        // Validate arguments
        if (name == null) {
            handleMissingArgument(action.getCommand(), "name");
            return;
        }

        userList.addTodo(name);
        handleAddSuccess();
    }

    /**
     * Add a deadline task to the user's list.
     * @param action the action of the task to perform
     */
    private void addDeadlineCommand(Action action) {
        String name = action.findDefaultArgument(),
                by = action.findArgument("by");

        // Validate arguments
        if (name == null) {
            handleMissingArgument(action.getCommand(), "name");
            return;
        }
        if (by == null) {
            handleMissingArgument(action.getCommand(), "by");
            return;
        }

        userList.addDeadline(name, by);
        handleAddSuccess();
    }

    /**
     * Add an event to the user's list.
     * @param action the action of the task to perform
     */
    private void addEventCommand(Action action) {
        String name = action.findDefaultArgument(),
                from = action.findArgument("from"),
                to = action.findArgument("to");

        // Validate arguments
        if (name == null) {
            handleMissingArgument(action.getCommand(), "name");
            return;
        }
        if (from == null) {
            handleMissingArgument(action.getCommand(), "from");
            return;
        }
        if (to == null) {
            handleMissingArgument(action.getCommand(), "to");
            return;
        }

        userList.addEvent(name, from, to);
        handleAddSuccess();
    }

    /**
     * Prints the message when a task is added.
     */
    private void handleAddSuccess() {
        Printer.printMessages(
                "Got it. I've added this task:",
                "    " + userList.getNewestTask(),
                "Now you have " + userList.size() + " task(s) in the list."
        );
    }

    /**
     * Prints the user's list.
     */
    private void listCommand() {
        Printer.printMessages(
                "Here are the tasks in your list:",
                userList.toString()
        );
    }

    /**
     * Marks and prints the task.
     * @param action the action of the task to perform
     */
    private void markCommand(Action action) {
        // Validate arguments
        if (action.findDefaultArgument() == null) {
            handleMissingArgument(action.getCommand(), "index");
            return;
        }

        int index = Integer.parseInt(action.findDefaultArgument()) - 1;
        userList.markTask(index);
        Printer.printMessages(
                "Nice! I've marked this task as done:",
                "    " + userList.getTask(index)
        );
    }

    /**
     * Unmarks and prints the task.
     * @param action the action of the task to perform
     */
    private void unmarkCommand(Action action) {
        // Validate arguments
        if (action.findDefaultArgument() == null) {
            handleMissingArgument(action.getCommand(), "index");
            return;
        }

        int index = Integer.parseInt(action.findDefaultArgument()) - 1;
        userList.unmarkTask(index);
        Printer.printMessages(
                "Ok, I've marked this task as not done yet:",
                "    " + userList.getTask(index)
        );
    }

    /**
     * Handles the case when the command is invalid.
     */
    private void invalidCommand() {
        Printer.printMessages("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Handles the case when arguments are missing in a command.
     */
    private void handleMissingArgument(Command command, String missingArg) {
        Printer.printMessages(
                "OOPS!!! The argument <" + missingArg + "> of " + command.name + " must be present!",
                "    Usage: `" + command.usageHint + "`"
        );
    }
}
