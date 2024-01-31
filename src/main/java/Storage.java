import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

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

    public String getNewFileName(Path originalPath) {
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

    public void saveTasks(List<Task> taskArray) {
        try {
            Path path = Paths.get(filepath);
            Files.createDirectories(path.getParent());

            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
            for (Task task : taskArray) {
                bw.write(task.toFileFormat() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasks(List<Task> taskArray) {
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
                    System.out.println("Error parsing line from file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }
}
