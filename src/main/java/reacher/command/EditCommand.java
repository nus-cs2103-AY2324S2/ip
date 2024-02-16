package reacher.command;

import reacher.*;
import reacher.task.Task;


public class EditCommand extends Command{
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
}
