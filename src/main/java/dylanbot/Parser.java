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
     * Processes the provided user input and takes the appropriate follow-up actions
     *
     * @param command Provided user input
     * @throws DylanBotException If input provided is of an invalid format
     */
    public String parseCommand(String command) throws DylanBotException {
        String response = "";
        try {
            switch (command) {
                case "list":
                    return processListCommand(command);
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

    public String processListCommand(String command) throws DylanBotException {
        if (tl.isEmpty()) {
            throw new DylanBotException("No tasks to list right now! Add something first la");
        }
        return ui.displayTasks(tl.getTasks());
    }

    public String processFindCommand(String command) throws DylanBotException {
        if (command.split(" ").length < 2) {
            throw new DylanBotException("HEY no search term provided");
        }
        String term = command.split(" ")[1];
        return tl.findTerm(term);
    }

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

    public String processTodoCommand(String command) throws DylanBotException {
        String desc = command.substring(5);
        if (desc.isEmpty()) {
            throw new DylanBotException("HEY todo description cannot be empty!");
        }
        return tl.createTodo(desc);
    }

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

    public String processDeleteCommand(String command) throws DylanBotException {
        String[] inputArr = command.split(" ");
        int idx = Integer.parseInt(inputArr[1]);
        if (idx > tl.getSize() || idx < 0) {
            throw new DylanBotException("HEY index requested is out of bounds");
        }
        return tl.deleteTask(idx);
    }
 }
