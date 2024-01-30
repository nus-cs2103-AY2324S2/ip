import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class Tyrone {
    private final Ui UI;
    private TaskList taskList = new TaskList();
    private enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE
    }
    private static final HashMap<String, Command> cmdMap = new HashMap<>();

    public static void main(String[] args) {
        new Tyrone().run();
    }

    public Tyrone() {
        this.UI = new Ui();
        this.taskList = new TaskList();
    }

    public void run() {
        start();
        runUntilExit();
        this.UI.outputByeMessage();
        System.exit(0);
    }

    public void start() {
        try {
            cmdMap.put("bye", Command.BYE);
            cmdMap.put("list", Command.LIST);
            cmdMap.put("todo", Command.TODO);
            cmdMap.put("deadline", Command.DEADLINE);
            cmdMap.put("event", Command.EVENT);
            cmdMap.put("mark", Command.MARK);
            cmdMap.put("unmark", Command.UNMARK);
            cmdMap.put("delete", Command.DELETE);
            this.UI.outputWelcomeMessage();
            taskList.loadTaskListFromFile();
        } catch (StorageHelperException e) {
            this.UI.outputFailedInitMessage();
            throw new RuntimeException(e);
        }
    }

    public void runUntilExit() {
        boolean isActive = true;
        while (isActive) {
            try {
                // extract the command
                String rawUserCommand = this.UI.getRawUserCommand();
                String cmdStr = !rawUserCommand.contains(" ") ? rawUserCommand : rawUserCommand.substring(0, rawUserCommand.indexOf(" "));
                if (!cmdMap.containsKey(cmdStr)) {
                    throw new IncorrectCommandException(Messages.MESSAGE_NOT_EXIST_CMD);
                }

                Command cmd = cmdMap.get(cmdStr);
                // execute cmd logic respectively
                switch (cmd) {
                case BYE:
                    isActive = false;
                    break;
                case LIST:
                    handleListCommand();
                    break;
                case TODO:
                    handleTodoCommand(rawUserCommand);
                    break;
                case DEADLINE:
                    handleDeadlineCommand(rawUserCommand);
                    break;
                case EVENT:
                    handleEventCommand(rawUserCommand);
                    break;
                case MARK:
                    handleMarkCommand(rawUserCommand);
                    break;
                case UNMARK:
                    handleUnmarkCommand(rawUserCommand);
                    break;
                case DELETE:
                    handleDeleteCommand(rawUserCommand);
                    break;
                default:
                    throw new IncorrectCommandException(Messages.MESSAGE_NOT_EXIST_CMD);
                }
                taskList.saveTaskListToFile();
            } catch (IncorrectCommandException e) {
                this.UI.outputExceptionToUser(e.getMessage());
            } catch (StorageHelperException e) {
                this.UI.outputExceptionToUser(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private void handleListCommand() {
        final String MESSAGE_LIST = String.format(Messages.MESSAGE_LIST, taskList);
        this.UI.outputResultToUser(MESSAGE_LIST);
    }

    private void handleTodoCommand(String input) throws IncorrectCommandException {

        // validate general input
        if (isEmptyParam(input))
            throw new IncorrectCommandException(Messages.MESSAGE_TODO_EMPTY_DESC);

        // extract input param
        ToDo newItem = new ToDo(input.substring(5));
        this.taskList.addItem(newItem);
        this.UI.outputResultToUser(String.format(Messages.MESSAGE_ADD_TASK, newItem, this.taskList.getListSize()));
    }

    private void handleDeadlineCommand(String input) throws IncorrectCommandException {
        // validate general input
        if (isEmptyParam(input) || !input.contains("/by"))
            throw new IncorrectCommandException(Messages.MESSAGE_DEADLINE_INCORRECT);

        // extract input params
        Deadline newItem = generateDeadlineFromInput(input.substring(9));
        this.taskList.addItem(newItem);
        this.UI.outputResultToUser(String.format(Messages.MESSAGE_ADD_TASK, newItem, this.taskList.getListSize()));
    }

    private Deadline generateDeadlineFromInput(String input) throws IncorrectCommandException {
        String[] params = input.split("/by");

        // check if enough params in the first place
        if (params.length != 2) {
            throw new IncorrectCommandException(Messages.MESSAGE_DEADLINE_INCORRECT);
        }

        String description = params[0].trim();
        String deadlineDateTimeStr = params[1].trim();

        // validate params
        if (description.isEmpty() || deadlineDateTimeStr.isEmpty()) {
            throw new IncorrectCommandException(Messages.MESSAGE_DEADLINE_INCORRECT);
        }

        try {
            return new Deadline(description, new DateTime(deadlineDateTimeStr));
        } catch (DateTimeParseException e) {
            throw new IncorrectCommandException(Messages.MESSAGE_DEADLINE_INCORRECT);
        }
    }

    private void handleEventCommand(String input) throws IncorrectCommandException {

        // validate general input
        if (isEmptyParam(input) || !input.contains("/from") || !input.contains("/to"))
            throw new IncorrectCommandException(Messages.MESSAGE_EVENT_INCORRECT);

        // extract input params
        Event newItem = generateEventFromInput(input.substring(6));
        this.taskList.addItem(newItem);
        this.UI.outputResultToUser(String.format(Messages.MESSAGE_ADD_TASK, newItem, this.taskList.getListSize()));
    }

    private Event generateEventFromInput(String input) throws IncorrectCommandException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(0, fromIndex).trim();
        String startDateTime = fromIndex + 5 < toIndex ? input.substring(fromIndex + 5, toIndex).trim() : "";
        String endDateTime = toIndex + 3 < input.length() - 1 ? input.substring(toIndex + 3).trim() : "";

        // validate params
        if (description.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty())
            throw new IncorrectCommandException(Messages.MESSAGE_EVENT_INCORRECT);

        try {
            return new Event(description, new DateTime(startDateTime), new DateTime(endDateTime));
        } catch (DateTimeParseException e) {
            throw new IncorrectCommandException(Messages.MESSAGE_EVENT_INCORRECT);
        }
    }

    private void handleMarkCommand(String input) throws IncorrectCommandException {
        final String MESSAGE_INCORRECT_MARK_INDEX = String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "mark");
        // validate general input
        if (isEmptyParam(input)) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_MARK_INDEX);
        }

        // extract input params
        try {
            String param = input.substring(4).trim();
            int index = Integer.parseInt(param) - 1;
            if (taskList.getListSize() <= 0 || index < 0 || index >= taskList.getListSize())
                throw new IncorrectCommandException(MESSAGE_INCORRECT_MARK_INDEX);
            taskList.markItemDone(index);
            this.UI.outputResultToUser(String.format(Messages.MESSAGE_MARK, taskList.getItem(index)));
        } catch (NumberFormatException e) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_MARK_INDEX);
        }
    }

    private void handleUnmarkCommand(String input) throws IncorrectCommandException {
        final String MESSAGE_INCORRECT_UNMARK_INDEX = String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "unmark");
        // validate general input
        if (isEmptyParam(input)) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_UNMARK_INDEX);
        }

        try {
            String param = input.substring(6).trim();
            int index = Integer.parseInt(param) - 1;
            if (this.taskList.getListSize() == 0 || index < 0 || index >= this.taskList.getListSize())
                throw new IncorrectCommandException(MESSAGE_INCORRECT_UNMARK_INDEX);
            this.taskList.unmarkItemDone(index);
            this.UI.outputResultToUser(String.format(Messages.MESSAGE_UNMARK, this.taskList.getItem(index)));
        } catch (NumberFormatException e) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_UNMARK_INDEX);
        }
    }

    private void handleDeleteCommand(String input) throws IncorrectCommandException {
        final String MESSAGE_INCORRECT_DELETE_INDEX = String.format(Messages.MESSAGE_INCORRECT_COMMAND_INDEX, "delete");
        // validate general input
        if (isEmptyParam(input)) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_DELETE_INDEX);
        }

        try {
            String param = input.substring(6).trim();
            int index = Integer.parseInt(param) - 1;
            if (this.taskList.getListSize() == 0 || index < 0 || index >= this.taskList.getListSize())
                throw new IncorrectCommandException(MESSAGE_INCORRECT_DELETE_INDEX);
            Task delItem = this.taskList.deleteItem(index);
            this.UI.outputResultToUser(String.format(Messages.MESSAGE_DELETE, delItem, this.taskList.getListSize()));
        } catch (NumberFormatException e) {
            throw new IncorrectCommandException(MESSAGE_INCORRECT_DELETE_INDEX);
        }
    }

    private static boolean isEmptyParam(String input) {
        return !input.trim().contains(" ");
    }
}
