package duke.commands;

import duke.DukeException;

/**
 * The Command class represents a generic command in the Duke chatbot.
 * It is an abstract class that serves as the base class for specific command types.
 */
public abstract class Command {
    protected String[] cmd;

    public Command(String[] cmd) {
        this.cmd = cmd;
    }

    public abstract String execute();

    protected void validateArgs() {
        if (cmd.length < 2) {
            throw new DukeException(
                    String.format(DukeException.NON_EMPTY_DESC, cmd[0]));
        }
    }
}
