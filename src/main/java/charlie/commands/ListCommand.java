package charlie.commands;

import charlie.storage.Storage;
import charlie.storage.TaskList;

public class ListCommand extends Command {
    /**
     * executes the List command, which prints out all the tasks in taskList
     * @param taskList - task list loaded at the start of the program.
     * @param storage  - class responsible for adding and loading tasks from and into the file
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder responseBuild = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            responseBuild.append(i + 1).append(".").append(taskList.getTasks().get(i)).append("\n");
        }
        isExit = false;
        return responseBuild.toString();
    }
}
