package gpt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the storage of tasks.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createFolderAndFile(filePath);
    }

    /**
     * Creates the data folder and file if they do not exist.
     *
     * @param filePath The file path of the data file.
     */
    static void createFolderAndFile(String filePath) {
        try {
            File dataFolder = new File("./data/");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            assert dataFolder.exists() : "Data folder should exist";
            File dataFile = new File(filePath);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            assert dataFile.exists() : "Data file should exist";
        } catch (IOException e) {
            System.out.println("Error creating data folder and file: " + e.getMessage());
        }

    }

    /**
     * Loads tasks from the data file.
     *
     * @return The TaskList containing the loaded tasks.
     */
    public TaskList loadTasks() {
        TaskList loadedTL = new TaskList(new ArrayList<>());
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] taskData = line.split(" \\| ");
                if (taskData.length >= 4) {
                    TaskType type = TaskType.valueOf(taskData[0]);
                    String name = taskData[1];
                    Boolean isDone = Integer.parseInt(taskData[2]) == 1;
                    String startTime = taskData[3];
                    String endTime = taskData.length > 4 ? taskData[4] : "";

                    switch(type) {
                    case T:
                        loadedTL.addTask(new Task(name, type, isDone));
                        break;
                    case D:
                        loadedTL.addTask(new Task(name, type, isDone, startTime));
                        break;
                    case E:
                        loadedTL.addTask(new Task(name, type, isDone, startTime, endTime));
                        break;
                    default:
                        loadedTL.addTask(new Task(name, type, isDone));
                        break;

                    }

                } else {
                    System.out.println("Warning: Ignored corrupted line in the file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return loadedTL;
    }

    /**
     * Saves tasks to the data file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try (FileWriter fw = new FileWriter(this.filePath)) {
            for (Task task : taskList.getTasks()) {
                assert task.getTaskType() != null : "Task type should not be null"; // Check that task type is not null
                assert task.getName() != null : "Task name should not be null"; // Check that task name is not null
                System.out.println(task);
                fw.write(task.getTaskType().name() + " | " + task.getName() + " | " + (task.isDone() ? 1 : 0)
                        + " | " + task.getStartDateString() + " | " + task.getEndDateString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
