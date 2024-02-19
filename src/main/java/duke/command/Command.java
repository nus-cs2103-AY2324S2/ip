package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class representing a command in the Duke application.
 *
 * <p>The {@code Command} class serves as the base class for all specific command classes
 * in the Duke application. It provides common functionality and structure for executing
 * different commands.</p>
 */
public abstract class Command {
    protected String command;
    protected List<String> arguments;

    /**
    * Constructs a command object with the specified command keyword and arguments.
    *
    * @param command   The command keyword representing the type of command.
    * @param arguments The list of arguments associated with the command.
    */
    public Command(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public abstract TaskList execute(TaskList tasks, Ui ui);

    public String getCommand() {
        return this.command;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Command) {
            Command other = (Command) obj;
            return command.equals(other.command)
                && (arguments == null ? other.arguments == null : arguments.equals(other.arguments));
        } else if (obj instanceof String) {
            String otherString = (String) obj;
            return command.equals(otherString);
        }
        return false;
    }
}
