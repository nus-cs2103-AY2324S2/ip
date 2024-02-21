package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command indicating the termination of the Duke application.
 * When executed, it writes tasks to storage and generates a goodbye message.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand with the specified input.
     *
     * @param input The input string associated with the command.
     */
    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        storage.writeToFile(tasks);
        return ui.showByeMessage();
    }

    /**
     * Checks if the command is a bye command.
     *
     * @return True, indicating that this is a bye command.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
