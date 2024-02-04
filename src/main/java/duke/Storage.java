package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    protected static String dataPath = "./data/duke.txt";
    protected static TaskList loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataPath))) {
            return (TaskList) ois.readObject();
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            return new TaskList();
        }
    }

    protected static void saveTasks() {
        try {
            // Ensure the parent directories exist
            File file = new File(dataPath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            // Save the tasks
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(Duke.lst);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
