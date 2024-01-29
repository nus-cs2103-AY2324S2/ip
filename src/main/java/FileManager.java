import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileManager {
    static final Path DIRECTORY_PATH = Paths.get("./data");
    private String fileName;
    private Path filePath;


    public FileManager(String fileName) {
        this.fileName = fileName;
        filePath = DIRECTORY_PATH.resolve(fileName + ".txt");
        createFile();
    }

    private void createFile() {
        try {
            if (!Files.exists(DIRECTORY_PATH)) {
                Files.createDirectories(DIRECTORY_PATH);
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException io) {
            System.out.println("There is an error when creating file. The error is " + io.getMessage());
        }
    }


    void writeArrayListToFile(ArrayList<Task> tasks, boolean isOverwrite) {
        try {
            if (isOverwrite) {
                Files.write(filePath, convertTaskToString(tasks).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.write(filePath, convertTaskToString(tasks).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException io) {
            System.out.println("There is an error when writing to file. The error is " + io.getMessage());
        }
    }


    private String convertTaskToString(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (Task task: tasks) {
            result.append(task.toString());
            result.append(System.getProperty("line.separator"));
        }
        return result.toString();
    }




}
