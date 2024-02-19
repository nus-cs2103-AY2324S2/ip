package duke.command;

import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.Task;

/**
 * Marks items as undone.
 */
public class UnmarkCommand extends Command {
    private String index;

    /**
     * Constructs UnmarkCommand.
     *
     * @param index The index of item to mark as undone.
     */
    public UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * Runs the command to mark a task as not done.
     *
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for unmark command.
     * @throws BelleException If index specified
     *         does not exist in the list.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws BelleException {
        try {
            assert (Integer.valueOf(this.index) >= 0) : "this index is negative";
            assert (Integer.valueOf(this.index) <= taskList.getSize()) : "this index is too big";

            Task undoTask = taskList.getTask(Integer.valueOf(index) - 1);
            undoTask.setTaskUndone();
            storage.save(taskList.getList());
            return generateUnmarkStatement(undoTask);
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    /**
     * Generates unmark print statement.
     *
     * @param undoTask Task to be unmarked.
     * @return Print statement for unmarked task.
     */
    public String generateUnmarkStatement(Task undoTask) {
        return "--------------------------" + "\n"
                + "OK, I've marked this task as not done yet:" + "\n"
                + undoTask.toString() + "\n" + "--------------------------";
    }
}
