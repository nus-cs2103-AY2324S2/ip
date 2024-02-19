package kokbot;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return type == command.type && Arrays.equals(args, command.args);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(type);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }


}
