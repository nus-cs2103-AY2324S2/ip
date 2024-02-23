package reacher.command;

import reacher.*;
import reacher.task.Task;
import reacher.ui.MainWindow;

/**
 * Command that when executed allows user to mark task done, undone or delete it.
 */
public class EditCommand extends Command{
    /**
     * Executes command to mark a task in tasks done, undone or delete it ans update storage.
     *
     * @param tasks   List of tasks
     * @param ui      User interface
     * @param storage Local file storage
     * @return
     * @throws ReacherException If user did not specify done, undone or delete.
     */
    @Override
    public String execute(String input, TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        int num = 0;
        try {
            num = Integer.parseInt(Parser.getInfo(input, 1));
        } catch (NumberFormatException e) {
            throw new ReacherException("Pls give a number.");
        }
        if (num > tasks.noOfTasks() || num < 1) {
            throw new ReacherException("No such task number");
        }
        Task task = tasks.getTask(num - 1);
        String change = Parser.getInfo(input, 2);
        if (change.equalsIgnoreCase("done")) {
            task.markDone();
            storage.storeList(tasks.getTasks());
            return ("Task " + num + " marked done");
        } else if (change.equalsIgnoreCase("undone")) {
            task.markNotDone();
            storage.storeList(tasks.getTasks());
            return ("Task " + num + " marked Undone");
        } else if (change.equalsIgnoreCase("delete")) {
            tasks.delete(num - 1);
            storage.storeList(tasks.getTasks());
            return ("Task " + num + " deleted");
        } else {
            throw new ReacherException("u did not write done, undone or delete.");
        }
    }
    @Override
    public boolean equals(Object object){
        return object instanceof EditCommand;
    }
}
