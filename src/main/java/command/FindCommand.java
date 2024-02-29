package command;

import java.util.LinkedList;
import java.util.StringTokenizer;

import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * Finds a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private String keyword;

    /**
     * Creates an instance of FindCommand.
     */
    public FindCommand(TaskList taskList, StringTokenizer st) {
        this.taskList = taskList;
        keyword = st.nextToken();
    }

    /**
     * {@inheritDocs}
     * Finds a task by searching for a keyword.
     */
    @Override
    public String execute() {
        String result = "Here are the matching tasks in your list:\n";
        System.out.print(result);

        LinkedList<Task> tl = taskList.getList();

        int counter = 1;
        for (Task t : tl) {
            if (t.hasKeyword(keyword)) {
                String taskString = counter + ". " + t.toString();
                System.out.println(taskString);
                result += taskString + "\n";

                counter++;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
