package osiris.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import osiris.formatters.DateTimeFormatters;
import osiris.storage.TxtFileStorage;

/**
 * The TaskManager class manages user tasks.
 */
public class TaskManager {

    private final String TASKSTORAGEFILEPATH = "src/main/java/Osiris/Storage/data/task.txt";

    private final ArrayList<Task> userTasks = new ArrayList<>();

    private final TxtFileStorage taskStorage = new TxtFileStorage(TASKSTORAGEFILEPATH);

    /**
     * Adds a to-do task to the task manager.
     *
     * @param taskName    The name of the task.
     * @param isCompleted The completion status of the task.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean addToDoTask(String taskName, boolean isCompleted) {
        ToDoTask newTask = new ToDoTask(taskName, isCompleted);
        this.userTasks.add(newTask);
        return true;
    }

    /**
     * Adds a deadline task to the task manager.
     *
     * @param taskName    The name of the task.
     * @param deadline    The deadline of the task.
     * @param isCompleted The completion status of the task.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean addDeadlineTask(String taskName, LocalDate deadline, boolean isCompleted) {
        DeadlineTask newTask = new DeadlineTask(taskName, isCompleted, deadline);
        this.userTasks.add(newTask);
        return true;
    }

    /**
     * Adds an event task to the task manager.
     *
     * @param taskName      The name of the task.
     * @param startDateTime The start date and time of the event.
     * @param endDateTime   The end date and time of the event.
     * @param isCompleted   The completion status of the task.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean addEventTask(String taskName, LocalDateTime startDateTime,
                                LocalDateTime endDateTime, boolean isCompleted) {
        EventTask newTask = new EventTask(taskName, isCompleted, startDateTime, endDateTime);
        this.userTasks.add(newTask);
        return true;
    }

    /**
     * Retrieves a task from the task manager based on its index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.userTasks.get(index);
    }

    /**
     * Retrieves all user tasks managed by the task manager.
     *
     * @return The list of user tasks.
     */
    public ArrayList<Task> getUserTasks() {
        return userTasks;
    }

    /**
     * Removes a task from the task manager based on its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task, or null if the index is out of bounds.
     */
    public Task removeTask(int index) {
        try {
            Task removedTask = this.userTasks.get(index);
            this.userTasks.remove(index);
            return removedTask;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            return null;
        }
    }

    /**
     * Retrieves the total number of tasks managed by the task manager.
     *
     * @return The total number of tasks.
     */
    public int getTotalTaskCount() {
        return this.userTasks.size();
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param index The index of the task to be marked as completed.
     * @return True if the task is marked as completed successfully, false otherwise.
     */
    public boolean markTaskCompleted(int index) {
        try {
            this.userTasks.get(index).markCompleted();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            return false;
        }
    }

    /**
     * Marks a task as incomplete based on its index.
     *
     * @param index The index of the task to be marked as incomplete.
     * @return True if the task is marked as incomplete successfully, false otherwise.
     */
    public boolean markTaskIncomplete(int index) {
        try {
            this.userTasks.get(index).markIncomplete();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            return false;
        }
    }

    /**
     * Stores user tasks to file storage.
     */
    private void storeUserTaskToFileStorage() {
        for (Task task : this.userTasks) {
            this.taskStorage.appendToTxtFileStorage(task.getStringStorageRepresentation());
        }
    }


    /**
     * Loads user tasks from file storage.
     */
    private void loadUserTaskFromFileStorage() {
        ArrayList<String> readContents = this.taskStorage.readTxtFileStorage();
        for (String readContentString : readContents) {
            String[] readContentWord = readContentString.split("\\|");

            if (readContentWord[0].trim().equals("T")) {
                this.addToDoTask(readContentWord[2].trim(), readContentWord[1].trim().equals("Y"));
            } else if (readContentWord[0].trim().equals("D")) {
                LocalDate deadline = DateTimeFormatters.getInstance()
                        .storedDataDateFormatter(readContentWord[3].trim());
                this.addDeadlineTask(readContentWord[2].trim(), deadline, readContentWord[1].trim().equals("Y"));
            } else if (readContentWord[0].trim().equals("E")) {
                LocalDateTime[] dateTimeRange = DateTimeFormatters.getInstance()
                        .storedDataDateTimeRangeFormatter(readContentWord[3].trim(), readContentWord[4].trim());
                this.addEventTask(readContentWord[2].trim(), dateTimeRange[0],
                        dateTimeRange[1], readContentWord[1].trim().equals("Y"));
            }
        }
    }


    /**
     * Initializes the task manager by loading tasks from file storage.
     */
    public void initialise() {
        this.taskStorage.initialiseTxtFileStorage();
        this.loadUserTaskFromFileStorage();
    }


    /**
     * Terminates the task manager by clearing file storage and storing tasks.
     */
    public void termintate() {
        this.taskStorage.clearTxtFileStorage();
        this.storeUserTaskToFileStorage();
    }
}
