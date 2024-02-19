package yapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import exception.YapperException;

/**
 * Handles the loading and saving of data for the {@link TaskList} between the program and the local files.
 */
public class FileManager {
    private final Parser parser;
    public FileManager(Parser p) {
        parser = p;
        parser.setFileManager(this);
    }

    /**
     * Reads the task list data from the local file and sends it to the {@link Parser} to parse the data.
     */
    public void loadTasks() {
        Path dir = Paths.get("data");
        try {
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
        } catch (IOException e) {
            System.out.println("Error in the IO when creating data dir");
        }

        Path file = Paths.get("data/taskData.txt");
        try {
            if (!Files.exists(file)) {
                file = Files.createFile(file);
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                parser.parseDataToTask(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error in the IO when creating taskData file");
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }
    }

    // can have a hasChanged check with the taskList to prevent rewriting data even if no changed occurred
    /**
     * Saves the task list data received from {@link Parser} and writes it to the local file.
     */
    public void saveTasks() throws YapperException {
        try (FileWriter fw = new FileWriter("data/taskData.txt")) {
            fw.write(parser.parseTaskToData());
        } catch (IOException e) {
            throw (new YapperException("IO Exception when saving data\n"));
        }
    }
}
