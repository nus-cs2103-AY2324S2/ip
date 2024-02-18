package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents storage component of Duke.
 */
public class Storage {
    public static final String DATA_FILE_DIRECTORY_PATH = "./Data";
    private static final String DATA_FILE_PATH = "Data/savedTasks.txt";
    private static Storage instance = null;
    private TaskList taskList = null;

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void initStorage() {
        taskList = TaskList.getInstance();
    }

    /**
     * Converts dateTime to storage format.
     *
     * @param localDateTime Input dataTime.
     * @return String converted from input dataTime in storage format.
     */
    public static String convertDateTimeForStorage(LocalDateTime localDateTime) {
        return Parser.convertDateTimeToCommandFormat(localDateTime);
    }

    /**
     * Loads taskList from memory.
     *
     * @throws FileNotFoundException If data file not found.
     */
    public void loadFromMemory() throws FileNotFoundException {
        File file = new File(DATA_FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadSingleRowOfData(scanner.nextLine());
        }
    }

    /**
     * Saves taskList to memory.
     */
    public void saveToMemory() {
        try {
            String dataToWrite = "";
            for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
                dataToWrite += taskList.getTask(i).convertToDataRow();
                if (i < taskList.getNumOfTasks()) {
                    dataToWrite += System.lineSeparator();
                }
            }
            writeToFile(DATA_FILE_PATH, dataToWrite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a file to store data.
     */
    public void createDataFile() {
        // Specify the path for the new directory
        String directoryPath = DATA_FILE_DIRECTORY_PATH;

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Use mkdirs() to create the directory and its parent directories if they don't exist
        boolean success = directory.mkdirs();

        if (success) {
            System.out.println("Data directory created successfully");
        } else {
            System.out.println("Failed to create data directory");
        }
    }

    private void loadSingleRowOfData(String s) {
        taskList.addTask(Task.convertDataToTask(s));
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}