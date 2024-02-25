package duke;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import duke.task.Task;

/**
 * The TaskList class represents a collection of tasks and provides methods
 * to manage and manipulate those tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs a TaskList and instantiates tasks as an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList and instantiates tasks using the provided input ArrayList.
     *
     * @param loadedTasks an ArrayList object containing Task objects to populate the task list
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * A method to add the provided task to the task list.
     *
     * @param task a Task object to be added to the task list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * A method to remove a task at the specified index (1-indexing) from the task list.
     *
     * @param taskNum an int representing the 1-indexed location of the task to be deleted
     */
    public void deleteTask(int taskNum) {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + tasks.size()
                    + " tasks on the chart, matey!");
        }
        tasks.remove(taskNum - 1);
    }

    /**
     * Method to mark a task at the specified 1-indexed index.
     *
     * @param taskNum an int representing the 1-indexed location of the task in the task list
     */
    public void markTask(int taskNum) {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + tasks.size()
                    + " tasks on the chart, matey!");
        }
        tasks.get(taskNum - 1).markAsDone();
    }

    /**
     * Method to unmark a task at the specified 1-index.
     *
     * @param taskNum an int representing the 1-indexed location of the task in the task list
     */
    public void unmarkTask(int taskNum) {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + tasks.size()
                    + " tasks on the chart, matey!");
        }
        tasks.get(taskNum - 1).markAsNotDone();
    }

    /**
     * Method to get the String describing a task at the specified 1-index.
     *
     * @param taskNum an int representing the 1-indexed location of the task in the task list
     */
    public String printTask(int taskNum) {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + tasks.size()
                    + " tasks on the chart, matey!");
        }
        return tasks.get(taskNum - 1).toString();
    }

    /**
     * Method to get the current number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Method to return the current task list as an ArrayList object.
     *
     * @return an ArrayList object from the current task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Method to find case-insensitive matches in the task list with the keyword input.
     *
     * @param keyword a String representing the keyword to be searched for
     * @return an ArrayList containing all matches found with the keyword
     * @throws NoSuchElementException if there are no matches found in the task list
     */
    public ArrayList<Task> findTasks(String keyword) throws NoSuchElementException {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        if (matches.size() == 0) {
            throw new NoSuchElementException("Avast! I be not spyin' that task on me list, matey.");
        }
        return matches;
    }
}
