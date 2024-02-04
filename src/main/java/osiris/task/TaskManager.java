package osiris.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import osiris.exceptions.OsirisInvalidIndexException;
import osiris.exceptions.OsirisStorageFileException;
import osiris.formatters.DateTimeFormatters;
import osiris.storage.StorageTxtFile;

/**
 * The TaskManager class manages user tasks.
 */
public class TaskManager {

    /** File path of where user tasks will be stored */
    private static final String TASK_STORAGE_FILE_PATH = "src/main/java/Osiris/Storage/data/task.txt";

    /** List of user task. */
    private final ArrayList<Task> userTasks = new ArrayList<>();

    /** Initialise new Txt File for storage. */
    private final StorageTxtFile taskStorage = new StorageTxtFile(TASK_STORAGE_FILE_PATH);

    /**
     * Initializes the task manager by loading tasks from file storage.
     */
    public void initialise() throws OsirisStorageFileException {
        taskStorage.initialiseStorageTxtFile();
        this.loadUserTaskFromFileStorage();
    }

    /**
     * Terminates the task manager by clearing file storage and storing tasks.
     */
    public void terminate() throws OsirisStorageFileException {
        taskStorage.clearStorageTxtFile();
        this.storeUserTaskToFileStorage();
    }

    /**
     * Adds a to-do task to the task manager.
     *
     * @param taskName    The name of the task.
     * @param isCompleted The completion status of the task.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean addToDoTask(String taskName, boolean isCompleted) {
        ToDoTask newTask = new ToDoTask(taskName, isCompleted);
        userTasks.add(newTask);
        return true;
    }

    /**
     * Adds a deadline task to the task manager.
     *
     * @param taskName    The name of the task.
     * @param isCompleted The completion status of the task.
     * @param deadline    The deadline of the task.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean addDeadlineTask(String taskName, boolean isCompleted, LocalDate deadline) {
        DeadlineTask newTask = new DeadlineTask(taskName, isCompleted, deadline);
        userTasks.add(newTask);
        return true;
    }

    /**
     * Adds an event task to the task manager.
     *
     * @param taskName      The name of the task.
     * @param isCompleted   The completion status of the task.
     * @param startDateTime The start date and time of the event.
     * @param endDateTime   The end date and time of the event.
     * @return True if the task is added successfully, false otherwise.
     */
    public boolean addEventTask(String taskName, boolean isCompleted, LocalDateTime startDateTime,
                                LocalDateTime endDateTime) {
        EventTask newTask = new EventTask(taskName, isCompleted, startDateTime, endDateTime);
        userTasks.add(newTask);
        return true;
    }

    /**
     * Retrieves a task from the task manager based on its index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return userTasks.get(index);
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
     * @throws OsirisInvalidIndexException If index provided is invalid.
     */
    public Task deleteTask(int index) {
        try {
            Task deletedTask = userTasks.get(index);
            userTasks.remove(index);
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            throw new OsirisInvalidIndexException(index + 1);
        }
    }

    /**
     * Retrieves the total number of tasks managed by the task manager.
     *
     * @return The total number of tasks.
     */
    public int getTotalTaskCount() {
        return userTasks.size();
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param index The index of the task to be marked as completed.
     * @return True if the task is marked as completed successfully, false otherwise.
     * @throws OsirisInvalidIndexException If index provided is invalid.
     */
    public boolean markTaskComplete(int index) {
        try {
            userTasks.get(index).markComplete();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            throw new OsirisInvalidIndexException(index + 1);
        }
    }

    /**
     * Marks a task as incomplete based on its index.
     *
     * @param index The index of the task to be marked as incomplete.
     * @return True if the task is marked as incomplete successfully, false otherwise.
     * @throws OsirisInvalidIndexException If index provided is invalid.
     */
    public boolean markTaskIncomplete(int index) {
        try {
            userTasks.get(index).markIncomplete();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with index " + (index + 1) + ". Enter 'list' to view tasks.");
            throw new OsirisInvalidIndexException(index + 1);
        }
    }

    /**
     * Finds tasks containing the specified search string.
     *
     * @param searchString The string to search for within task descriptions.
     * @return An ArrayList of task details containing the search string.
     */
    public ArrayList<String> findTask(String searchString) {
        ArrayList<String> relevantTask = new ArrayList<>();
        for (Task task: userTasks) {
            if (task.toString().contains(searchString)) {
                relevantTask.add(task.toString());
            }
        }
        return relevantTask;
    }

    /**
     * Stores user tasks to file storage.
     */
    private void storeUserTaskToFileStorage() throws OsirisStorageFileException {
        for (Task task : userTasks) {
            taskStorage.appendToStorageTxtFile(task.getStringStorageRepresentation());
        }
    }

    /**
     * Loads user tasks from file storage.
     */
    private void loadUserTaskFromFileStorage() throws OsirisStorageFileException {
        ArrayList<String> readContents = taskStorage.readStorageTxtFile();
        for (String readContentString : readContents) {
            String[] readContentWord = readContentString.split("\\|");

            if (readContentWord[0].trim().equals("T")) {
                this.addToDoTask(readContentWord[2].trim(), readContentWord[1].trim().equals("Y"));
            } else if (readContentWord[0].trim().equals("D")) {
                LocalDate deadline = DateTimeFormatters.getInstance()
                        .formatStoredDate(readContentWord[3].trim());
                this.addDeadlineTask(readContentWord[2].trim(), readContentWord[1].trim().equals("Y"), deadline);
            } else if (readContentWord[0].trim().equals("E")) {
                LocalDateTime[] dateTimeRange = DateTimeFormatters.getInstance()
                        .formatStoredDateTimeRange(readContentWord[3].trim(), readContentWord[4].trim());
                this.addEventTask(readContentWord[2].trim(), readContentWord[1].trim().equals("Y"), dateTimeRange[0],
                        dateTimeRange[1]);
            }
        }
    }
}
