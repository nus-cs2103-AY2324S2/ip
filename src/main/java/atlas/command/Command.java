package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.exception.AtlasException;
public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    public Command(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public abstract void execute() throws AtlasException;
//    public enum CommandType {
//        ADD_TODO, ADD_DEADLINE, ADD_EVENT, LIST, MARK, UNMARK, DELETE, EXIT, TASKS_ON_DATE, INVALID
//    }
//
//    private CommandType commandType;
//    private String[] commandData;
//    private int taskIndex;
//
//    public Command(CommandType commandType) {
//        this.commandType = commandType;
//        this.commandData = null;
//        this.taskIndex = -1;
//    }
//
//    public Command(CommandType commandType, String[] commandData) {
//        this(commandType);
//        this.commandData = commandData;
//    }
//
//    public Command(CommandType commandType, int taskIndex) {
//        this(commandType);
//        this.taskIndex = taskIndex;
//    }
//
//    // Getters
//    public CommandType getCommandType() {
//        return commandType;
//    }
//
//    public String[] getCommandData() {
//        return commandData;
//    }
//
//    public int getTaskIndex() {
//        return taskIndex;
//    }
}
