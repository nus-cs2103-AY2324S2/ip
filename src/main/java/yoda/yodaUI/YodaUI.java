package yoda.yodaUI;


import yoda.exceptions.InvalidTaskException;
import yoda.parser.UpdateState;
import yoda.task.Task;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.Todo;
import yoda.task.TaskList;
import yoda.constants.Replies;
import yoda.storage.Storage;
import java.io.IOException;
import java.time.LocalDateTime;


import yoda.constants.Replies;


public class YodaUI {
    private final TaskList TASKLIST;
    private final Storage STORAGE;
    private int updatingTaskNumber = -1; // Default to -1 to indicate no task is being updated
    private UpdateState updateState = UpdateState.NONE; // Default state

    /**
     * Constructor to initialize the chat bot with a TaskList, and a Storage.
     * @param taskList The TaskList object to manage tasks.
     * @param storage The Storage object for handling task persistence.
     */
    public YodaUI(TaskList taskList, Storage storage) {
        this.TASKLIST = taskList;
        this.STORAGE = storage;
    }


    /**
     * Marks a task as done.
     * Delegates to TaskList to mark a task as done.
     * @param taskNumber The number of the task to mark as done.
     */
    public String markTaskAsDone(int taskNumber) {
        try {
            return TASKLIST.markTaskAsDone(taskNumber);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as undone.
     * Delegates to TaskList to mark a task as undone.
     * @param taskNumber The number of the task to mark as not done.
     */
    public String markTaskAsUndone(int taskNumber) {
        try {
            return TASKLIST.markTaskAsUndone(taskNumber);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getTaskListSize() {
        return TASKLIST.getSize();
    }

    public TaskList getTaskList() {
        return this.TASKLIST;
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
            return TASKLIST.deleteTask(taskNumber);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a task to the list.
     * Delegates to TaskList to add a task.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        TASKLIST.addTask(task);
        return "Hmm, added this task, I have:\n" + task + "\nTasks in the list, now you have " + TASKLIST.getSize() + ", hmm.";
    }

    /**
     * Displays all the tasks in the list.
     * Delegates to TaskList to get the string representation of tasks.
     */
    public String showTasks() {
        return TASKLIST.toString();
    }

    /**
     * Saves the tasks to the file.
     * Delegates to Storage to save the tasks.
     * @param taskList The TaskList to be saved.
     */
    public String saveTasks(TaskList taskList) {
        try {
            STORAGE.saveTasks(taskList);
            return Replies.TASKS_SAVED;
        } catch (IOException e) {
            return "Error saving tasks: " + e.getMessage();
        }
    }


    public String startUpdateProcess(int taskNumber) {
        Task task = null;
        try {
            task = TASKLIST.getTask(taskNumber);
            setUpdatingTaskNumber(taskNumber);

            if (task instanceof Todo) {
                setUpdateState(UpdateState.AWAITING_NEW_DESCRIPTION);
                return Replies.AWAITING_TODO_UPDATE;
            } else if (task instanceof Deadline) {
                setUpdateState(UpdateState.AWAITING_UPDATE_CHOICE);
                return Replies.AWAITING_DEADLINE_UPDATE;
            } else if (task instanceof Event) {
                setUpdateState(UpdateState.AWAITING_UPDATE_CHOICE);
                return Replies.AWAITING_EVENT_UPDATE;
            }
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
        return Replies.UNKNOWN_COMMAND;
    }


    public void setUpdatingTaskNumber(int taskNumber) {
        this.updatingTaskNumber = taskNumber;
    }

    public void setUpdateState(UpdateState state) {
        this.updateState = state;
    }

    public int getUpdatingTaskNumber() {
        return this.updatingTaskNumber;
    }

    public UpdateState getUpdateState() {
        return this.updateState;
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
