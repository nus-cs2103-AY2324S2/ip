package charlie.commands;

import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

public class ListCommand extends Command{
    /**
     * executes the List command, which prints out all the tasks in taskList
     * @param taskList - task list loaded at the start of the program.
     * @param ui       - class responsible for user interface interactions
     * @param storage  - class responsible for adding and loading tasks from and into the file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printOutput("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            ui.printOutput((i + 1) + "." + taskList.getTasks().get(i));
        }
        isExit = false;
    }
}