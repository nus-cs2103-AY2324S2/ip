package tommy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import tommy.exception.InvalidFileException;
import tommy.task.Task;
import tommy.task.TaskList;

/**
 * Represents the database to store the past logs of taskList.
 */
public class Storage {

    // Default file path for storage
    private static String filePath = "./data/tasks.txt";
    private static final Path DIRECTORY_PATH = Paths.get("./data");
    private static final Path FILE_PATH = Paths.get(filePath);

    /**
     * Constructor for the storage.
     * <p>
     * The constructor creates a new file and/or directory if the specified
     * path does not exist.
     *
     * @param filePath String of the path of the file to retrieve the past log taskList from.
     */
    public Storage(String filePath) {
        assert !filePath.isBlank() : "the file path should not be blank";

        this.filePath = filePath;

        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the updated taskList to the storage by writing to the file.
     *
     * @param taskList Updated taskList to be written to the storage file.
     */
    public static void save(TaskList taskList) {
        ArrayList<Task> arrayListOfTasks = taskList.getArrayList();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : arrayListOfTasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Retrieves the past log of taskList if found, else it will start with an empty taskList.
     *
     * @return Stored taskList from past log or new list if not found.
     * @throws InvalidFileException
     */
    public static ArrayList<Task> load() throws InvalidFileException {
        ArrayList arrayTaskList = new ArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data = reader.readLine();

            while (data != null) {
                Task task = Task.parseFromFileString(data);
                if (task == null) {
                    break;
                }
                arrayTaskList.add(task);
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new InvalidFileException("No existing file. Starting with an empty task list.");
        } catch (IOException e) {
            throw new InvalidFileException("No existing file. Starting with an empty task list.");
        }
        return arrayTaskList;
    }
}
