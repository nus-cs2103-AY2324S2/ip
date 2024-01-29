package saopig.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final String FILE_PATH = "./data/saopigTaskList.txt";
    private static final String FILE_DIRECTORY = "./data";
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList with an empty ArrayList of Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Add a Todo task to the TaskList.
     *
     * @param task Todo task to be added.
     */
    public void addTodoTask(Todo task) {
        this.tasks.add(task);
    }

    /**
     * Add a Deadline task to the TaskList.
     *
     * @param task Deadline task to be added.
     */
    public void addDeadlineTask(Deadline task) {
        this.tasks.add(task);
    }

    /**
     * Add an Event task to the TaskList.
     *
     * @param task Event task to be added.
     */
    public void addEventTask(Event task) {
        this.tasks.add(task);
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index Index of the Task.
     * @return Task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of Tasks in the TaskList.
     *
     * @return Number of Tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the ArrayList of Tasks.
     *
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Deletes the Task at the given index.
     *
     * @param index Index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

}
