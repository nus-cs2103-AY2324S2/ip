package belle.command;

import belle.others.BelleException;
import belle.run.Storage;
import belle.run.TaskList;
import belle.run.Ui;
import belle.tasks.Task;

/**
 * Deletes item from list.
 */
public class DeleteCommand extends Command {
    private String index;

    /**
     * Constructs DeleteCommand.
     *
     * @param index The index of item to delete.
     */
    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * Runs the command to delete tasks from tasklist.
     *
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for delete command.
     * @throws BelleException If index specified
     *         does not exist in the list.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws BelleException {
        try {
            assert (Integer.valueOf(index) > 0) : "index cannot be negative";
            assert (Integer.valueOf(this.index) >= 0) : "this index is negative";
            assert (Integer.valueOf(this.index) <= taskList.getSize()) : "this index is too big";

            Task deleteTask = taskList.getTask(Integer.valueOf(index) - 1);
            taskList.removeTask(Integer.parseInt(index) - 1);

            storage.save(taskList.getList());

            return generateDeleteStatement(deleteTask, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new BelleException("This is not a valid number in my task list :(");
        }
    }

    /**
     * Generates delete print statement.
     *
     * @param deleteTask Task to be deleted.
     * @param t Tasklist of program.
     * @return Print statement for deleted task.
     */
    public String generateDeleteStatement(Task deleteTask, TaskList t) {
        return "--------------------------" + "\n"
                + "Noted. I've removed this task:" + "\n"
                + deleteTask.toString() + "\n" + "Now you have "
                + t.getSize() + " tasks in the list." + "\n"
                + "--------------------------";
    }
}
