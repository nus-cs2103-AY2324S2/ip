package duke;

/**
 * Represents a Command
 */
public class Command {

    /**
     * Type of the Command
     */
    public Duke.CommandType type;

    /**
     * Stores the arguments required for running of command
     */
    public String[] args;

    /**
     * Constructor for Commands which do not require arguments
     * @param newType Type of the Command
     */
    public Command(Duke.CommandType newType) {
        this.type = newType;
        this.args = new String[]{};
    }

    /**
     * Constructor for Command
     * @param newType Type of the Command
     * @param newArgs Arguments required for running of command
     */
    public Command(Duke.CommandType newType, String[] newArgs) {
        this.type = newType;
        this.args = newArgs;
    }


}
