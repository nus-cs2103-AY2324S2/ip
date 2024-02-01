package tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import objects.Deadline;
import objects.DukeException;
import objects.Event;
import objects.Task;
import objects.Todo;

/**
 * The TaskList class is used to represent the list of tasks used in the application.
 */
public class TaskList {

    /** TaskList of tasks */
    private ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class.
     *
     * @param taskList An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to add a ToDo to the taskList.
     *
     * @param description Todo description.
     */
    public void addTodo(String description) {
        Todo newTask = new Todo(description);
        this.taskList.add(newTask);
    }

    /**
     * Method to add a Deadline to the taskList.
     *
     * @param description Deadline description.
     * @param date Date at which a deadline expires
     */
    public void addDeadline(String description, LocalDateTime date) {
        Deadline newTask = new Deadline(description, date);
        this.taskList.add(newTask);
    }

    /**
     * Method to add an Event to the tasklist.
     *
     * @param description Event description.
     * @param start Start time of event.
     * @param end End time of event.
     */
    public void addEvent(String description, LocalDateTime start, LocalDateTime end) {
        Event newTask = new Event(description, start, end);
        this.taskList.add(newTask);
    }


    /**
     * Method to mark a task in the taskList as done.
     *
     * @param taskIndex Index of the task in the ArrayList
     */
    public void markTask(int taskIndex) {
        this.taskList.get(taskIndex).setDone();
        System.out.println("I've marked this task as done: \n" +
                this.taskList.get(taskIndex).toString());
    }

    /**
     * Method to unmark a task in the taskList.
     *
     * @param taskIndex Index of the task in the ArrayList
     */
    public void unmarkTask(int taskIndex) {
        this.taskList.get(taskIndex).setNotDone();
        System.out.println("I've marked this task as not done yet: \n" +
                this.taskList.get(taskIndex).toString());
    }

    /**
     * Method to delete a task in the taskList.
     *
     * @param taskIndex Index of the task in the ArrayList
     */
    public void deleteTask(int taskIndex) {
        String taskString = this.taskList.get(taskIndex).toString();
        this.taskList.remove(taskIndex);
        System.out.println("I've removed this task as you instructed: \n" +
                taskString + "\nNumber of tasks in the list: " + taskList.size());
    }

    /**
     * Getter for the taskList
     *
     * @return The taskList stored in the object.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
