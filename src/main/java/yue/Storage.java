package yue;

import yue.tasks.Task;
import yue.tasks.TodoTask;
import yue.tasks.DeadlineTask;
import yue.tasks.EventTask;
import yue.tasks.EventTaskLoad;
import yue.tasks.DeadlineTaskLoad;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

/**
 * The Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return The list of tasks loaded from the file.
     * @throws YueException If there is an error loading tasks from the file.
     */
    public List<Task> load() throws YueException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                boolean created = file.createNewFile();
                if (!created) {
                    throw new YueException("Failed to create a new file: " + filePath);
                }
                // Now that the file is created, return an empty list of tasks
                return tasks;
            }
            if (!file.exists()) {
                // If the file doesn't exist, create a new one
                boolean created = file.createNewFile();
                if (!created) {
                    throw new YueException("Failed to create a new file: " + filePath);
                }
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                assert parts.length >= 3 : "Invalid task format: " + line;
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new TodoTask(parts[2]);
                        break;
                    case "D":
                        task = new DeadlineTaskLoad(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new EventTaskLoad(parts[2], parts[3]);
                        break;
                    default:
                        continue;
                        }
                        if (parts[1].equals("1")) {
                            task.markDone();
                        }
                        tasks.add(task);
                    }
                scanner.close();
        } catch (IOException e) {
            throw new YueException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }


    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws YueException If there is an error saving tasks to the file.
     */
    public void save(List<Task> tasks) throws YueException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                String taskType;
                if (task instanceof TodoTask) {
                    taskType = "T";
                } else if (task instanceof DeadlineTask) {
                    taskType = "D";
                } else if (task instanceof EventTask) {
                    taskType = "E";
                } else {
                    continue;
                }
                writer.write(String.format("%s | %d | %s", taskType, task.isMarked ? 1 : 0, task.getTask()));
                if (task instanceof DeadlineTask) {
                    writer.write(" | " + ((DeadlineTask) task).getDateTime());
                } else if (task instanceof EventTask) {
                    writer.write(" | " + ((EventTask) task).getDateTime());
                }
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new YueException("Error saving tasks to file: " + e.getMessage());
        }
    }
}


