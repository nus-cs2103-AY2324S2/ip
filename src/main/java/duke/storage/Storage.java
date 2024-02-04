package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Storage {

    private String filePath;

    private String directoryPath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.directoryPath = "./data/";
    }

    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        // Create a new file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating a new File: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromString(line);
                    tasks.add(task);
                }
            } catch (IOException e) {
                throw new DukeException("Error loading tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) throws DukeException {
        File directory = new File(directoryPath);

        // Create a directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Write the tasks in the list into the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            ArrayList<Task> taskList = tasks.getTasks();

            for (Task task : taskList) {
                writer.write(task.toString());
                writer.newLine();
            }

            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }


}
