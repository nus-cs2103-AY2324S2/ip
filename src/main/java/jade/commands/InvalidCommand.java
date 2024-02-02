package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;
import jade.ui.Ui;

/**
 * The <code>InvalidCommand</code> object represents an invalid command.
 */
public class InvalidCommand extends Command {
    private JadeException exception; // exception saved for possible further use

    /**
     * Class constructor specifying the JadeException.
     */
    public InvalidCommand(JadeException e) {
        this.exception = e;
    }

    /**
     * @inheriDocs This implementation prints an error message to user
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("\tInput is invalid, please retry.");
    }

    /**
     * @inheriDocs The InvalidCommand does not indicate the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
