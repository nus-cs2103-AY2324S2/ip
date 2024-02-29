package bob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bob.exception.LoadingException;
import bob.exception.SavingException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Period;
import bob.task.Task;
import bob.task.Todo;

/**
 * Represents the storage for the program. A <code>Storage</code> object corresponds to
 * a file acting as the storage for the program.
 */
public class Storage {
    private static final String DATA_DIR = "data";
    public static final String DATA_PATH = DATA_DIR + "/bob.txt";

    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private File dataFile;

    /**
     * Retrieves the data file to be loaded from hard disk,
     * creating the required directory and/or the file if necessary.
     *
     * @param dataPath The path on which the data file lives.
     * @return The retrieved data file to be loaded from hard disk.
     * @throws IOException If there has been an error creating the directory and/or file.
     */
    private static File createOrRetrieve(String dataPath) throws IOException {
        Path path = Paths.get(dataPath);
        Path parent = path.getParent();
        Files.createDirectories(parent);
        if (Files.notExists(path)) {
            return Files.createFile(path).toFile();
        }
        return path.toFile();
    }

    private static boolean getDone(String parameter) throws LoadingException {
        if (!parameter.equals("true") && !parameter.equals("false")) {
            throw new LoadingException("invalid value for isDone detected");
        }
        return Boolean.parseBoolean(parameter);
    }

    /**
     * Makes sense of a line in the data file to be converted into a task.
     *
     * @param line A line in the data file.
     * @return The task represented by the given line.
     * @throws LoadingException If the given line is of incorrect format and does not represent any task.
     */
    // TODO: Once extractParameter is rewritten, we can move this to Parser
    private static Task parseStorageLine(String line) throws LoadingException {
        String[] parameters = line.split(" \\| ");

        // The first part of the split indicates the task type
        String taskType = parameters[0];

        // The second part of the split indicates whether the task is done
        boolean isDone = getDone(parameters[1]);

        try {
            Task task = getTask(parameters, taskType);
            task.setDone(isDone);
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    private static Task getTask(String[] parameters, String taskType) throws LoadingException {
        // The third part of the split indicates the task description
        String description = parameters[2];

        // Parse the remaining parts of the split according to the task type
        Task task;
        switch (taskType) {
        case Todo.STORAGE_INDICATOR:
            task = new Todo(description);
            break;
        case Deadline.STORAGE_INDICATOR:
            LocalDateTime by = LocalDateTime.parse(parameters[3], FORMATTER_DATE_TIME);
            task = new Deadline(description, by);
            break;
        case Event.STORAGE_INDICATOR:
            LocalDateTime from = LocalDateTime.parse(parameters[3], FORMATTER_DATE_TIME);
            LocalDateTime to = LocalDateTime.parse(parameters[4], FORMATTER_DATE_TIME);
            task = new Event(description, from, to);
            break;
        case Period.STORAGE_INDICATOR:
            LocalDateTime start = LocalDateTime.parse(parameters[3], FORMATTER_DATE_TIME);
            LocalDateTime end = LocalDateTime.parse(parameters[4], FORMATTER_DATE_TIME);
            task = new Period(description, start, end);
            break;
        default:
            throw new LoadingException("i don't recognise this storage indicator");
        }
        return task;
    }

    /**
     * Utility method to format the given <code>LocalDateTime</code>
     * using the predefined <code>DateTimeFormatter</code>.
     *
     * @param dateTime The given <code>LocalDateTime</code>.
     * @return The formatted string to be stored in the storage.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_DATE_TIME);
    }

    private ArrayList<Task> loadData() throws IOException, LoadingException {
        try (Scanner s = new Scanner(dataFile)) {
            ArrayList<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                tasks.add(parseStorageLine(s.nextLine()));
            }
            return tasks;
        }
    }

    /**
     * Loads data from hard disk.
     *
     * @param dataPath The file path in which the data to be loaded is stored.
     * @return A list of tasks loaded from hard disk.
     * @throws LoadingException If an error occurred while loading the tasks from hard disk.
     */
    public ArrayList<Task> load(String dataPath) throws LoadingException {
        try {
            dataFile = createOrRetrieve(dataPath);
            return loadData();
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    private void writeTask(BufferedWriter bw, Task task) throws IOException {
        bw.write(task.getStorageFormat());
        bw.newLine();
    }

    /**
     * Saves a new task to hard disk.
     *
     * @param task The new task to be saved to hard disk.
     * @throws SavingException If an error occurred while saving the new task to hard disk.
     */
    public void saveTask(Task task) throws SavingException {
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile(), true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                writeTask(bw, task);
            }
        } catch (IOException e) {
            throw new SavingException(e.getMessage());
        }
    }

    private void rewriteFile(BufferedWriter bw, ArrayList<Task> tasks) throws IOException {
        for (Task task : tasks) {
            writeTask(bw, task);
        }
        bw.flush();
    }

    /**
     * Update the storage with a specified list of tasks.
     *
     * @param tasks The list of tasks to update the storage with.
     * @throws SavingException If an error occurred while saving the tasks in the list of tasks to hard disk.
     */
    public void refresh(ArrayList<Task> tasks) throws SavingException {
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                rewriteFile(bw, tasks);
            }
        } catch (IOException e) {
            throw new SavingException(e.getMessage());
        }
    }
}
