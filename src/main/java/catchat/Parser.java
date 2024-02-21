package catchat;

/**
 * Parser class deals with making sense of the user command
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;
    private String userInput;

    /**
     * Constructor for Parser
     *
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
    }

    /**
     * Executes user input
     *
     * @param input user input
     */
    public String executeUserInput(String input) {
        CommandType commandType = getCommandType(input);
        userInput = input;
        switch (commandType) {
        case EXIT:
            return handleExit();
        case LIST:
            return handleList();
        case MARK_DONE:
            return handleMarkDone();
        case MARK_UNDONE:
            return handleMarkUndone();
        case FIND:
            return handleFind();
        case HELP:
            return handleHelp();
        case DELETE:
            return handleDelete();
        default: // default is to add task
            return handleAdd();
        }
    }

    /**
     * Exits the program
     *
     * @return String goodbye message
     */
    public String handleExit() {
        return ui.showGoodbye();
    }

    /**
     * Lists all tasks
     *
     * @return String list of tasks
     */
    public String handleList() {
        return taskList.getList();
    }

    /**
     * Marks a task as done
     *
     * @return String task marked as done message
     */
    public String handleMarkDone() {
        try {
            int index = Integer.parseInt(userInput.substring(9).trim()) - 1;
            return taskList.markDone(index);
        } catch (NumberFormatException e) {
            return ui.printInvalidTaskIndex();
        }
    }

    /**
     * Marks a task as undone
     *
     * @return String task marked as undone message
     */
    public String handleMarkUndone() {
        try {
            int index = Integer.parseInt(userInput.substring(11).trim()) - 1;
            return taskList.markUndone(index);
        } catch (NumberFormatException e) {
            return ui.printInvalidTaskIndex();
        }
    }

    /**
     * Finds a task
     *
     * @return String list of tasks
     */
    public String handleFind() {
        try {
            String keyword = userInput.substring(5).trim();
            return taskList.findTasks(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printInvalidKeyword();
        }
    }
    public String handleHelp() {
        return ui.getHelp1();
    }

    /**
     * Deletes a task
     *
     * @return String deletion message
     */
    public String handleDelete() {
        try {
            int index = Integer.parseInt(userInput.substring(6).trim()) - 1;
            return taskList.deleteTask(index);
        } catch (NumberFormatException e) {
            return ui.printInvalidTaskIndex();
        }
    }

    /**
     * Adds a task
     *
     * @return String task added message
     */
    public String handleAdd() {
        return taskList.addTask(userInput);
    }

    /**
     * Get the command type
     *
     * @param input
     * @return
     */
    public CommandType getCommandType(String input) {
        if (input.equals("bye")) {
            return CommandType.EXIT;
        }

        if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark done")) {
            return CommandType.MARK_DONE;
        } else if (input.startsWith("mark undone")) {
            return CommandType.MARK_UNDONE;
        } else if (input.startsWith("find")) {
            return CommandType.FIND;
        } else if (input.startsWith("help")) {
            return CommandType.HELP;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else {
            // Assuming any other input should be treated as adding a task
            return CommandType.ADD;
        }
    }
}

/**
 * Enum for CommandType
 */
enum CommandType {
    EXIT,
    LIST,
    MARK_DONE,
    MARK_UNDONE,
    FIND,
    ADD,
    HELP,
    DELETE
}
