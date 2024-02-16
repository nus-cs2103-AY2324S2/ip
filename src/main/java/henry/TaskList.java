package henry;

import java.util.ArrayList;

import henry.task.Deadline;
import henry.task.Event;
import henry.task.Task;
import henry.task.Todo;

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
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        items.add(task);
    }

    /**
     * Updates a task in the list.
     * @param index The index of the task to be updated.
     * @param description The new description of the task.
     * @param to The new 'to' date of the task.
     * @param from The new 'from' date of the task.
     * @param by The new 'by' date of the task.
     * @return The updated task.
     */
    public Task updateTask(int index, String description, String to, String from, String by) throws HenryException {
        Task task = this.items.get(index);
        if (description != null) {
            task.setDescription(description);
        }
        if (task instanceof Todo) {
            if (to != null || from != null || by != null) {
                throw new HenryException("Todo tasks do not have a date.");
            }
        } else if (task instanceof Deadline) {
            if (to != null || from != null) {
                throw new HenryException("Deadline tasks only have a 'by' date.");
            }
            if (by != null) {
                Deadline deadline = (Deadline) task;
                deadline.setBy(by);
            }
        } else if (task instanceof Event) {
            if (by != null) {
                throw new HenryException("Event tasks do not have a 'by' date.");
            }
            Event event = (Event) task;
            if (to != null) {
                event.setTo(to);
            }
            if (from != null) {
                event.setFrom(from);
            }
        }
        return task;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws HenryException If the index is out of bounds.
     */
    public Task markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        return items.get(index);
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws HenryException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        return items.get(index);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @throws HenryException If the index is out of bounds.
     */
    public String deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        String ret = String.format("This task is deleted :)\n%s\n", items.get(index));
        items.remove(index);
        return ret;
    }

    /**
     * Finds tasks by keyword.
     *
     * @param keyword The keyword to be searched for.
     * @return The list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> ret = new ArrayList<>();
        for (Task task : items) {
            if (task.containsKeyword(keyword)) {
                ret.add(task);
            }
        }
        return ret;
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
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileString());
        }
        return ret;
    }
}
