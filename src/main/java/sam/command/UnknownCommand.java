package sam.command;

import sam.Storage;
import sam.TaskList;
import sam.Ui;
import sam.SamException;

/**
 * Represents a command that is not recognised.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        throw new SamException("Hey, please choose from the following commands\n"
                + "if you want to add task, please use todo, deadline or event\n"
                + "if you want to mark or unmark task, please use mark or unmark\n"
                + "if you want delete a task, please use delete\n"
                + "if you want to view the existing task list, please enter list.");
    }
}