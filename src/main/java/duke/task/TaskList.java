package duke.task;

import duke.exceptions.IllegalParamException;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Represents a collection of tasks and contains methods to manipulate this collection.
 */
public class TaskList implements Iterable<Task>{
    /** Stores tasks in a list */
    private List<Task> taskList = new ArrayList<>();

    @Override
    public String toString() {
        String out = "Here is the list of things I remember!\n";
        int count = 1;

        for (Task currentItem : this.taskList) {
            out += count + "." + currentItem + "\n";
            count++;
        }
        return out.equals("Here is the list of things I remember!\n") ? "Looks like you have nothing to do! Yay!\n" : out;
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    /**
     * Adds new task to the list.
     *
     * @param taskName Task object to be added.
     */
    public void add(Task taskName) {
        this.taskList.add(taskName);
    }

    /**
     * Retrieves a task from the list by their index.
     *
     * @param index Index to retrieve.
     * @return Task object.
     * @throws IllegalParamException If index requested is not available.
     */
    public Task getTask(int index) throws IllegalParamException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParamException("I cant do that! The task does not exist!");
        }
    }

    /**
     * Deletes a task from the list based on index.
     *
     * @param index Index to delete.
     * @throws IllegalParamException If index specified is not available.
     */
    public void deleteTask(int index) throws IllegalParamException {
        try {
            this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParamException("I cant delete that task! It does not exist!");
        }
    }

    /**
     * Returns count of number of tasks in list.
     *
     * @return Int value.
     */
    public int countTasks() {
        return taskList.size();
    }
}
