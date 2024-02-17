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
        int i = 1;
        StringBuilder output = new StringBuilder();

        for (int j = 0; j < tasks.size(); j++) {
            String s = tasks.get(j).toString();

            if (s.contains(" " + input)) {
                output.append(String.format("%d. %s\n", i, s));
                i++;
            }
        }

        if (output.length() > 0) {
            output.setLength(output.length() - 1);
        }

        if (output.length() == 0) {
            return "No tasks found";
        } else {
            return "Here are the matching tasks in your list:\n" + output;
        }
    }
}
