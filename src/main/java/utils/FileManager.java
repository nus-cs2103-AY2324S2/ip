package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The FileManager class provides utility methods for reading from and writing to files.
 */
public class FileManager {

    /**
     * Reads the content of the file with the specified file name.
     *
     * @param fileName The name of the file to read.
     * @return The content of the file as a string.
     */
    public static String readFile(String fileName) {
        StringBuilder fileData = new StringBuilder();
        try {
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                return "";
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                fileData.append(reader.nextLine()).append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // If the file is not found, return an empty string
            System.out.printf("An error occurred. %s\n", e.getMessage());
        }
        return fileData.toString();
    }

    /**
     * Writes the content of the task list to the specified file.
     *
     * @param fileName The name of the file to write.
     * @param taskList The task list object to write to the file.
     */
    public static void writeFile(String fileName, TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(taskList.convertTaskListToFileString());
            writer.close();
        } catch (IOException e) {
            // If an IO exception occurs, print an error message
            System.out.printf("An error occurred. %s\n", e.getMessage());
        }
    }
}
