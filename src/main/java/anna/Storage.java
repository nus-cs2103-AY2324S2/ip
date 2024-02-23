package anna;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
* The Storage class handles the loading and saving of task data to a file.
*
* The format of the saved data is a serialization of {@link Task} objects, where
* each task is separated by "<2>", and task details are further divided using
* "<0>" and "<1>" as separators.
*
* @see Task
* @see TaskList
*/
public class Storage {

    private String fileName;
    private String folderPath;

    /**
    * Constructs a Storage object with the specified folder path and file name.
    *
    * @param folderPath The folder path for storing the task data file.
    * @param fileName   The name of the file for storing the task data.
    */
    Storage(String folderPath, String fileName) {
        this.fileName = fileName;
        this.folderPath = folderPath;
    }

    private File getFileHandle() throws AnnaException {
        try {
            Path baseFolder = Paths.get(System.getProperty("user.dir"));
            Path dataFolder = baseFolder.resolve(folderPath);
            if (!Files.exists(dataFolder) || !Files.isDirectory(dataFolder)) {
                dataFolder.toFile().mkdirs();
            }
            Path taskFile = dataFolder.resolve(fileName);
            if (!Files.exists(taskFile)) {
                taskFile.toFile().createNewFile();
            }
            return taskFile.toFile();
        } catch (IOException e) {
            throw new AnnaException("Storage.getFileHandle: %s" + e);
        }
    }

    /**
    * Loads tasks from the task data file.
    *
    * @return An ArrayList of Task objects representing the loaded tasks.
    * @throws AnnaException
    */
    ArrayList<Task> load() throws AnnaException {
        try {
            FileInputStream f = new FileInputStream(getFileHandle());
            String serialised = new String(f.readAllBytes());
            f.close();
            String[] tasksString = serialised.split("<2>");
            return Arrays
            .stream(tasksString)
            .filter(s -> s.length() > 0)
            .map(s -> TaskFactory.deserealiseTask(s))
            .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new AnnaException("Storage.load: " + e.getMessage());
        }
    }

    /**
    * Saves the given ArrayList of tasks to the task data file.
    *
    * @param storedTasks The ArrayList of Task objects to be saved.
    * @throws AnnaException
    */
    void save(ArrayList<Task> storedTasks) throws AnnaException {
        try {
            String serialised = storedTasks
                .stream()
                .<String>map(t -> t.serialise())
                .collect(Collectors.joining("<2>"));

            FileWriter f = new FileWriter(getFileHandle());
            f.write(serialised);
            f.close();
        } catch (IOException e) {
            throw new AnnaException("Storage.save: " + e.getMessage());
        }
    }
}
