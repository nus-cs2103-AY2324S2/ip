package duke.actions;

import java.lang.StringBuilder;

import duke.KBot.TaskManager;

public class ListTask extends Command {
    public ListTask() {
    }

    /**
     * Prints the Task List, labels them with numbers
     */
    @Override
    public String execute() {
        if (TaskManager.getTasks().size() == 0) {
            return ("There are no tasks here. Please add a task!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < TaskManager.getTasks().size(); i++) {
                sb.append((i + 1)).append(". ").append(TaskManager.getTasks().get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}
