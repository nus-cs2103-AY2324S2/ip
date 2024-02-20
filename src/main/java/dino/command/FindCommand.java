package dino.command;

import java.util.ArrayList;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;
import dino.task.Task;

/**
 * Represents a command to find a task from the task list.
 */
public class FindCommand extends Command {
    private String argument;

    public FindCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DinoException {
        String searchKeyword = argument.trim();
        ArrayList<Task> matchingTasks = taskList.findTasksByKeyword(searchKeyword);

        if (matchingTasks.isEmpty()) {
            throw new DinoException("Aww, there are no tasks that contains that keyword.");
        } else {
            StringBuilder printTask = new StringBuilder("Matching tasks:\n");
            for (Task task : matchingTasks) {
                printTask.append(task).append("\n");
            }
            return String.valueOf(printTask);
        }
    }
}
