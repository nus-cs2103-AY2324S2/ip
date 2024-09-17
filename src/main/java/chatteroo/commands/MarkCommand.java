package chatteroo.commands;

import chatteroo.ChatterooException;
import chatteroo.tasks.TaskList;
import chatteroo.ui.Ui;
import chatteroo.storage.Storage;

/**
 * Marks the specified task as done.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * Constructor for the MarkCommand class.
     * @param taskNum The task number.
     */
    public MarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChatterooException {
        try {
            tasks.markTaskAsDone(taskNum);
            return ui.showMarkDoneTaskResponse() + tasks.getTask(taskNum).toString();
        } catch (IndexOutOfBoundsException e) {
            return ui.showInvalidTaskNumberResponse();
        }
    }
}
