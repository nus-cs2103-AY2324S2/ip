package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;
import task.Task;


/**
 * The ListCommand class represents a command to display the list of tasks in the TaskList.
 * It extends the Command class and implements the execute method to perform the task listing operation.
 * Upon execution, it outputs the list of tasks to the user interface, along with their respective indices.
 *
 * @author wolffe88
 */

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by displaying the tasks in the TaskList that matches the keywords along with their indices.
     *
     * @param tasks   The TaskList that stores the tasks.
     * @param ui      The user interface that outputs to the terminal.
     * @param storage The storage path to store persistent data.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(ui.getBot() + "Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            if (task.getDescription().contains(keyword)) {
                System.out.println("    " + count + ". " + task.toString());
                count++;
            }
        }

    }

}