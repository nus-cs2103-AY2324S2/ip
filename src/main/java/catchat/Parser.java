package catchat;

/**
 * Parser class deals with making sense of the user command
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;

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

        switch (commandType) {
        case EXIT:
            return ui.showGoodbye();
        case LIST:
            return taskList.getList();
        case MARK_DONE:
            try {
                int index = Integer.parseInt(input.substring(9).trim()) - 1;
                return taskList.markDone(index);
            } catch (NumberFormatException e) {
                return ui.printInvalidTaskIndex();
            }
        case MARK_UNDONE:
            try {
                int index = Integer.parseInt(input.substring(11).trim()) - 1;
                return taskList.markUndone(index);
            } catch (NumberFormatException e) {
                return ui.printInvalidTaskIndex();
            }
        case FIND:
            try {
                String keyword = input.substring(5).trim();
                return taskList.findTasks(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                return ui.printInvalidKeyword();
            }
        case HELP:
            return ui.getHelp();
        case DELETE:
            try {
                int index = Integer.parseInt(input.substring(6).trim()) - 1;
                return taskList.deleteTask(index);
            } catch (NumberFormatException e) {
                return ui.printInvalidTaskIndex();
            }
        default: // default is to add task
            return taskList.addTask(input);
        }
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
