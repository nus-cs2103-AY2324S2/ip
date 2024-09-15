package belle.command;

import belle.others.BelleException;
import belle.run.Storage;
import belle.run.TaskList;
import belle.run.Ui;
import belle.tasks.Task;

/**
 * Marks items as done.
 */
public class MarkCommand extends Command {
    private String index;

    /**
     * Constructs MarkCommand.
     *
     * @param index The index of item to mark as done.
     */
    public MarkCommand(String index) {
        this.index = index;
    }

    /**
     * Runs the command to mark a task as done.
     *
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for Mark command.
     * @throws BelleException If index specified
     *         does not exist in the list.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws BelleException {
        try {
            assert (Integer.valueOf(this.index) >= 0) : "this index is negative";
            assert (Integer.valueOf(this.index) <= taskList.getSize()) : "this index is too big";

            Task doingTask = taskList.getTask(Integer.valueOf(index) - 1);
            doingTask.setTaskDone();

            storage.save(taskList.getList());

            return generateMarkStatement(doingTask);
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    /**
     * Generates mark print statement.
     *
     * @param doingTask Task to be marked done.
     * @return Print statement for marked task.
     */
    public String generateMarkStatement(Task doingTask) {
        return "--------------------------" + "\n"
                + "Nice! I have marked this task as done:" + "\n"
                + doingTask.toString() + "\n" + "--------------------------";
    }
}
