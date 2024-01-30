import objects.TaskList;

import java.io.*;

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
            System.err.println("Error saving task list: " + e.getMessage());

        }
    }

    public static TaskList load() {
        if (!fileExists()) {
            System.out.println("Data not found, creating new file...");

            return new TaskList();
        }

        TaskList tasks = null;

        try (FileInputStream fileInput = new FileInputStream(FILE_PATH);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            tasks = (TaskList) objectInput.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data");

        }

        System.out.println("Existing data found, loading...");

        return tasks;
    }
}
