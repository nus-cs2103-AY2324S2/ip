package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A utility class for handling storage operations, such as loading and saving tasks to a file.
 *
 * <p>The {@code Storage} class provides methods for reading tasks from a file and writing tasks
 * to a file. It handles the conversion between task objects and their string representations
 * suitable for storage in a file. It also defines the file path where tasks are stored.</p>
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String filePath;
    private final Path folder;
    private final File storageFile;

    public Storage() {
        this(FILE_PATH);
    }

    /**
     * Constructor of that takes a path of the file and specify the file for
     * storage of the given path.
     *
     * @param filePath The path of the storage file
     */
    public Storage(String filePath) {
        // Get the absolute path of the root directory
        String rootPath = Paths.get("").toAbsolutePath().toString();
        // Create the full path to the storage file by concatenating the root path and the file path
        this.filePath = Paths.get(rootPath, filePath).toString();

        // Get the path of the file
        Path path = Paths.get(filePath);
        // Get the number of elements in the path
        int len = path.getNameCount();

        // Get the parent folder of the storage file by getting the path of all elements except the last one
        this.folder = Paths.get(rootPath, path.subpath(0, len - 1).toString());
        // Create a new file object for the storage file
        this.storageFile = new File(this.filePath);
    }



    /**
     * Loads tasks from the storage file into a TaskList object.
     *
     * @return A {@link TaskList} containing tasks loaded from the storage file.
     * @throws FileNotFoundException If the storage file is not found.
     */
    public TaskList load() throws FileNotFoundException {
        TaskList list = new TaskList();

        // Check if directory exists, if not, create it
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Check if file exists, if not, return empty task list
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return list;
        }

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTask(line);
                list.addTask(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // Handle the FileNotFoundException if it occurs
            System.err.println("Error: Unable to read tasks from file: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    private Task parseTask(String line) {
        String[] parts = line.split("\\s\\|\\s");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("[X]");
        String description = parts[2];

        switch (taskType) {
        case "[T]":
            return new ToDo(description, isDone);
        case "[D]":
            LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
            return new Deadline(description, by, isDone);
        case "[E]":
            LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
            return new Event(description, from, to, isDone);
        default:
            throw new IllegalArgumentException("Invalid task type found in storage file.");
        }
    }

    /**
     * Saves tasks from a TaskList object to the storage file.
     *
     * @param list The {@link TaskList} containing tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the storage file.
     */
    public void save(TaskList list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Task task : list.getAllTasks()) {
            sb.append(task.storageString()).append(System.lineSeparator());
        }
        writeToFile(FILE_PATH, sb.toString());
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(textToAdd);
        }
    }
}
