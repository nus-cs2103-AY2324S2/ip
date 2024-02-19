package Kokbot;

/**
 * Represents a Command
 */
public class Command {

    /**
     * Type of the Command
     */
    public Kokbot.CommandType type;

    /**
     * Stores the arguments required for running of command
     */
    public String[] args;

    /**
     * Constructor for Commands which do not require arguments
     *
     * @param newType Type of the Command
     */
    public Command(Kokbot.CommandType newType) {
        this.type = newType;
        this.args = new String[]{};
    }

    /**
     * Constructor for Command
     *
     * @param newType Type of the Command
     * @param newArgs Arguments required for running of command
     */
    public Command(Kokbot.CommandType newType, String[] newArgs) {
        this.type = newType;
        this.args = newArgs;
    }


}
