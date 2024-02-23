package lindi.commands;

import java.util.ArrayList;

import lindi.storage.Storage;
import lindi.task.TaskList;

/**
 * Represents a command that executes multiple commands upon execution
 */
public class MassOpsCommand extends Command {
    private final ArrayList<Command> commands;

    /**
     * Creates a command that executes multiple commands in a mass operation.
     */
    public MassOpsCommand() {
        this.commands = new ArrayList<>();
    }

    /**
     * Adds a command to the list of commands to be executed in the mass operation
     *
     * @param command to be added to the list of commands to be executed
     */
    public void addCommand(Command command) {
        assert command != null : "Command cannot be null";
        this.commands.add(command);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Executes each command in the list of commands and appends their status messages to a single status message.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : this.commands) {
            command.execute(tasks, storage);
            stringBuilder.append(command.status()).append("\n\n");
        }
        this.statusMsg = stringBuilder.toString();
    }
}
