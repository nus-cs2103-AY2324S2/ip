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

public class Storage {

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
    public static String convertDateTimeForStorage(LocalDateTime localDateTime) {
        return Parser.convertDateTimeToCommandFormat(localDateTime);
    }

    private static final String DATA_FILE_PATH = "Data/savedTasks.txt";
    public void loadFromMemory() throws FileNotFoundException {
        File file = new File(DATA_FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadSingleRowOfData(scanner.nextLine());
        }
    }

    private void loadSingleRowOfData(String s) {
        taskList.addTask(Task.convertDataToTask(s));
    }

    public void saveToMemory() {
        try {
            String dataToWrite = "";
            for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
                dataToWrite += taskList.getTask(i).convertToDataRow();
                if (i < taskList.getNumOfTasks()) dataToWrite += System.lineSeparator();
            }
            writeToFile(DATA_FILE_PATH, dataToWrite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createDataFile() {
        // Specify the path for the new directory
        String directoryPath = "./Data";

        // Create a File object representing the directory
        File directory = new File(directoryPath);

        // Use mkdirs() to create the directory and its parent directories if they don't exist
        boolean success = directory.mkdirs();

        if (success) {
            System.out.println("Data directory created successfully");
            /*
            // Specify the file name
            String fileName = "savedData.txt";

            // Create a File object representing the file inside the directory
            File file = new File(directory, fileName);

            try {
                // Use createNewFile() to create the file
                boolean fileCreated = file.createNewFile();

                if (fileCreated) {
                    System.out.println("Data file created successfully");
                } else {
                    System.out.println("Data file already exists");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
            */
        } else {
            System.out.println("Failed to create data directory");
        }
    }


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
}
