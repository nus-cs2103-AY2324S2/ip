package baron.enums;

/**
 * Enum for the different task types, which is used for determining user input commands
 * as well as datbaase file names.
 */
public enum TaskType {

    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String command;

    TaskType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
