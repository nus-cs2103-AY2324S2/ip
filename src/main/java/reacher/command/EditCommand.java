package reacher.command;

import reacher.*;
import reacher.task.Task;

/**
 * Command that when executed allows user to mark task done, undone or delete it.
 */
public class EditCommand extends Command{
    /**
     * Executes command to mark a task in tasks done, undone or delete it ans update storage.
     * @param tasks List of tasks
     * @param ui User interface
     * @param storage Local file storage
     * @throws ReacherException If user did not specify done, undone or delete.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        ui.print("Which task number would u like to edit?");
        int num = ui.readInt();
        if (num > tasks.noOfTasks() || num < 1) {
            throw new ReacherException("No such task number");
        }
        Task task = tasks.getTask(num - 1);
        ui.print("Mark Done or Undone or Delete?");
        String change = ui.readString();
        if (change.equalsIgnoreCase("done")) {
            task.markDone();
            ui.print("Task " + num + " marked done");
        } else if (change.equalsIgnoreCase("undone")) {
            task.markNotDone();
            ui.print("Task " + num + " marked Undone");
        } else if (change.equalsIgnoreCase("delete")) {
            tasks.delete(num - 1);
            ui.print("Task " + num + " deleted");
        } else {
            throw new ReacherException("u did not write done, undone or delete.");
        }
        storage.storeList(tasks.getTasks());
    }
    @Override
    public boolean equals(Object object){
        return object instanceof EditCommand;
    }
}
