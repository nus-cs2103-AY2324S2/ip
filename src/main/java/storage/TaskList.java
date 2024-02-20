package storage;

import java.util.ArrayList;
import java.util.List;

import exceptions.ArgumentException;
import parser.Parser;
import tasks.Task;


/**
 * Represents the todolist of the application.
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>(100);
    }

    /**
     * Adds the provided task to the list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes the task located at the specified 1-based index.
     *
     * @throws ArgumentException if the provided index is out of bounds.
     */
    public void deleteTask(int index) throws ArgumentException {
        throwIfInvalidIndex(index);
        list.remove(index - 1);
    }

    /**
     * Marks the task at the specified 1-based index to be done.
     *
     * @throws ArgumentException if the provided index is out of bounds.
     */
    public void markTaskDone(int index) throws ArgumentException {
        this.getTask(index).markDone();
    }

    /**
     * Marks the task at the specified 1-based index to be not done.
     *
     * @throws ArgumentException if the provided index is out of bounds.
     */
    public void markTaskNotDone(int index) throws ArgumentException {
        this.getTask(index).markNotDone();
    }

    /**
     * Returns the current length of the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task at the specified 1-based index.
     *
     * @throws ArgumentException if the provided index is out of bounds.
     */
    public Task getTask(int index) throws ArgumentException {
        throwIfInvalidIndex(index);
        return list.get(index - 1);
    }

    /**
     * Returns a String of all tasks in the list where the description contains the provided String.
     *
     * @param matcher String to look for.
     */
    public String findTasks(String matcher) {
        String output = "";
        int outputIndex = 1;
        for (Task t : list) {
            if (t.isFound(matcher)) {
                output += outputIndex + "." + t + "\n";
                outputIndex++;
            }
        }
        return output;
    }

    /**
     * Updates the details of the task at the specified 1-based index.
     *
     * @throws ArgumentException if the provided index is out of bounds or if insufficient details are provided.
     */
    public void updateTask(int index, String details) throws ArgumentException {
        this.getTask(index).update(details);
    }

    /**
     * Returns the list of tasks converted to their String save format.
     */
    public List<String> toSaveFormat() {
        List<String> output = new ArrayList<>();
        for (Task task : list) {
            output.add(task.toSaveFormat());
        }
        return output;
    }

    /**
     * Converts the input list of Strings to tasks and adds them to the list.
     *
     * @throws ArgumentException if input is of wrong format.
     */
    public void loadFromSaveFormat(List<String> input) throws ArgumentException {
        for (String line : input) {
            list.add(Parser.parseLine(line));
        }
    }

    private void throwIfInvalidIndex(int index) throws ArgumentException {
        if (index > list.size() || index < 1) {
            throw new ArgumentException("Invalid index: " + index);
        }
    }

    /**
     * Returns String representation of this object.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            output += (i + 1) + "." + t.toString() + "\n";
        }
        return output;
    }
}
