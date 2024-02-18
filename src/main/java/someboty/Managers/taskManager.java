package someboty.Managers;

import java.util.ArrayList;

import someboty.Exceptions.InputException;
import someboty.Tasks.Task;

/**
 * TaskManager is a class that handles actions related to tasks.
 * This includes (but not yet limited to) changing completion status of a task,
 * adding new tasks, deleting existing tasks and finding tasks.
 * 
 * The task list will be passed to fileManager to save the list for future sessions.
 */
public class TaskManager {

    private ArrayList<Task> taskList;
    private FileManager files;

    /**
     * Constructor for taskManager.
     * @param files A fileManager object to fetch/save the task list.
     */
    public TaskManager(FileManager files) {
        this.files = files;
        taskList = this.files.fetchTasks();
        assert (taskList != null) : "Unable to create/open 'tasks.csv' file.";
    }

    /**
     * Returns the current size of the task list.
     * @return Current size of the task list as an integer.
     */
    protected int getListSize() {
        return this.taskList.size();
    }
    
    /**
     * Returns a String representation of the formatted task list.
     * The task list is formatted to be reader friendly.
     * @return
     */
    protected String printListTasks() {
        if (taskList.size() == 0) {    // special message for empty list
            return "Wow! You have no recorded task! Peek laziness here.";
        }

        String response = "Here are the tasks:\n";

        int index = 0;
        for (Task task : taskList) {
            index++;
            response += String.format("%d. %s\n", index, task);
        }

        return response;
    }

    /**
     * Set the completion status of a task in the task list.
     * Status is set to true if the task is marked as completed, else set to false.
     * @param index The i-th position of the task in the list. (1-indexed)
     * @param status The completion status of the task to set.
     * @return The task in the i-th position on the list.
     * @throws InputException If index argument is out of task list's range.
     */
    protected Task setTaskStatus(int index, boolean status) throws InputException {
        try {
            this.taskList.get(index).setStatus(status);
            return this.taskList.get(index);

         } catch (IndexOutOfBoundsException e) {
            throw new InputException("Bruh, there ain't no task " + String.valueOf(index + 1));
        }
    }

    /**
     * Deletes a task from the task list.
     * @param index The position of the task to be deleted (1-index).
     * @return The task that has just been deleted from the list.
     * @throws InputException If index argument is out of task list's range.
     */
    protected Task deleteTask(int index) throws InputException{
        try {
            Task removedTask = taskList.remove(index);
            return removedTask;

        } catch (IndexOutOfBoundsException e) {
            throw new InputException("Bruh, there ain't no task " + String.valueOf(index + 1));
        }
    }

    /**
     * Removes all existing tasks in the list.
     */
    protected void clear() {
        this.taskList.clear();
    }

    /**
     * Adds a new task into the task list.
     * The type argument is represented using the first character in the full task type.
     * @param type The type of the new task.
     * @param description Details of the new task, including its name and (if any) dates.
     * @return Successfully created new task.
     */
    protected Task addTask(char type, String description) {
        Task newTask = Task.createTask(type, description);
        assert (newTask != null) : "Failed to create a new task.";

        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Finds all existing tasks that has matching names with a given substring.
     * The substring will be case insensitive.
     * @param substring Substring to match with all task names.
     * @return A list of tasks with names that contain the substring.
     */
    protected ArrayList<Task> findTasks(String substring) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        String taskName;

        substring = substring.toLowerCase();
        for (Task task : this.taskList) {
            taskName = task.toString().toLowerCase();

            if (taskName.contains(substring)) {
                matchedTasks.add(task);
            }
        }

        return matchedTasks;
    }

    /**
     * Passes the current task list into fileManager to save to file.
     */
    protected void update() {
        assert this.files.storeTasks(this.taskList) 
            : "Unable to store the tasks into the 'tasks.csv' file.";
    }
    
    
}
