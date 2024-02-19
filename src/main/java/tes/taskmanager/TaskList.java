package tes.taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list to store tasks.
 */
public class TaskList {
    /** Format of the date and time in the command received */
    private static final DateTimeFormatter FORMATTER_RECEIVE = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** The task list to store tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs a task list to store tasks.
     * Assumes the maximum size of the list to be 100.
     *
     * @param tasks The task list either retrieved from local disk or a new one.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Stores the to-do task into the task list.
     *
     * @param task Description of task to be stored.
     */
    public void storeToDo(String task) {
        ToDo newToDo = new ToDo(task);
        this.tasks.add(newToDo);
        Storage.saveToFile(this.tasks);
    }

    /**
     * Stores the task with deadline into the task list.
     *
     * @param task Description of task to be stored.
     * @param by tes.taskmanager.Deadline of the task.
     */
    public void storeDeadline(String task, String by) {
        LocalDateTime formattedBy = LocalDateTime.parse(by, FORMATTER_RECEIVE);
        Deadline newDeadline = new Deadline(task, formattedBy);
        this.tasks.add(newDeadline);
        Storage.saveToFile(this.tasks);
    }

    /**
     * Stores the task with a designated period into the task list.
     *
     * @param task Description of task to be stored.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public void storeEvent(String task, String from, String to) {
        LocalDateTime formattedFrom = LocalDateTime.parse(from, FORMATTER_RECEIVE);
        LocalDateTime formattedTo = LocalDateTime.parse(to, FORMATTER_RECEIVE);
        Event newEvent = new Event(task, formattedFrom, formattedTo);
        this.tasks.add(newEvent);
        Storage.saveToFile(this.tasks);
    }

    /**
     * Gets task description.
     *
     * @param index Index of task in the list.
     * @return The task description.
     */
    public String getTaskDescription(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Gets the size of list.
     *
     * @return The size of list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks the task as done.
     *
     * @param index Index of task in the list.
     */
    public void mark(int index) {
        this.tasks.get(index).mark();
        Storage.saveToFile(this.tasks);
    }

    /**
     * Unmarks the task.
     *
     * @param index Index of task in the list.
     */
    public void unmark(int index) {
        this.tasks.get(index).unmark();
        Storage.saveToFile(this.tasks);
    }

    /**
     * Deletes the item in tasks with the respective index.
     *
     * @param index Index of task in the list.
     */
    public void delete(int index) {
        this.tasks.remove(index);
        Storage.saveToFile(this.tasks);
    }
}
