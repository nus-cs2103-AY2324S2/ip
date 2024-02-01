public class Command {
    public enum CommandType {
        LIST, MARK, UNMARK, ADD_TODO, ADD_DEADLINE, ADD_EVENT, DELETE, BYE, INVALID
    }

    private CommandType commandType;
    private String secondPart;
    private int taskIndex;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public Command(CommandType commandType, String secondPart) {
        this(commandType);
        this.secondPart = secondPart;
    }

    public Command(CommandType commandType, int taskIndex) {
        this(commandType);
        this.taskIndex = taskIndex;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getParts() {
        return secondPart;
    }

    public int getTaskIndex() {
        return taskIndex;
    }
}
