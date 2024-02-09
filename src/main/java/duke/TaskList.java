package duke;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    protected static ArrayList<Task> tasks;
    protected static int taskCount = 0;

    /**
     * Constructs a TaskList and instantiates tasks as an empty ArrayList.
     */
    public TaskList() {
        this.tasks =  new ArrayList<>();
    }

    /**
     * Constructs a TaskList and instantiates tasks using the provided input ArrayList.
     *
     * @param loadedTasks an ArrayList object containing Task objects to populate the task list
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
        taskCount = loadedTasks.size();
    }

    /**
     * A method to add the provided task to the task list.
     *
     * @param task a Task object to be added to the task list
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount += 1;
    }

    /**
     * A method to remove a task at the specified index (1-indexing) from the task list.
     *
     * @param taskNum an int representing the 1-indexed location of the task to be deleted
     */
    public void deleteTask(int taskNum) {
        if (taskNum > taskCount || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + taskCount
                    + " tasks on the chart, matey!");
        }
        tasks.remove(taskNum - 1);
        taskCount -= 1;
    }

    /**
     * Method to print all current tasks in the task list.
     */
    public void listTasks () {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + "." +tasks.get(i).toString());
        }
    }

    /**
     * Method to mark a task at the specified 1-indexed index.
     *
     * @param taskNum an int representing the 1-indexed location of the task in the task list
     */
    public void markTask(int taskNum) {
        if (taskNum > taskCount || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + taskCount
                    + " tasks on the chart, matey!");
        }
        tasks.get(taskNum - 1).markAsDone();
    }

    /**
     * Method to unmark a task at the specified 1-indexed index.
     *
     * @param taskNum an int representing the 1-indexed location of the task in the task list
     */
    public void unmarkTask(int taskNum) {
        if (taskNum > taskCount || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + taskCount
                    + " tasks on the chart, matey!");
        }
        tasks.get(taskNum - 1).markAsNotDone();
    }

    /**
     * Method to get the String describing a task at the specified 1-indexed index.
     *
     * @param taskNum an int representing the 1-indexed location of the task in the task list
     */
    public String printTask(int taskNum) {
        if (taskNum > taskCount || taskNum < 1) {
            throw new IllegalArgumentException("Blunder! Ye only be havin' " + taskCount
                    + " tasks on the chart, matey!");
        }
        return tasks.get(taskNum - 1).toString();
    }

    /**
     * Method to get the current number of tasks in the task list.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Method to return the current task list as an ArrayList object.
     *
     * @return an ArrayList object from the current task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
