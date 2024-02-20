package andelu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;



/**
 * A Storage class that acts as a File Manager to handle all file's read and write method.
 */
public class Storage {

    /** A fixed directory for text files. */
    static final Path DIRECTORY_PATH = Paths.get("./data");

    /** The name of the file. */
    private String fileName;

    /** The path for the file. */
    private Path filePath;


    /**
     * A constructor to create new Storage Object.
     *
     * @param fileName The file name to be loaded or stored.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        filePath = DIRECTORY_PATH.resolve(fileName + ".txt");
        createFile();
    }

    /**
     * Creates directory and file if it doesn't exist.
     */
    private void createFile() {
        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }
            assert Files.exists(DIRECTORY_PATH) : "Error: Directory not found.";

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            assert Files.exists(filePath) : "Error: file not found.";
        } catch (IOException io) {
            Ui ui = new Ui();
            ui.printAnyStatement("There is an error when creating file. The error is " + io.getMessage());
        }
    }

    /**
     * Writes tasks to the file.
     *
     * @param tasks the tasks that need to be written.
     * @param isOverwrite True for overwrite the previous data. False for appending the data.
     */
    public void writeArrayListToFile(ArrayList<Task> tasks, boolean isOverwrite) {
        try {
            assert Files.exists(filePath) : "Error: file not found when writing.";
            if (isOverwrite) {
                Files.write(filePath,
                        convertTasksToString(tasks).getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.write(filePath,
                        convertTasksToString(tasks).getBytes(),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException io) {
            Ui ui = new Ui();
            ui.printAnyStatement("There is an error when writing to file. The error is " + io.getMessage());
        }
    }

    /**
     * Loads Tasks from the file.
     *
     * @return an ArrayList of Tasks.
     */
    public ArrayList<Task> loadTasksFromFile() throws AndeluException {
        ArrayList<Task> result = new ArrayList<>();

        try {
            List<String> fileContentLines = Files.readAllLines(filePath);
            result = convertStringListToTasks(fileContentLines);
        } catch (IOException io) {
            throw new AndeluException("There is an error when reading the file.");
        }

        return result;
    }

    /**
     * Converts an ArrayList of Tasks to String.
     *
     * @param tasks An arrayList of Tasks.
     * @return String format of the list of Tasks.
     */
    private String convertTasksToString(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        for (Task task: tasks) {
            if (task instanceof ToDo) {
                result.append("T | ");
                result.append(task.getStatusIcon().equals("X") ? "1 | " : "0 | ");
                result.append(task.getDescription() + " | ");
                result.append(task.getPriorityLevel());
                result.append(System.getProperty("line.separator"));
            } else if (task instanceof Deadline) {
                result.append("D | ");
                Deadline deadlineTask = (Deadline) task;
                result.append(deadlineTask.getStatusIcon().equals("X") ? "1 | " : "0 | ");
                result.append(deadlineTask.getDescription() + " | ");
                result.append(deadlineTask.getBy() + " | ");
                result.append(task.getPriorityLevel());
                result.append(System.getProperty("line.separator"));
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                result.append("E | ");
                result.append(eventTask.getStatusIcon().equals("X") ? "1 | " : "0 | ");
                result.append(eventTask.getDescription() + " | ");
                result.append(eventTask.getStart() + " | ");
                result.append(eventTask.getEnd() + " | ");
                result.append(task.getPriorityLevel());
                result.append(System.getProperty("line.separator"));
            }

        }

        return result.toString();
    }

    /**
     * Converts the List of String to an ArrayList of Tasks.
     *
     * @param content the List of String to be converted.
     * @return an ArrayList of Tasks.
     */
    private ArrayList<Task> convertStringListToTasks(List<String> content) throws AndeluException {
        ArrayList<Task> fileTasks = new ArrayList<>();

        for (String i : content) {
            String[] stringAttributes = i.split("\\|");
            if (stringAttributes[0].trim().equals("T")) {
                fileTasks.add(new ToDo(
                        stringAttributes[2].trim(),
                        stringAttributes[1].trim().equals("1") ? true : false,
                        PriorityLevel.valueOf(stringAttributes[3].trim())));

            } else if (stringAttributes[0].trim().equals("D")) {
                fileTasks.add(new Deadline(
                        stringAttributes[2].trim(),
                        stringAttributes[1].trim().equals("1") ? true : false,
                        PriorityLevel.valueOf(stringAttributes[4].trim()),
                        DateTimeManager.convertStringToLocalDateTime(stringAttributes[3].trim())));

            } else if (stringAttributes[0].trim().equals("E")) {
                fileTasks.add(new Event(
                        stringAttributes[2].trim(),
                        stringAttributes[1].trim().equals("1") ? true : false,
                        PriorityLevel.valueOf(stringAttributes[5].trim()),
                        DateTimeManager.convertStringToLocalDateTime(stringAttributes[3].trim()),
                        DateTimeManager.convertStringToLocalDateTime(stringAttributes[4].trim())));
            }
        }

        return fileTasks;
    }

    /**
     * Converts the String content to an ArrayList of Tasks.
     *
     * @param content the String content to be converted.
     * @return an ArrayList of Tasks.
     */
    private ArrayList<Task> convertStringToTasks(String content) throws AndeluException {
        String[] individualStringTask = content.trim().split(System.lineSeparator());
        ArrayList<Task> fileTasks = new ArrayList<>();

        for (String i : individualStringTask) {
            String[] stringAttributes = i.split("|");
            if (stringAttributes[0].equals("T")) {
                fileTasks.add(new ToDo(
                        stringAttributes[2].trim(),
                        stringAttributes[1].equals("1") ? true : false,
                        PriorityLevel.valueOf(stringAttributes[3].trim())));

            } else if (stringAttributes[0].equals("D")) {
                fileTasks.add(new Deadline(
                        stringAttributes[2].trim(),
                        stringAttributes[1].equals("1") ? true : false,
                        PriorityLevel.valueOf(stringAttributes[3].trim()),
                        DateTimeManager.convertStringToLocalDateTime(stringAttributes[3].trim())));

            } else if (stringAttributes[0].equals("E")) {
                fileTasks.add(new Event(
                        stringAttributes[2].trim(),
                        stringAttributes[1].equals("1") ? true : false,
                        PriorityLevel.valueOf(stringAttributes[3].trim()),
                        DateTimeManager.convertStringToLocalDateTime(stringAttributes[3].trim()),
                        DateTimeManager.convertStringToLocalDateTime(stringAttributes[4].trim())));
            }
        }
        return fileTasks;
    }



}
