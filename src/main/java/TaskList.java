import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.util.*;
//test
public class TaskList {
    private final List<Task> taskArray;

    private static  final String FILE_PATH = "./data/jayne.txt";
    private int taskCount;

    public TaskList() {
        this.taskArray = new ArrayList<>();
        //checkAndDeleteFile();
        renameFileIfExists();
        loadTasks();
    }

    private void checkAndDeleteFile() {
        Path path = Paths.get(FILE_PATH);
        try {
            // Check if the file exists
            if (Files.exists(path)) {
                // Delete the file
                Files.delete(path);
                System.out.println(FILE_PATH + " has been detected and deleted.");
            } else {
                System.out.println(FILE_PATH + " does not exist, no need to delete.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking/deleting the file: " + e.getMessage());
        }
    }

    private void renameFileIfExists() {
        Path path = Paths.get(FILE_PATH);
        try {
            if (Files.exists(path)) {
                String newFileName = getNewFileName(path);
                Files.move(path, path.resolveSibling(newFileName));
                System.out.println(FILE_PATH + " has been renamed to " + newFileName);
            } else {
                System.out.println(FILE_PATH + " does not exist, no need to rename.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while renaming the file: " + e.getMessage());
        }
    }

    private String getNewFileName(Path originalPath) {
        int counter = 1;
        String originalFileName = originalPath.getFileName().toString();
        String fileWithoutExtension = originalFileName.replaceFirst("[.][^.]+$", "");
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        Path parentDir = originalPath.getParent();

        while (true) {
            String newName = fileWithoutExtension + "(" + counter + ")" + extension;
            Path newPath = parentDir.resolve(newName);
            if (!Files.exists(newPath)) {
                return newName;
            }
            counter++;
        }
    }

    public void saveTasks() {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : taskArray) {
                bw.write(task.toFileFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try {
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                return; // File doesn't exist, nothing to load
            }

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                try {
                    Task task = Task.fromFileFormat(line);
                    if (task != null) {
                        taskArray.add(task);
                    }
                } catch (JayneException e) {
                    System.out.println("Error parsing line from file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task deleteTask(int taskNumber) throws JayneException {
        if (taskNumber < 1 || taskNumber > taskArray.size()) {
            throw new JayneException("Task number " + taskNumber + " does not exist.");
        }
        this.taskCount = taskCount - 1;
        return taskArray.remove(taskNumber - 1);
    }

    public void addTask(Task task) {
        taskArray.add(task);
        this.taskCount = taskCount + 1;
    }

    public Task getTask(int index) {
        if (index >= 0 && index <= taskArray.size()) {
            return taskArray.get(index - 1);
        }
        System.out.println("Index invalid");
        return null;
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsDone();
        }
    }

    public void markTaskAsNotDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsNotDone();
        }
    }

    public void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println((i + 1) + ". " + taskArray.get(i).toString());
        }
    }
}
