package asher.commands;

import asher.tasks.Task;
import asher.tasks.TaskList;
import asher.tasks.Deadline;
import asher.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The SortCommand class represents a command to sort the deadline tasks in the task list by their due dates.
 */
public class SortCommand extends Command {

    /**
     * Constructs a SortCommand object.
     *
     * @param input The input string representing the command.
     */
    public SortCommand(String input) {
        super(input);
    }

    /**
     * Retrieves the deadline tasks from the task list.
     *
     * @param taskList The task list containing all tasks.
     * @return An ArrayList of Deadline tasks.
     */
    public ArrayList<Deadline> retrieveDeadlineTasks(TaskList taskList) {
        ArrayList<Deadline> deadlineTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task instanceof Deadline) {
                deadlineTasks.add((Deadline) task);
            }
        }
        return deadlineTasks;
    }

    /**
     * Sorts the deadline tasks by their due dates and times.
     *
     * @param taskList The task list containing all tasks.
     * @return An ArrayList of Deadline tasks sorted by due dates and times.
     */
    public ArrayList<Deadline> sortByDeadline(TaskList taskList) {
        ArrayList<Deadline> deadlineTasks = retrieveDeadlineTasks(taskList);

        Collections.sort(deadlineTasks, (d1, d2) -> {
            int compareDates = d1.getDueDate().compareTo(d2.getDueDate());
            int compareTimes = d1.getDueTime().compareTo(d2.getDueTime());
            if (compareDates != 0) {
                return compareDates;
            } else {
                return compareTimes;
            }
        });
        return deadlineTasks;
    }

    /**
     * Executes the SortCommand, sorting the deadline tasks and displaying the sorted list.
     *
     * @param ui      The user interface for displaying messages.
     * @param taskList The task list containing all tasks.
     * @param storage The storage for saving task list data.
     * @return A string message displaying the sorted deadline tasks.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        ArrayList<Deadline> sortedDeadlineTasks = sortByDeadline(taskList);
        return ui.showSortedDeadlineTasks(sortedDeadlineTasks);
    }
}

