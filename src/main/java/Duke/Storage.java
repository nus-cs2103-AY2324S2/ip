package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Task.Task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;
import Duke.Task.TaskList;
import Duke.Exception.DukeException;

public class Storage {
    // Default file path for storage
    private static String filePath = "./data/tasks.txt";
    private static final Path DIRECTORY_PATH = Paths.get("./data");
    private static final Path FILE_PATH = Paths.get(filePath);

    public Storage(String filePath) {
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
    public static void save(TaskList tasksList) {
        ArrayList<Task> arrayListOfTasks = tasksList.getArrayList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : arrayListOfTasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> load() throws DukeException {
        ArrayList arrayTaskList = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data;
            while ((data = reader.readLine()) != null) {
                Task task = Task.parseFromFileString(data);
                if (task != null) {
                    arrayTaskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new InvalidCommandException("No existing file. Starting with an empty task list.");
        } catch (IOException e) {
            throw new InvalidCommandException("No existing file. Starting with an empty task list.");
        }

        return arrayTaskList;
    }
}
