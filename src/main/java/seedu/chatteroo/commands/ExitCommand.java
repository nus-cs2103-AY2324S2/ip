package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Prints the exit message.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the ExitCommand class.
     */
    public ExitCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showByeResponse();
    }


}
