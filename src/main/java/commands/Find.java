package commands;

import objects.TaskList;

/**
 * Represents the 'Find' command, which searches for tasks containing a specific keyword.
 * Implements the Command interface.
 */
public class Find implements Command {
    private final TaskList tasks;
    private final String input;

    /**
     * Constructs a Find command with the specified TaskList and input keyword.
     *
     * @param tasks The TaskList to search for matching tasks.
     * @param input The keyword to search for in task descriptions.
     */
    public Find(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Executes the Find command, searching for tasks containing the specified keyword.
     * Displays the matching tasks with their corresponding indices.
     *
     * @return
     */
    @Override
    public String execute() {
        StringBuilder output = new StringBuilder();
        int taskCount = 0;

        for (int j = 0; j < tasks.size(); j++) {
            String taskString = tasks.get(j).toString();

            if (taskString.contains(input)) {
                taskCount++;
                output.append(String.format("%d. %s\n", taskCount, taskString));
            }
        }

        if (taskCount == 0) {
            return "No tasks found";
        } else {
            output.setLength(output.length() - 1);
            return "Here are the matching tasks in your list:\n" + output;
        }
    }

}
