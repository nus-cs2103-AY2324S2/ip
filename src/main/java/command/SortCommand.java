package command;

import java.util.Comparator;

import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * Sort the tasks in the task list.
 */
public class SortCommand extends Command {
    private TaskList taskList;

    /**
     * Creates an instance of SortCommand.
     */
    public SortCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * {@inheritDocs}
     * List all the tasks in the task list.
     */
    @Override
    public String execute() {
        taskList.getList().sort(new StatusComparator());
        String message = "I have sorted the task list according to the tasks' statuses.";
        return message + "\n\n" + new ListCommand(taskList).execute();
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

    static class StatusComparator implements Comparator<Task> {
        // undone status are on top of the list
        @Override
        public int compare(Task task1, Task task2) {
            if (!task1.checkStatus() && task2.checkStatus()) {
                return -1;

            } else if (task1.checkStatus() && !task2.checkStatus()) {
                return 1;

            } else {
                return task1.getTaskName().compareToIgnoreCase(task2.getTaskName());
            }
        }
    }
}
