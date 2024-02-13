package yoda.yodaUI;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.storage.Storage;
import java.io.IOException;
import yoda.constants.Replies;


public class YodaUI {
    private final TaskList TASKLIST;
    private boolean isChatting;
    private final Storage STORAGE;

    /**
     * Constructor to initialize the chatbot with a name, a TaskList, and a Storage.
     * @param taskList The TaskList object to manage tasks.
     * @param storage The Storage object for handling task persistence.
     */
    public YodaUI(TaskList taskList, Storage storage) {
        this.isChatting = true;
        this.TASKLIST = taskList;
        this.STORAGE = storage;
    }

    /**
     * Checks if the chatbot is currently chatting.
     * @return true if chatting, false otherwise.
     */
    public boolean isChatting() {
        return this.isChatting;
    }

    /**
     * Stops the chatbot from chatting.
     */
    public void stopChatting() {
        this.isChatting = false;
    }

    /**
     * Marks a task as done.
     * Delegates to TaskList to mark a task as done.
     * @param taskNumber The number of the task to mark as done.
     */
    public String markTaskAsDone(int taskNumber) {
        try {
            TASKLIST.markTaskAsDone(taskNumber);
            Task task = TASKLIST.getTask(taskNumber);
            return "Done, this task is:\n" + task;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public int getTaskListSize() {
        return TASKLIST.size();
    }

    // Method to get the TaskList object
    public TaskList getTaskList() {
        return this.TASKLIST;
    }

    /**
     * Marks a task as undone.
     * Delegates to TaskList to mark a task as undone.
     * @param taskNumber The number of the task to mark as not done.
     */
    public String markTaskAsUndone(int taskNumber) {
        try {
            TASKLIST.markTaskAsUndone(taskNumber);
            Task task = TASKLIST.getTask(taskNumber);
            return "Undone, this task remains:\n" + task;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Finds tasks that match the search term.
     * Delegates to TaskList to find tasks.
     * @param searchTerm The search term to match tasks against.
     */
    public String findTasks(String searchTerm) {
        return TASKLIST.findTasks(searchTerm);
    }

    /**
     * Deletes a task from the list.
     * Delegates to TaskList to delete a task.
     * @param taskNumber The number of the task to be deleted.
     */
    public String deleteTask(int taskNumber) {
        try {
            TASKLIST.deleteTask(taskNumber);
            return "Removed, this task has been:\nNow you have " + TASKLIST.size() + " tasks in the list.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a new task to the list.
     * Delegates to TaskList to add a new task.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        TASKLIST.addTask(task);
        return "Hmm, added this task, I have:\n" + task + "\nTasks in the list, now you have " + TASKLIST.size() + ", hmm.";
    }

    /**
     * Displays all the tasks in the list.
     * Delegates to TaskList to get the string representation of tasks.
     */
    public String showTasks() {
        return TASKLIST.toString();
    }

    public String saveTasks(TaskList taskList) {
        try {
            STORAGE.saveTasks(taskList);
            return "Saved, your task list has been.";
        } catch (IOException e) {
            return "Error saving tasks: " + e.getMessage();
        }
    }


    /**
     * Constructs a greeting message when the chatbot starts.
     *
     * @return A string containing the formatted greeting message.
     */
    public String printGreeting() {
        return Replies.GREET;
    }


}
