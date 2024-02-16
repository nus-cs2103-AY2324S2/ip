package harper.utils;

import java.util.ArrayList;

import harper.exceptions.HarperInvalidIndexException;
import harper.tasks.Task;

/**
 * The TaskList class handles operations on the task list, such as add task, delete task
 * mark or unmark task and list task.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Lists out the tasks in the list as String form.
     *
     * @return String form of the tasks in the list.
     */
    public String listTasks() {
        String tasksString = "";
        int tasksSize = this.tasks.size();
        for (int i = 0; i < tasksSize; i++) {
            int index = i + 1;
            tasksString = tasksString + index + ". " + this.tasks.get(i).toString() + "\n";
        }
        return tasksString;
    }

    /**
     * Lists out the tasks in the list as file form.
     *
     * @return File form of the tasks in the list.
     */
    public String listTasksIntoFile() {
        String tasksString = "";
        for (Task task : this.tasks) {
            tasksString = tasksString + task.toString() + System.lineSeparator();
        }
        return tasksString;
    }

    /**
     * Adds the task into the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task with index specified.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public Task deleteTask(int taskIndex) {
        try {
            Task taskRemoved = this.tasks.remove(taskIndex);
            return taskRemoved;
        } catch (IndexOutOfBoundsException e) {
            throw new HarperInvalidIndexException();
        }
    }

    /**
     * Marks the task with index specified as done.
     *
     * @param taskIndex Index of the task to be marked.
     * @return task.Task that is marked.
     */
    public Task markTask(int taskIndex, boolean isMarked) {
        try {
            Task task = this.tasks.get(taskIndex);
            if (isMarked) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new HarperInvalidIndexException();
        }
    }

    /**
     * Returns size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds tasks inside the list that match the keyword.
     *
     * @param keyword Keyword to be checked.
     * @return String representation of the task list that match the keyword.
     */
    public String findTasks(String keyword) {
        String tasksString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.matchKeyword(keyword)) {
                int index = i + 1;
                tasksString = tasksString + index + ". " + task.toString() + "\n";
            }
        }
        return tasksString;
    }
}
