package zhen;
import java.util.ArrayList;

import zhen.task.Task;

/**
 * A system to store a list of task with additional functionality.
 * Functionalities includes inserting, deleting, marking, unmarkinng, etc.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;

    /**
     * Constructs TaskList using an ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskCount = this.tasks.size();
    }

    /**
     * Accesses the number of tasks stored in TaskList.
     *
     * @return The number of tasks stored in the TaskList.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Accesses the ArrayList of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Inserts a task into the TaskList.
     *
     * @param task The task to be added.
     */
    public void insert(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Deletes a task from the TaskList with specified index.
     *
     * @param index Index of task to be deleted.
     * @return String representation of the deleted task.
     */
    public String delete(int index) {
        assert index > 0 && index < taskCount : "Delete index out of bound.";
        String deletedTaskDescription = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        taskCount--;
        assert taskCount >= 0 : "The number of tasks can't be negative.";
        return deletedTaskDescription;
    }

    /**
     * Marks the task with specified index as done.
     *
     * @param index Index of tasks to be marked as done.
     * @return String representation fo the marked task.
     */
    public String mark(int index) {
        assert index > 0 && index < taskCount : "Mark index out of bound.";
        tasks.get(index - 1).mark();
        return tasks.get(index - 1).toString();
    }

    /**
     * Unmarks the task with specified index as not done.
     *
     * @param index Index of task to be resumed as not done.
     * @return String representation of the unmarked task.
     */
    public String unmark(int index) {
        assert index > 0 && index < taskCount : "Unmark index out of bound.";
        tasks.get(index - 1).unmark();
        return tasks.get(index - 1).toString();
    }

    /**
     * Tag the task with specified index with tag.
     *
     * @param index Index of task to be resumed as not done.
     * @return String representation of the unmarked task.
     */
    public String tag(int index, String tagMessage) {
        assert index > 0 && index < taskCount : "Tag index out of bound.";
        tasks.get(index - 1).addTag(tagMessage);
        return tasks.get(index - 1).toString();
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return String listing all the tasks with each prefixed with its index number.
     */
    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            taskListString = taskListString + (i + 1) + ". " + tasks.get(i) + "\n ";
        }
        if (tasks.size() >= 1) {
            taskListString = taskListString + (tasks.size()) + ". " + tasks.get(tasks.size() - 1);
        } else {
            taskListString = "Oh, there is nothing inside the list. Please add some.";
        }
        return taskListString;
    }
}
