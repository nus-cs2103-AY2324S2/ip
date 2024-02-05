package command;

import roland.Storage;
import task.Task;
import roland.TaskList;
import roland.Ui;

public class ListCommand extends Command{

    /**
     * Executes the command by displaying the list of tasks in the TaskList along with their indices.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(ui.getBot() + "Here are your tasks:");
        for (int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i-1);
            System.out.println("    " + i + ". " + task.toString());
        }

    }

}
