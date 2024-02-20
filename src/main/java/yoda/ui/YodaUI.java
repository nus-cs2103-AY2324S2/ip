package yoda.ui;


import yoda.exceptions.InvalidTaskException;
import yoda.parser.UpdateState;
import yoda.task.Task;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.TaskList;
import yoda.constants.Replies;
import yoda.storage.Storage;
import java.io.IOException;
import java.time.LocalDateTime;
import yoda.datetimeutil.DateTimeUtil;


public class YodaUI {
    private final TaskList TASKLIST;
    private final Storage STORAGE;
    private int updatingTaskNumber = -1;
    private UpdateState updateState = UpdateState.NONE;
    private Task taskToUpdate;

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

    /** Sets the task number to be updated.
     * @param taskNumber The number of the task to be updated.
     */
    public void setUpdatingTaskNumber(int taskNumber) {
        this.updatingTaskNumber = taskNumber;
    }

    /** Sets the current state of the update process.
     * @param state The current state of the update process.
     */
    public void setUpdateState(UpdateState state) {
        this.updateState = state;
    }

    /** Gets the number of the task to be updated.
     * @return The number of the task to be updated.
     */
    public int getUpdatingTaskNumber() {
        return this.updatingTaskNumber;
    }

    /** Gets the current state of the update process.
     * @return The current state of the update process.
     */
    public UpdateState getUpdateState() {
        return this.updateState;
    }

    /** Starts the process of updating a task.
     * @param taskNumber The number of the task to be updated.
     * @param input The user's input.
     * @return The response to be displayed in the GUI.
     */
    public String startUpdateProcess(int taskNumber, String input) {
        setUpdatingTaskNumber(taskNumber);
        try {
            taskToUpdate = TASKLIST.getTask(updatingTaskNumber);
            String typeOfTask = taskToUpdate.getType();
            switch (typeOfTask) {
            case "T":
                updateState = UpdateState.AWAITING_UPDATE_CHOICE;
                return Replies.TODO_UPDATE;
            case "D":
                updateState = UpdateState.AWAITING_UPDATE_CHOICE;
                return Replies.DEADLINE_UPDATE;
            case "E":
                updateState = UpdateState.AWAITING_UPDATE_CHOICE;
                return Replies.EVENT_UPDATE;
            }
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
        return Replies.UNKNOWN_COMMAND;
    }

    /**
     * Handles the update process for a task.
     * @param input The user's input.
     * @return The response to be displayed in the GUI.
     */
    public String handleUpdateState(String input) {
        String response;
        switch (updateState) {
        case AWAITING_UPDATE_CHOICE:
            response = processUpdateChoice(input);
            break;
        case AWAITING_NEW_DESCRIPTION:
            response = updateDescription(input);
            break;
        case AWAITING_NEW_DEADLINE:
            response = updateDeadline(input);
            break;
        case AWAITING_NEW_EVENT_START:
        case AWAITING_NEW_EVENT_END:
            response = updateEventTimes(input);
            break;
        default:
            response = Replies.UNKNOWN_COMMAND;
            break;
        }
        return response;
    }

    /** Processes the user's choice of what to update for a task.
     * @param input The user's input.
     * @return The response to be displayed in the GUI.
     */
    private String processUpdateChoice(String input) {
        Task task;
        try {
            task = TASKLIST.getTask(updatingTaskNumber);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }

        switch (input.toLowerCase()) {
        case "description":
            setUpdateState(UpdateState.AWAITING_NEW_DESCRIPTION);
            return Replies.AWAITING_DESCRIPTION_UPDATE;
        case "by time":
            if (task instanceof Deadline) {
                setUpdateState(UpdateState.AWAITING_NEW_DEADLINE);
                return Replies.AWAITING_DEADLINE_UPDATE;
            }
            break;
        case "from time":
            if (task instanceof Event) {
                setUpdateState(UpdateState.AWAITING_NEW_EVENT_START);
                return Replies.AWAITING_EVENT_START_UPDATE;
            }
            break;
        case "to time":
            if (task instanceof Event) {
                setUpdateState(UpdateState.AWAITING_NEW_EVENT_END);
                return Replies.AWAITING_EVENT_END_UPDATE;
            }
            break;
        }
        return Replies.INVALID_OPTION;
    }

    /** Updates the description of a task.
     * @param newDescription The new description to be updated.
     * @return The response to be displayed in the GUI.
     */
    private String updateDescription(String newDescription) {
        try {
            Task task = TASKLIST.getTask(updatingTaskNumber);
            task.setDescription(newDescription);
            setUpdateState(UpdateState.NONE);
            setUpdatingTaskNumber(-1);
            return Replies.TASK_UPDATED;
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }

    /** Updates the deadline of a deadline task.
     * @param newDeadline The new deadline to be updated.
     * @return The response to be displayed in the GUI.
     */
    private String updateDeadline(String newDeadline) {
        try {
            Task task = TASKLIST.getTask(updatingTaskNumber);
            if (task instanceof Deadline) {
                LocalDateTime byDateTime = DateTimeUtil.parseDateTime(newDeadline);
                ((Deadline) task).setBy(byDateTime);
                setUpdateState(UpdateState.NONE);
                return "Updated, the task deadline is.";
            }
        } catch (InvalidTaskException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Invalid date/time format, provide you must.";
        }
        return "Invalid task type, this is.";
    }

    /**
     * Updates the start and end times of an event task.
     * @param newTime The new start or end time to be updated.
     * @return The response to be displayed in the GUI.
     */
    private String updateEventTimes(String newTime) {
        try {
            Task task = TASKLIST.getTask(updatingTaskNumber);
            if (task instanceof Event) {
                LocalDateTime newDateTime = DateTimeUtil.parseDateTime(newTime);
                if (updateState == UpdateState.AWAITING_NEW_EVENT_START) {
                    ((Event) task).setFrom(newDateTime);
                    setUpdateState(UpdateState.AWAITING_NEW_EVENT_END);
                    return "New end time (YYYY-MM-DD HH:MM), enter you must:";
                } else {
                    ((Event) task).setTo(newDateTime);
                    setUpdateState(UpdateState.NONE);
                    return "Updated, the task event times are.";
                }
            }
        } catch (InvalidTaskException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Invalid date/time format, provide you must.";
        }
        return "Invalid task type, this is.";
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
