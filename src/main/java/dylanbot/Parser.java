package dylanbot;

/**
 * Represents a Parser that makes sense of user inputs
 */
public class Parser {
    private Ui ui;
    private TaskList tl;

    /**
     * Creates a Parser
     *
     * @param ui The Ui to be used
     * @param tl The TaskList that will be referenced and used
     */
    public Parser(Ui ui, TaskList tl) {
        this.ui = ui;
        this.tl = tl;
    }

    /**
     * Parses the provided user input and processes the command accordingly
     *
     * @param command Provided user input
     * @throws DylanBotException If input provided is of an invalid format
     */
    public String parseCommand(String command) throws DylanBotException {
        try {
            switch (command.split(" ")[0]) {
                case "list":
                    return processListCommand();
                case "find":
                    return processFindCommand(command);
                case "mark":
                    return processMarkCommand(command, true);
                case "unmark":
                    return processMarkCommand(command, false);
                case "todo":
                    return processTodoCommand(command);
                case "deadline":
                    return processDeadlineCommand(command);
                case "event":
                    return processEventCommand(command);
                case "delete":
                    return processDeleteCommand(command);
                default:
                    throw new DylanBotException("HEY invalid input! Try again");
            }
        } catch (DylanBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes the List Command
     *
     * @return Complete list of tasks
     * @throws DylanBotException If command is invalid
     */
    public String processListCommand() throws DylanBotException {
        if (tl.isEmpty()) {
            throw new DylanBotException("No tasks to list right now! Add something first la");
        }
        return ui.displayTasks(tl.getTasks());
    }

    /**
     * Processes the find command
     *
     * @param command The command containing the search term
     * @return List of tasks containing the search term
     * @throws DylanBotException If command is invalid
     */
    public String processFindCommand(String command) throws DylanBotException {
        if (command.split(" ").length < 2) {
            throw new DylanBotException("HEY no search term provided");
        }
        String term = command.split(" ")[1];
        return tl.findTerm(term);
    }

    /**
     * Processes either the mark or unmark commands
     *
     * @param command The command to mark or unmark a task
     * @param toMark Whether to mark or unmark
     * @return The marked/unmarked task
     * @throws DylanBotException If command is invalid
     */
    public String processMarkCommand(String command, boolean toMark) throws DylanBotException {
        if (command.split(" ").length < 2) {
            throw new DylanBotException("HEY no index specified for item to mark");
        }
        int idx = Integer.parseInt(command.split(" ")[1]);
        if (idx > tl.getSize() || idx < 0) {
            throw new DylanBotException("HEY index requested is out of bounds");
        }
        return toMark ? tl.mark(idx) : tl.unmark(idx);
    }

    /**
     * Processes the command to create a TodoTask
     *
     * @param command The command containing the Task description
     * @return The created TodoTask
     * @throws DylanBotException If command is invalid
     */
    public String processTodoCommand(String command) throws DylanBotException {
        String desc = command.substring(5);
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY todo description cannot be empty!");
        }
        return tl.createTodo(desc);
    }

    /**
     * Processes the command to create a DeadlineTask
     *
     * @param command The command containing the Task description
     * @return The created DeadlineTask
     * @throws DylanBotException If command is invalid
     */
    public String processDeadlineCommand(String command) throws DylanBotException {
        String[] inputArr = command.split("/by");
        String desc = inputArr[0].substring(9).trim();
        String deadlineStr = inputArr[1].trim();
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY deadline description cannot be empty!");
        }
        if (deadlineStr.isEmpty()) {
            throw new DylanBotException("HEY deadline tasks need deadlines!");
        }
        return tl.createDeadline(desc, deadlineStr);
    }

    /**
     * Processes the command to create a EventTask
     *
     * @param command The command containing the Task description
     * @return The created EventTask
     * @throws DylanBotException If command is invalid
     */
    public String processEventCommand(String command) throws DylanBotException {
        String[] inputArr = command.split("/from|/to");
        String desc = inputArr[0].substring(6).trim();
        String fromStr = inputArr[1].trim();
        String toStr = inputArr[2].trim();
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY event description cannot be empty!");
        }
        if (fromStr.isEmpty()) {
            throw new DylanBotException("HEY event tasks need starting dates!");
        }
        if (toStr.isEmpty()) {
            throw new DylanBotException("HEY event tasks need ending dates!");
        }
        return tl.createEvent(desc, fromStr, toStr);
    }

    /**
     * Processes the command to delete a Task
     *
     * @param command The command containing the Task to delete
     * @return The deleted Task
     * @throws DylanBotException If command is invalid
     */
    public String processDeleteCommand(String command) throws DylanBotException {
        String[] inputArr = command.split(" ");
        int idx = Integer.parseInt(inputArr[1]);
        if (idx > tl.getSize() || idx < 0) {
            throw new DylanBotException("HEY index requested is out of bounds");
        }
        return tl.deleteTask(idx);
    }
 }
