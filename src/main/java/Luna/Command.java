package Luna;

/**
 * Represents a command. The command object has different types and can be executed
 */
public class Command {

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
        EXIT,
        INVALID
    }

    CommandType commandType;
    TaskList taskList;
    Ui ui;
    Storage storage;

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
}
