package atlas;

import atlas.exception.AtlasException;
import atlas.task.Deadline;
import atlas.task.Event;
import atlas.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes a new TaskList with an empty arraylist of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list using index.
     *
     * @param index The index of the task to be deleted.
     * @return deletedTask The task to be deleted.
     * @throws AtlasException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws AtlasException {
        checkBounds(index);
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    private void checkBounds(int index) throws AtlasException {
        if (index < 0 || index >= tasks.size()) {
            throw new AtlasException("Task number " + (index + 1) + " does not exist in the list.");
        }
    }

    private void checkIfAlreadyMarked(int index) throws AtlasException {
        if (tasks.get(index).getStatusIcon().equals("X")) {
            throw new AtlasException("Task is already marked as completed!");
        }
    }

    private void checkIfAlreadyUnmarked(int index) throws AtlasException {
        if (tasks.get(index).getStatusIcon().equals(" ")) {
            throw new AtlasException("Task is already unmarked!");
        }
    }

    /**
     * Marks a task as done by index.
     *
     * @param i The index of the task to be marked as done.
     * @throws AtlasException If the index is out of bounds.
     */
    public void markTask(int i) throws AtlasException {
        checkBounds(i);
        checkIfAlreadyMarked(i);
        tasks.get(i).toggle();
    }

    /**
     * Unmarks a task as not done by index.
     *
     * @param i The index of the task to be unmarked.
     * @throws AtlasException If the index is out of bounds.
     */
    public void unmarkTask(int i) throws AtlasException {
        checkBounds(i);
        checkIfAlreadyUnmarked(i);
        tasks.get(i).toggle();
    }

    /**
     * Retrieves a list of tasks that occur on a particular date.
     *
     * @param date The date for which tasks are to be retrieved.
     * @return An ArrayList of tasks that occur on the specified date.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDate startDate = event.getStart().toLocalDate();
                LocalDate endDate = event.getEnd().toLocalDate();
                if (checkDateWithinRange(date, startDate, endDate)) {
                    tasksOnDate.add(task);
                }
            }
        }
        return tasksOnDate;
    }

    private static boolean checkDateWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    /**
     * Gets the list of all tasks.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Takes in a keyword and return tasks that match with that specific keyword.
     *
     * @param keyword Keyword given by the user.
     * @return A list of tasks that match with that keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void changeTaskPriority(int taskIndex, int priority) {
        this.tasks.get(taskIndex).setPriority(priority);
    }

}
