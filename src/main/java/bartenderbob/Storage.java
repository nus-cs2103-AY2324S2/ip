package bartenderbob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the storage class to store user tasks into the hard disk.
 */
public class Storage {
    /** File path to the storage file */
    private String filePath; //eg "./data/tasks.txt"
    /** Displays messages to the user */
    private Ui ui = new Ui();

    /**
     * Creates an instance of the Storage class and initialises its file path.
     *
     * @param filePath File path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the storage file into BartenderBob.
     *
     * @return ArrayList of tasks
     * @throws BartenderBobException If error occurred while loading the tasks into BartenderBob.
     */
    public ArrayList<Task> load() throws BartenderBobException {
        try {
            assert filePath != null : "File path cannot be null";
            ArrayList<Task> taskArray = new ArrayList<>();
            Path path = Paths.get(filePath); //Operating system independent
            if (Files.exists(path)) {
                List<String> tasks = Files.readAllLines(path);
                for (String taskString : tasks) {
                    Task task = parseTaskFromString(taskString);
                    taskArray.add(task);
                }
            } else {
                //Create data directory
                //if path is "./data/tasks.txt", then path.getParent()
                //gives data.
                Files.createDirectories(path.getParent());
                //Create tasks.txt
                Files.createFile(path);
            }
            return taskArray;
        } catch (IOException e) {
            throw new BartenderBobException();
        }
    }

    /**
     * Parses a task from a string and returns the task.
     *
     * @param taskString String representation of the task.
     * @return Task
     */
    private Task parseTaskFromString(String taskString) {
        String[] taskStringComponents = taskString.split(" \\| ");
        String taskType = taskStringComponents[0];
        String taskStatus = taskStringComponents[1];
        boolean isDone = taskStatus.equals("X");
        String description = taskStringComponents[2];

        if (taskType.equals("T")) {
            return new ToDo(description, isDone);
        } else if (taskType.equals("D")) {
            String dueDate = taskStringComponents[3];
            dueDate = convertDateFormat(dueDate);
            return new Deadline(description, dueDate, isDone);
        } else if (taskType.equals("E")) {
            String fromDate = taskStringComponents[3];
            fromDate = convertDateFormat(fromDate);
            String toDate = taskStringComponents[4];
            toDate = convertDateFormat(toDate);
            return new Event(description, fromDate, toDate, isDone);
        }
        return null;
    }

    /**
     * Saves changes to the files in the hard disk
     * especially after unmarking, marking and deleting them.
     *
     * @param tasks The tasks that the user has inputted to BartenderBob.
     */
    public void saveChanges(ArrayList<Task> tasks) {
        try {
            assert filePath != null : "File path cannot be null";
            assert tasks != null : "Task cannot be null";
            Path path = Paths.get(filePath);
            Files.write(path, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task : tasks) {
                saveTask(task);
            }
        } catch (IOException e) {
            ui.showSaveChangesError();
        }

    }

    /**
     * Converts date format from MMM dd yyyy to yyyy-MM-dd
     *
     * @param oldDateFormat Date which is of MMM dd yyyy format.
     * @return Date which is of yyyy-MM-dd format.
     */
    private String convertDateFormat(String oldDateFormat) {
        //The storage and show() method stores the date as "MMM dd yyyy"
        //however, the constructor for deadline and event takes in a date
        //as "yyyy-MM-dd". That's why we need this method when loading from
        //storage into the chatbot.
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        // Parse the original string to LocalDate
        LocalDate localDate = LocalDate.parse(oldDateFormat, inputFormatter);

        // Define the formatter for the output pattern
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the LocalDate to the desired output pattern
        return localDate.format(outputFormatter);
    }

    /**
     * Saves tasks to the storage file specified by the filepath,
     * especially after adding them to the tasks list.
     *
     * @param task The task that we are saving to the storage file.
     */
    public void saveTask(Task task) {
        try {
            assert task != null : "Task cannot be null";
            Path path = Paths.get(filePath);
            String taskString = task.show();
            String typeOfTask = taskString.substring(1, 2);
            String taskStatus = taskString.substring(4, 5);
            String saveEntry = createSaveEntry(typeOfTask, taskString, taskStatus);
            Files.write(path, (saveEntry + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            ui.showSaveTasksError();
        }
    }
    /**
     * Creates a save entry for the task to be saved into the storage file.
     *
     * @param typeOfTask The type of task.
     * @param taskString The string representation of the task.
     * @param taskStatus The status of the task.
     * @return The save entry for the task.
     */
    private String createSaveEntry(String typeOfTask, String taskString, String taskStatus) {
        if (typeOfTask.equals("T")) {
            return createTodoSaveEntry(taskString, taskStatus);
        } else if (typeOfTask.equals("D")) {
            return createDeadlineSaveEntry(taskString, taskStatus);
        } else if (typeOfTask.equals("E")) {
            return createEventSaveEntry(taskString, taskStatus);
        }
        return null;
    }
    /**
     * Creates a save entry for the todo task to be saved into the storage file.
     *
     * @param taskString The string representation of the task.
     * @param taskStatus The status of the task.
     * @return The save entry for the todo task.
     */
    private String createTodoSaveEntry(String taskString, String taskStatus) {
        String taskDescription = taskString.substring(7);
        return "T" + " | " + taskStatus + " | " + taskDescription;
    }
    /**
     * Creates a save entry for the deadline task to be saved into the storage file.
     *
     * @param taskString The string representation of the task.
     * @param taskStatus The status of the task.
     * @return The save entry for the deadline task.
     */
    private String createDeadlineSaveEntry(String taskString, String taskStatus) {
        int startIndex = taskString.indexOf("(by: ");
        int endIndex = taskString.indexOf(")");
        String taskDescription = taskString.substring(7, startIndex - 1);
        String deadline = taskString.substring(startIndex + 5, endIndex);
        return "D" + " | " + taskStatus + " | " + taskDescription
                + " | " + deadline;
    }
    /**
     * Creates a save entry for the event task to be saved into the storage file.
     *
     * @param taskString The string representation of the task.
     * @param taskStatus The status of the task.
     * @return The save entry for the event task.
     */
    private String createEventSaveEntry(String taskString, String taskStatus) {
        int startIndex = taskString.indexOf("(from: ");
        String taskDescription = taskString.substring(7, startIndex - 1);
        int endIndex = taskString.indexOf(" to:");
        String fromDate = taskString.substring(startIndex + 7, endIndex);
        startIndex = taskString.indexOf(")");
        String toDate = taskString.substring(endIndex + 5, startIndex);
        return "E" + " | " + taskStatus + " | " + taskDescription
                + " | " + fromDate + " | " + toDate;
    }
}
