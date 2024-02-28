package luna;

/**
 * Represents a command. The command object has different types and can be executed
 */
public class Command {

    /**
     * Represents the type of Command Object
     */
    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        UNMARK,
        MARK,
        DELETE,
        SAVE,
        LOAD,
        FIND,
        SNOOZE,
        EXIT,
        INVALID
    }

    private CommandType commandType;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public Command(CommandType ct) {
        this.commandType = ct;
    }

    /**
     * Attach the reference task list, ui and storage to execute the command on
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        this.taskList = tl;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Getter function for commandType
     * @return commandType
     */
    public CommandType getCommandType() {
        return commandType; }

    /**
     * Getter function for Task List
     * @return task list
     */
    public TaskList getTaskList() {
        return this.taskList; }

    public Boolean isListType() {
        return this.commandType == CommandType.LIST;
    }
    public Boolean isTodoType() {
        return this.commandType == CommandType.TODO;
    }
    public Boolean isDeadlineType() {
        return this.commandType == CommandType.DEADLINE;
    }
    public Boolean isEventType() {
        return this.commandType == CommandType.EVENT;
    }
    public Boolean isMarkType() {
        return this.commandType == CommandType.MARK;
    }
    public Boolean isUnmarkType() {
        return this.commandType == CommandType.UNMARK;
    }
    public Boolean isSaveType() {
        return this.commandType == CommandType.SAVE;
    }
    public Boolean isLoadType() {
        return this.commandType == CommandType.LOAD;
    }
    public Boolean isFindType() {
        return this.commandType == CommandType.FIND;
    }
    public Boolean isSnoozeType() {
        return this.commandType == CommandType.SNOOZE;
    }

    public Boolean isExitType() {
        return this.commandType == CommandType.EXIT;
    }

    public Boolean isInvalidType() {
        return this.commandType == CommandType.INVALID;
    }


}

