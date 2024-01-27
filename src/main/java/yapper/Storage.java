package yapper;
import yapper.command.Parser;
import yapper.tasks.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns list of tasks that has been recorded in the file based on user input.
     *
     * @return List of tasks as recorded in the file.
     */
    public List<Task> load() throws YapperException {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Parser.parseTask(line));
            }
        } catch (IOException e) {
            throw new YapperException("Error loading tasks from file. Creating a new task list.");
        }
        return tasks;
    }

    /**
     * Saves the tasks from user input in the file.
     *
     * @param tasks yapper.tasks.Task input extracted from user input.
     */
    public void saveTasks(List<Task> tasks) throws YapperException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new YapperException("Error saving tasks to file :(");
        }
    }
}
