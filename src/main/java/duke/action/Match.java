package duke.action;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents an action to find and display tasks matching a specific keyword.
 * Implements the Action interface.
 */
public class Match implements Action {
    private TaskList taskList;
    private String keyword;

    /**
     * Constructs a Match object with the specified TaskList.
     *
     * @param taskList The TaskList to search for matching tasks.
     */
    public Match(String keyword, TaskList taskList) {
        this.keyword = keyword.toLowerCase(); // Convert keyword to lowercase
        this.taskList = taskList;
    }

    /**
     * Executes the action to find and display tasks matching a keyword.
     *
     * @return A string with all the tasks with that matches keyword
     */
    @Override
    public String response() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            assert task != null : "Task in TaskList cannot be null";
            if (task.getDescription().toLowerCase().contains(keyword)) { // Convert description to lowercase
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                stringBuilder.append("  ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return stringBuilder.toString();
        }
    }
}

