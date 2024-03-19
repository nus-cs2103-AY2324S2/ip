package anna;

import java.util.function.Consumer;

/**
 * The Command interface represents an executable command.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param reply the consumer to reply with the execution result
     * @param list the task list on which the command operates
     * @return true if the command is successfully executed, otherwise false
     * @throws AnnaException if an exception occurs during command execution
     */
    public boolean execute(Consumer<String> reply, TaskList list) throws AnnaException;
}
