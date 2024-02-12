package aurora.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import aurora.objects.*;

/**
 * The TaskList class is used to represent the list of tasks used in the application.
 */
public class TaskList {

    /** TaskList of tasks */
    private ArrayList<Task> taskList;

    private static final String MARK_DONE = "I've marked this task as done: \n";
    private static final String UNMARK_DONE = "I've marked this task as not done yet: \n";
    private static final String DELETE_TASK = "I've removed this task as you instructed: \n";
    private static final String NUMBER_OF_TASKS = "\nNumber of tasks in the list: ";

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
     * Method to add a doAfter (after a date) to the taskList.
     *
     * @param description DoAfter description
     * @param date Date after which the task should be performed
     */
    public void addDoAfter(String description, LocalDateTime date) {
        DoAfter newTask = new DoAfter(description, date);
        this.taskList.add(newTask);
    }

    /**
     * Method to add a doAfter (after a task) to the taskList.
     *
     * @param description DoAfter description
     * @param taskNumber Integer representing the task after which this task should be performed
     */
    public void addDoAfter(String description,int taskNumber) {
        Task previousTask = this.taskList.get(taskNumber);
        DoAfter newTask = new DoAfter(description, taskNumber);
        newTask.setTask(previousTask);
        this.taskList.add(newTask);
    }

    /**
     * Marks a task in the taskList as done and returns a confirmation message.
     *
     * @param taskIndex Index of the task in the ArrayList.
     * @return A string confirming the task has been marked as done.
     */
    public String markTaskGui(int taskIndex) {
        this.taskList.get(taskIndex).setDone();
        return MARK_DONE + this.taskList.get(taskIndex).toString();
    }

    /**
     * Unmarks a task in the taskList and returns a confirmation message.
     *
     * @param taskIndex Index of the task in the ArrayList.
     * @return A string confirming the task has been unmarked.
     */
    public String unmarkTaskGui(int taskIndex) {
        this.taskList.get(taskIndex).setNotDone();
        return UNMARK_DONE + this.taskList.get(taskIndex).toString();
    }

    /**
     * Deletes a task from the taskList and returns a confirmation message.
     *
     * @param taskIndex Index of the task in the ArrayList.
     * @return A string confirming the task has been deleted.
     */
    public String deleteTaskGui(int taskIndex) {
        String taskString = this.taskList.get(taskIndex).toString();
        this.taskList.remove(taskIndex);
        return DELETE_TASK + taskString +
                NUMBER_OF_TASKS + this.taskList.size();
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
