package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;
import jade.ui.Ui;

/**
 * The <code>InvalidCommand</code> object represents an invalid command.
 */
public class InvalidCommand extends Command {
    private final JadeException exception; // exception saved for possible further use

    /**
     * Class constructor specifying the JadeException.
     */
    public InvalidCommand(JadeException e) {
        this.exception = e;
    }

    /**
     * @inheritDoc This implementation prints an error message to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String result = "Input is invalid, please retry.";
        return result;
    }

    /**
     * @inheritDoc The InvalidCommand does not indicate the exit of the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
