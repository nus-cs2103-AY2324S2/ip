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
        assert tasks != null : "Task list should be initialized as an empty list";
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
        assert taskNumber > 0 && taskNumber <= tasks.size() : "Task number should be within the valid range";
        tasks.remove(taskNumber - 1);
        taskCount--;
        assert taskCount == tasks.size() : "Task count should be equal to the size of the task list";
    }

    /**
     * Method to list all tasks
     * 
     * @return A list of all task descriptions
     */
    public List<String> getTasksAsList() {
        List<String> taskDescriptions = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskDescriptions.add((i + 1) + ". " + task.toString());
        }
        return taskDescriptions;
    }

    /**
     * Finds tasks in the task list that contain the specified keyword in their
     * description
     * Assumption here is that keyword would be valid
     *
     * @param keyword the keyword to search for in the task descriptions
     * @return a list of tasks that contain the specified keyword in their
     *         description
     */
    public List<Task> getTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            // convert all to lower case for case-insensitive search
            String temp = task.getDescription().toLowerCase();
            if (temp.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Method to mark task as done
     * Just need number of task, index is
     * adjusted automatically by this method
     * 
     * @param taskNumber The number of the task to mark as done
     */
    public void markTaskAsDone(int taskNumber) {
        assert taskNumber > 0 && taskNumber <= tasks.size() : "Task number should be within the valid range";
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Method to mark task as undone
     * Just need number of task, index is
     * adjusted automatically by this method
     * 
     * @param taskNumber The number of the task to mark as undone
     */
    public void unmarkTaskAsDone(int taskNumber) {
        assert taskNumber > 0 && taskNumber <= tasks.size() : "Task number should be within the valid range";
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

    /*
     * Method to return the string representation of the task list
     * 
     * @return The string representation of the task list
     */
    @Override
    public String toString() {
        StringBuilder tasksMsg = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tasksMsg.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return tasksMsg.toString();
    }
}