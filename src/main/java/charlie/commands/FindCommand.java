package charlie.commands;

import java.util.ArrayList;

import charlie.exceptions.CharlieException;
import charlie.models.Task;
import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    /**
     * constructor for FindCommand
     * @param keyword word to be searched in list of tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * executes a find command,
     * @param taskList - task list loaded at the start of the program.
     * @param ui       - class responsible for user interface interactions
     * @param storage  - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException {
        ArrayList<Task> tasks = taskList.getTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks found with the keyword: " + keyword);
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                response.append(i + 1).append(".").append(task).append("\n");
            }
            System.out.println(response.toString());
        }
    }


}
