package baron.Enums;

public enum TaskType {

    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    TaskType (String command) {
        this.command = command;
    }

    public String getCommand () {
        return command;
    }
}
