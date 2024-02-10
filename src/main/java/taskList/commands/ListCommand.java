package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;
import tasklist.tasks.Task;

/** Represents a command to list all tasks in the tasklist. Implements the Command interface. */
public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            int itemNumber = i + 1;
            Task listItem = taskList.getTask(i);
            System.out.println(itemNumber + ". " + listItem);
        }
    }
}
