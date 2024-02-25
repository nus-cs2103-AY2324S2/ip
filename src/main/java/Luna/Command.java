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
     * Attach the reference tasklist, ui and storage to execute the command on
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    public void execute(TaskList tl, Ui ui, Storage storage) {
        this.taskList = tl;
        this.ui = ui;
        this.storage = storage;
    }
}
