package command;

import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * List all the tasks in the task list.
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Creates an instance of ListCommand.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * {@inheritDocs}
     * List all the tasks in the task list.
     */
    @Override
    public String execute() {
        String result = "Here are the tasks in your list:\n";
        System.out.print(result);

        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i);
            String taskString = i + ". " + t.toString();
            System.out.println(taskString);
            result += taskString + "\n";
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
