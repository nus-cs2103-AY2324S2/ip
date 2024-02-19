package lia;

import java.util.ArrayList;

/**
 * The TaskList class represents a collection of tasks and provides methods to manipulate the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Sets the list of tasks in the TaskList.
     *
     * @param tasks The list of tasks to set.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a specific task from the TaskList based on its position.
     *
     * @param pos The position of the task in the list.
     * @return The task at the specified position.
     */
    public Task getTask(int pos) {
        assert pos >= 1 && pos <= tasks.size() : "Invalid position";
        return tasks.get(pos);
    }

    /**
     * Marks a task as done based on its position in the list.
     *
     * @param pos The position of the task to mark as done.
     */
    public void markTaskAsDone(int pos) {
        assert pos >= 1 && pos <= tasks.size() : "Invalid position";
        tasks.get(pos - 1).markAsDone();
    }

    /**
     * Marks a task as not done based on its position in the list.
     *
     * @param pos The position of the task to mark as not done.
     */
    public void markTaskAsNotDone(int pos) {
        assert pos >= 1 && pos <= tasks.size() : "Invalid position";
        tasks.get(pos - 1).markAsNotDone();
    }

    /**
     * Adds a new Todo task to the list with the given description.
     *
     * @param description The description of the Todo task.
     */
    public void addTodoTask(String description) {
        assert description != null : "Description should not be null";
        tasks.add(new Todo(description, false));
    }

    /**
     * Adds a new Deadline task to the list with the given description and deadline date.
     *
     * @param description The description of the Deadline task.
     * @param by           The deadline date of the task.
     */
    public void addDeadlineTask(String description, String by) {
        assert description != null : "Description should not be null";
        assert by != null : "Deadline should not be null";
        tasks.add(new Deadline(description, by, false));
    }

    /**
     * Adds a new Event task to the list with the given description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public void addEventTask(String description, String start, String end) {
        assert description != null : "Description should not be null";
        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";
        tasks.add(new Event(description, start, end, false));
    }

    /**
     * Deletes a task from the list based on its position.
     *
     * @param pos The position of the task to delete.
     */
    public void deleteTask(int pos) {
        assert pos >= 1 && pos <= tasks.size() : "Invalid position";
        tasks.remove(pos - 1);
    }

    /**
     * Validates if the specified task position is within the valid range.
     *
     * @param pos The position of the task to validate.
     * @throws LiaException If the position is not within the valid range.
     */
    public void validateTaskPosition(int pos) throws LiaException {
        if (pos <= 0) {
            throw new LiaException("Enter number from 1 to " + tasks.size() + ".");
        }

        if (pos > tasks.size()) {
            throw new LiaException("There are only " + tasks.size() + " tasks in the list.");
        }
    }

    /**
     * Finds tasks containing the specified keyword and returns them as a list.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList containing tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword should not be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Gets the last task in the list.
     *
     * @return The last task in the list.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }
}
