public class Command {

    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        UNMARK,
        MARK,
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

    public void execute(TaskList tl, Ui ui, Storage storage) {
        this.taskList = tl;
        this.ui = ui;
        this.storage = storage;
    }
}
