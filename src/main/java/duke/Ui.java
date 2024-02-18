package duke;

/**
 * Manages the user interface for the Duke application. This class handles input
 * and output, displaying
 * greetings, errors, and other messages to the user.
 */
public class Ui {
    protected static String hello = String.format("\tHello! I'm %s\n", Duke.name) + "\tWhat can I do for you?\n";
    private static String goodbye = "\tBye. Hope to see you again soon!\n";

    private Storage storage = null;
    private TaskList taskList = null;

    /**
     * Manages the user interface for the Duke application. This class handles input
     * and output, displaying
     * greetings, errors, and other messages to the user.
     */
    Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Returns a message indicating the user has entered an invalid command.
     * @return A string containing help message as response to invalid commands.
     */
    public static String getInvalidString() {
        return "\tOOPS!!! That is not a valid command!\n"
                + "\tTry the following: \n"
                + "\tlist\n"
                + "\tmark x\n"
                + "\tunmark x\n"
                + "\tdelete x\n"
                + "\tfind xxx\n"
                + "\ttodo xxx\n"
                + "\tdeadline xxx /by yyyy-MM-dd\n"
                + "\tevent xxx /from yyyy-MM-dd /to yyyy-MM-dd";
    }

    public static String getInvalidFormatString() {
        return "\tSpecify a number!";
    }

    public static String getInvalidNumString() {
        return "\tTask number out of range!";
    }

    /**
     * Processes a given string input and returns a response.
     * Executes commands such as listing tasks, marking tasks as complete or incomplete, adding new tasks,
     * deleting tasks, and generates an appropriate response to be displayed in GUI.
     *
     * @param input The user input string to be processed.
     * @return A string containing the response to the input command.
     */
    public String getResponse(String input) {
        Parser.ParsedCommand parsedCommand = Parser.parse(input);
        return processCommand(parsedCommand);
    }

    /**
     * Processes the parsed command and generates the corresponding response by switching
     * on the type of command parsed
     *
     * @param parsedCommand The parsed command object containing the command type and any necessary parameters
     * @return A string response after executing the command
     */
    private String processCommand(Parser.ParsedCommand parsedCommand) {
        StringBuilder output = new StringBuilder();
        int taskIndex = parsedCommand.getTaskNumber() - 1;

        switch (parsedCommand.getCommandType()) {
        case INVALID:
            return handleInvalid(output);
        case INVALID_FORMAT:
            return handleInvalidFormat(output);
        case INVALID_NUM:
            return handleInvalidNum(output);
        case BYE:
            return handleBye(output);
        case LIST:
            return handleList(output);
        case MARK:
            return handleMark(output, taskIndex);
        case UNMARK:
            return handleUnmark(output, taskIndex);
        case DELETE:
            return handleDelete(output, taskIndex, parsedCommand.getInput());
        case FIND:
            return handleFind(output, parsedCommand.getInput());
        case EVENT:
        case TODO:
        case DEADLINE:
        System.out.println("HANDLING TODO");
            return handleTaskAddition(output, parsedCommand);
        default:
            return "";
        }
    }
    
    /**
     * Appends the invalid command response to the output.
     *
     * @param output The StringBuilder to append the response to.
     * @return The string representation of the response.
     */
    private String handleInvalid(StringBuilder output) {
        output.append(Ui.getInvalidString());
        return output.toString();
    }

    /**
     * Appends the invalid number command response to the output.
     *
     * @param output The StringBuilder to append the response to.
     * @return The string representation of the response.
     */
    private String handleInvalidNum(StringBuilder output) {
        output.append(Ui.getInvalidNumString());
        return output.toString();
    }

    /**
     * Appends the invalid format command response to the output.
     *
     * @param output The StringBuilder to append the response to.
     * @return The string representation of the response.
     */
    private String handleInvalidFormat(StringBuilder output) {
        output.append(Ui.getInvalidFormatString());
        return output.toString();
    }

    /**
     * Appends the goodbye message to the output and triggers saving tasks to storage.
     *
     * @param output The StringBuilder to append the goodbye message to.
     * @return The string representation of the goodbye message.
     */
    private String handleBye(StringBuilder output) {
        output.append(Ui.goodbye).append("\n");
        this.storage.saveTasks();
        return output.toString();
    }

    /**
     * Appends a list of all tasks to the output.
     *
     * @param output The StringBuilder to append the list of tasks to.
     * @return The string representation of the list of tasks.
     */
    private String handleList(StringBuilder output) {
        output.append(this.taskList.listTasks());
        return output.toString();
    }

    /**
     * Marks a task as complete and appends the result to the output.
     *
     * @param output The StringBuilder to append the result to.
     * @param taskIndex The index of the task to mark as complete.
     * @return The string representation of the task marked as complete.
     */
    private String handleMark(StringBuilder output, int taskIndex) {
        output.append(this.taskList.getTask(taskIndex).markComplete());
        assert this.taskList.getTask(taskIndex).getStatusNumber() == 1 : "Status number should be 1";
        return output.toString();
    }

    /**
     * Marks a task as incomplete and appends the result to the output.
     *
     * @param output The StringBuilder to append the result to.
     * @param taskIndex The index of the task to mark as incomplete.
     * @return The string representation of the task marked as incomplete.
     */
    private String handleUnmark(StringBuilder output, int taskIndex) {
        output.append(this.taskList.getTask(taskIndex).unmarkComplete());
        assert this.taskList.getTask(taskIndex).getStatusNumber() == 0 : "Status number should be 0";
        return output.toString();
    }

    /**
     * Deletes a task and appends a confirmation message to the output.
     *
     * @param output The StringBuilder to append the confirmation message to.
     * @param taskIndex The index of the task to delete.
     * @param input The original user input for additional context if needed.
     * @return The string representation of the deletion confirmation.
     */
    private String handleDelete(StringBuilder output, int taskIndex, String input) {
        Task deletedTask = this.taskList.getTask(taskIndex);
        this.taskList.deleteTask(taskIndex);
        output.append("\tNoted. I've removed this task:\n\t").append(deletedTask).append("\n");
        return output.toString();
    }

    /**
     * Finds tasks that match the given input and appends them to the output.
     *
     * @param output The StringBuilder to append the found tasks to.
     * @param input The search query to match tasks against.
     * @return The string representation of the found tasks.
     */
    private String handleFind(StringBuilder output, String input) {
        output.append(this.taskList.findTasks(input));
        return output.toString();
    }

    /**
     * Adds a new task based on the parsed command and appends a confirmation message to the output.
     *
     * @param output The StringBuilder to append the confirmation message to.
     * @param parsedCommand The parsed command containing details for creating the new task.
     * @return The string representation of the task addition confirmation.
     */
    private String handleTaskAddition(StringBuilder output, Parser.ParsedCommand parsedCommand) {
        Task task = Parser.createTask(parsedCommand.getCommandType(), parsedCommand.getInput());
        if (task instanceof InvalidTask) {
            output.append(task.getDetails());
        } else {
            this.taskList.addTask(task);
            output.append("\tGot it. I've added this task:\n\t").append(task);
            output.append("\tNow you have ").append(TaskList.storageFill).append(" tasks in the list.\n");
        }
        return output.toString();
    }
    
}
