import objects.TaskList;
import view.EncaseLines;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
public class Storage {

    private static final String FILE_PATH = "./src/main/data/duke.txt";

    public static boolean fileExists() {
        File file = new File(FILE_PATH);

        return file.exists();
    }

    public static void save(TaskList taskList) {
        try (FileOutputStream fileOutput = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(taskList);

        } catch (IOException e) {
            EncaseLines.display("Error saving task list: " + e.getMessage());

        }
    }

    public static TaskList load() {
        if (!fileExists()) {
            EncaseLines.display("Data not found, creating new file...");

            return new TaskList();
        }

        TaskList tasks = null;

        try (FileInputStream fileInput = new FileInputStream(FILE_PATH);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            tasks = (TaskList) objectInput.readObject();

        } catch (IOException | ClassNotFoundException e) {
            EncaseLines.display("Error loading data");

        }

        EncaseLines.display("Existing data found, loading...");

        return tasks;
    }
}
