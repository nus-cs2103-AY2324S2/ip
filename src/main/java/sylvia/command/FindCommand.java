package sylvia.command;

import sylvia.state.ProgramState;
import sylvia.task.Task;
import sylvia.task.TaskList;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    public static final String MANUAL = "Usage: find <keyword>\n"
            + "Finds tasks in the task list that contain the keyword.\n" + "The keyword cannot be empty.\n"
            + "Example: find book\n" + "Aliases: f";

    public FindCommand(String body) {
        super(body);
    }

    /**
     * Executes the command. The command finds all tasks that contain the keyword in
     * the list. The state is set to normal.
     *
     * @param list  the list of tasks
     * @param state the program state
     * @return the response
     * @throws EmptyKeywordException if the keyword is empty
     */
    @Override
    public String execute(TaskList list, ProgramState state) throws EmptyKeywordException {
        String body = getBody();
        if (body.equals("")) {
            throw new EmptyKeywordException("Keyword cannot be empty.",
                    "I'm sorry, but I cannot find anything if you don't tell me what to look for.");
        }
        String response = "Here are the matching tasks in your list:\n";
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (!task.contains(body)) {
                continue;
            }
            response += i + 1 + ". " + task + "\n";
            count++;
        }
        if (count == 0) {
            response = "No matching tasks found.";
        }
        state.setNormal();
        return response;
    }
}
