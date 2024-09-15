package jayne;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jayne.task.Task;
/**
 * Handles file operations for Jayne application, including saving and loading tasks to and from a file.
 */
public class Storage {
    private String filepath;
    /**
     * Renames the file specified by the filepath if it exists.
     * If the file exists, it is renamed with a unique name to avoid overwriting.
     */
    public Storage(String filepath) {
        assert filepath != null : "File path cannot be null";
        this.filepath = filepath;
    }
    /**
     * Renames the file specified by the filepath if it exists.
     * If the file exists, it is renamed with a unique name to avoid overwriting.
     */
    public void renameFileIfExists() {
        Path path = Paths.get(filepath);
        try {
            if (Files.exists(path)) {
                String newFileName = getNewFileName(path);
                Files.move(path, path.resolveSibling(newFileName));
                System.out.println(filepath + " has been renamed to " + newFileName);
            } else {
                System.out.println(filepath + " does not exist, no need to rename.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while renaming the file: " + e.getMessage());
        }
    }
    /**
     * Generates a new file name based on the original file path.
     * It appends a counter to the file name to avoid conflicts with existing files.
     *
     * @param originalPath the original file path.
     * @return a new file name that does not conflict with existing files.
     */
    public String getNewFileName(Path originalPath) {
        assert originalPath != null : "input path cannot be null";
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
    /**
     * Saves the list of tasks to the file specified by the filepath.
     * Each task is converted into a format suitable for file storage.
     *
     * @param taskArray the list of tasks to be saved.
     */
    public void saveTasks(List<Task> taskArray) throws JayneException {
        try {
            Path path = Paths.get(filepath);
            Files.createDirectories(path.getParent());

            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            for (Task task : taskArray) {
                bw.write(task.toFileFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new JayneException("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }
    /**
     * Loads tasks from the file specified by the filepath into the provided list.
     * Each line in the file is converted back into a task object.
     *
     * @param taskArray the list to load the tasks into.
     */
    public void loadTasks(List<Task> taskArray) throws JayneException {
        try {
            Path path = Paths.get(filepath);
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
                    throw new JayneException("Error parsing line from file: " + line);
                }
            }
        } catch (IOException e) {
            throw new JayneException("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }
    /**
     * Deletes the file specified by the filepath if it exists.
     */
    public void deleteFile() {
        Path path = Paths.get(filepath);
        try {
            if (Files.deleteIfExists(path)) {
                System.out.println(filepath + " has been deleted.");
            } else {
                System.out.println(filepath + " does not exist, no need to delete.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the file: " + e.getMessage());
        }
    }
}
