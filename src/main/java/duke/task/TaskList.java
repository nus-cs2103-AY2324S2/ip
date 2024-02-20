package duke.task;

import java.util.ArrayList;
import java.util.StringTokenizer;

import duke.storage.LoadException;
import duke.utils.Parser;

/**
 * Class to track and manage tasks.
 *
 * @author KohGuanZeh
 */
public class TaskList {
    // Store list of all tasks.
    private ArrayList<Task> taskList;

    /**
     * Creates a new instance of TaskList.
     * Initial capacity is set to 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Adds the specified Task and returns a message of the added Task.
     *
     * @param task Task to add.
     * @return Message stating the added task.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return "Added: " + task.getTaskInformation() + "\n" + this.getTotalTasks();
    }

    /**
     * Removes the task at specified index and returns a message of the deleted Task.
     *
     * @param index Index of task as displayed.
     * @return Message stating the deleted task.
     * @throws TaskListException Exception indicating invalid command when passed index does not exist.
     */
    public String deleteTask(int index) throws TaskListException {
        Task removedTask = this.removeTaskAtIndex(index);
        return "Removed: " + removedTask.getTaskInformation() + "\n" + this.getTotalTasks();
    }

    /**
     * Returns the total number of tasks tracked.
     *
     * @return Message of total number of tasks tracked.
     */
    public String getTotalTasks() {
        return "There are now " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Sets the task at the specified index as done and returns a task completion message.
     *
     * @param index Index of task as displayed.
     * @return Task completion message.
     * @throws TaskListException Exception indicating invalid command when passed index does not exist.
     */
    public String markTask(int index) throws TaskListException {
        Task task = this.getTaskAtIndex(index);
        task.markAsDone();
        return "Great job for finishing the task:\n" + task.getTaskInformation();
    }

    /**
     * Sets the task at the specified index as not done and returns a task incompletion message.
     *
     * @param index Index of task as displayed.
     * @return Task incompletion message.
     * @throws TaskListException Exception indicating invalid command when passed index does not exist.
     */
    public String unmarkTask(int index) throws TaskListException {
        Task task = this.getTaskAtIndex(index);
        task.unmarkAsDone();
        return "Take your time mate. Quality over quantity:\n" + task.getTaskInformation();
    }

    /**
     * Sets the task at specified index to be of specified priority.
     *
     * @param index Index of task as displayed.
     * @param priority New priority of task.
     * @return Task new priority message.
     * @throws TaskListException Exception indicating invalid command when passed index does not exist.
     */
    public String setPriority(int index, Priority priority) throws TaskListException {
        Task task = this.getTaskAtIndex(index);
        task.setPriority(priority);
        return "Modified Priority.\n" + task.getTaskInformation();
    }

    /**
     *  Returns the task at specified index in taskList.
     *
     * @param index ArrayList index of task in taskList.
     * @return Index of task as displayed.
     * @throws TaskListException Exception thrown when index is out of bounds.
     */
    private Task getTaskAtIndex(int index) throws TaskListException {
        if (index < 1 || index > this.taskList.size()) {
            throw new TaskListException("Error. Task of index " + index + " cannot be found.");
        }
        return this.taskList.get(index - 1);
    }

    /**
     *  Returns and removes the task at specified index in taskList.
     *
     * @param index ArrayList index of task in taskList.
     * @return Index of task as displayed.
     * @throws TaskListException Exception thrown when index is out of bounds.
     */
    private Task removeTaskAtIndex(int index) throws TaskListException {
        if (index < 1 || index > this.taskList.size()) {
            throw new TaskListException("Error. Task of index " + index + " cannot be found.");
        }
        return this.taskList.remove(index - 1);
    }

    /**
     * Returns the list of tasks and their completion status.
     *
     * @return String containing list of tasks and completion status.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder("Here are your list of tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\n    ");
            sb.append(i + 1).append(".").append(this.taskList.get(i).getTaskInformation());
        }
        return sb.toString();
    }

    /**
     * Returns the list of tasks matching given keyword.
     *
     * @param keyword Matching keyword in description.
     * @return String containing list of tasks that matches keyword and their completion status.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the list of tasks that matches '" + keyword + "':");
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).descriptionContains(keyword)) {
                continue;
            }
            sb.append("\n    ");
            sb.append(i + 1).append(".").append(taskList.get(i).getTaskInformation());
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks that were not loaded successfully.
     * Loads the given String data as tasks and adds them to the list.
     *
     * @param data String data stored in file.
     * @return Number of tasks that did not load successfully.
     */
    public int loadTasks(String data) {
        int failedToLoad = 0;
        StringTokenizer st = new StringTokenizer(data, "\n");
        while (st.hasMoreTokens()) {
            try {
                this.addTask(Parser.parseData(st.nextToken()));
            } catch (LoadException e) {
                failedToLoad += 1;
            }
        }
        return failedToLoad;
    }

    /**
     * Returns the String format as to how data should be written to file.
     *
     * @return String to be written to save file.
     */
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.taskList) {
            sb.append(task.toDataString()).append("\n");
        }
        return sb.toString();
    }
}
