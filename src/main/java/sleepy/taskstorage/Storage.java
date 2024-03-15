package sleepy.taskstorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.util.Pair;
import sleepy.tasks.Task;
import sleepy.tools.ResponseHandler;

/**
 * This class helps to store the task list in the hard disk.
 *
 * @author kjw142857
 */
public class Storage {
    private File hardDiskFile;

    /**
     * Constructor for the Storage class.
     */
    public Storage() {
        try {
            String appDirectory = System.getProperty("user.dir");
            // For multiple-OS support, File.separator is used instead of "/".
            String filePath = appDirectory + File.separator + "storage"
                    + File.separator + "HardDiskStorage.txt";
            hardDiskFile = new File(filePath);
            if (!hardDiskFile.exists()) {
                Files.createDirectories(hardDiskFile.toPath().getParent());
                hardDiskFile.createNewFile();
            }
        } catch (IOException i) {
            ResponseHandler.appendLineToResponse(
                    "Sleepy encountered a serious error! Please restart the application :("
            );
        }
    }

    /**
     * Returns the tasks read from the file as an ArrayList of pairs.
     * Each pair contains the string description of the task,
     * and a boolean indicating if it is done.
     *
     * @return Tasks read from the file.
     */
    public ArrayList<Pair<String, Boolean>> readFile() {
        ArrayList<Pair<String, Boolean>> tasksReadFromFile = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(hardDiskFile);
            while (scanner.hasNextLine()) {
                String taskDescription = scanner.nextLine();
                Boolean isDone = Boolean.valueOf(scanner.nextLine());
                Pair<String, Boolean> nextTask = new Pair<>(taskDescription, isDone);
                tasksReadFromFile.add(nextTask);
            }
        } catch (FileNotFoundException f) {
            ResponseHandler.appendLineToResponse("Sleepy encountered a bug and could not recover your data!");
        } catch (NoSuchElementException n) {
            ResponseHandler.appendLineToResponse("Sleepy encountered a problem upon startup!"
                    + " Your saved data has unfortunately been lost.");
            // Clear corrupted data
            tasksReadFromFile.clear();
        }
        return tasksReadFromFile;
    }

    /**
     * Saves the tasks in the hard disk.
     *
     * @param tasks Tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(hardDiskFile, false);
            for (Task task : tasks) {
                // Write description of task
                fileWriter.write(task.getRawDescription() + "\n");
                // Write whether task is done
                fileWriter.write(task.isDone() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException i) {
            ResponseHandler.appendLineToResponse("Sleepy couldn't save your data!"
                    + " Please restart the application :(");
        }
    }
}
