package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class to store and manage tasks
 */
public class TaskList {
    // List of tasks to be done by the user
    private ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Constructor for the TaskList class
     * It initializes an empty list of tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to add a new todo task to the list
     * 
     * @param description The description of the task
     */
    public void addTodo(String description) {
        this.tasks.add(new Todo(description));
        taskCount++;
    }

    /**
     * Method to add a new deadline task to the list
     * 
     * @param description The description of the task
     * @param dueDate     The due date of the task
     */
    public void addDeadline(String description, String dueDate) throws BotException {
        this.tasks.add(new Deadline(description, dueDate));
        taskCount++;
    }

    /**
     * Method to add a new event task to the list
     * 
     * @param description The description of the task
     * @param startTime   The start time of the event
     * @param endTime     The end time of the event
     */
    public void addEvent(String description, String startTime, String endTime) throws BotException {
        this.tasks.add(new TimeBlock(description, startTime, endTime));
        taskCount++;
    }

    /**
     * Method to return task by task number
     * 
     * @param taskNumber The number of the task
     * @return The task at the specified index
     */
    public Task getTaskByNum(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Method to remove task
     * 
     * @param taskNumber The index of the task to remove
     */
    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
        taskCount--;
    }

    /**
     * Method to list all tasks
     * 
     * @return A list of all task descriptions
     */
    public List<String> listTasks() {
        List<String> taskDescriptions = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskDescriptions.add((i + 1) + ". " + task.toString());
        }
        return taskDescriptions;
    }

    /**
     * Method to mark task as done
     * Just need number of task, index is
     * adjusted automatically by this method
     * 
     * @param taskNumber The number of the task to mark as done
     */
    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Method to mark task as undone
     * Just need number of task, index is
     * adjusted automatically by this method
     * 
     * @param taskNumber The number of the task to mark as undone
     */
    public void markTaskAsUndone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Method to return the number of tasks
     * 
     * @return The number of tasks
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Method to get the list of tasks
     * 
     * @return The list of tasks
     */
    public List<Task> getTaskList() {
        return tasks;
    }
}