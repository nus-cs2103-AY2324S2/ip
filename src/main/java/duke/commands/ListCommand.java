package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            System.out.println("Yay! You have no tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
    }
}
