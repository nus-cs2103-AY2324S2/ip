package sam;

import java.lang.reflect.Array;
import java.util.ArrayList;

import sam.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Returns the number of tasks in the list.
     * @return Number of tasks in the list.
     */
    public int getNumOfTasks() {
        return items.size();
    }

    /**
     * Gets the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(items);
    }

    /**
     * Marks a task in the list as done.
     *
     * Marks the task at the specified index in the task list as done.
     *
     * @param index the index of the task to mark as done
     * @throws SamException if the index is out of bounds
     */
    public Task markTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Please check how many tasks are there in your list.");
        }
        items.get(index).markAsDone();
        return items.get(index);
    }

    /**
     * Unmarks a task in the list as undone.
     *
     * Unmarks the task at the specified index in the task list as undone.
     *
     * @param index the index of the task to unmark as undone
     * @throws SamException if the index is out of bounds
     */
    public Task unmarkTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Please check how many tasks are there in your list.");
        }
        items.get(index).markAsUndone();
        return items.get(index);
    }

    /**
     * Adds a new task to the task list.
     *
     * Adds the provided Task object to the task list.
     *
     * @param newTask the task to be added to the list
     */
    public void addTask(Task newTask) {
        items.add(newTask);
    }

    /**
     * Deletes a task from the task list.
     *
     * Removes the task at the specified index from the task list.
     *
     * @param index the index of the task to delete from the list
     * @throws SamException if the index is out of bounds
     */
    public String deleteTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Invalid task number. Please check how many tasks your have in the list.");
        }

        Task removedTask = items.remove(index);
        String notice = String.format("This task has been deleted \n$s\n", removedTask);
        return notice;
    }

    /**
     * Finds tasks containing the specified keyword.
     *
     * Searches through the list of tasks and adds tasks containing the specified keyword to a new list.
     *
     * @param keyword the keyword to search for in task descriptions
     * @return an ArrayList containing tasks that match the keyword
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : items) {
            if (task.containsKeyword(keyword)) {
                matchList.add(task);
            }
        }
        return matchList;
    }


    /**
     * Retrieves the file strings from the Storage object.
     *
     * Returns an ArrayList containing the file strings stored in the Storage object.
     *
     * @return an ArrayList containing the file strings
     */
    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileFormat());
        }
        return ret;
    }
}
