public class Command {
    public enum CommandType {
        ADD_TODO, ADD_DEADLINE, ADD_EVENT, LIST, MARK, UNMARK, DELETE, EXIT, TASKS_ON_DATE, INVALID
    }

    private CommandType commandType;
    private String[] commandData;
    private int taskIndex;

    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.commandData = null;
        this.taskIndex = -1;
    }

    public Command(CommandType commandType, String[] commandData) {
        this(commandType);
        this.commandData = commandData;
    }

    public Command(CommandType commandType, int taskIndex) {
        this(commandType);
        this.taskIndex = taskIndex;
    }

    // Getters
    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getCommandData() {
        return commandData;
    }

    public int getTaskIndex() {
        return taskIndex;
    }
}
