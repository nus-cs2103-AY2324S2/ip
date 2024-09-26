package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Handles reading, writing, and deleting lines from the database file.
 */
public class DatabaseHandler {

    private String storageFilePath;

    /**
     * Initializes the database handler with the specified file path.
     *
     * @param storageFilePath the path to the database file
     */
    public DatabaseHandler(String storageFilePath) {
        this.storageFilePath = storageFilePath;
        try {
            File file = new File(this.storageFilePath);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Oops! Failed to create the database file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Reads all lines from the database file into a list of strings.
     *
     * @return a list of strings containing all lines from the database file
     */
    public List<String> readOps() {
        try {
            return Files.readAllLines(Paths.get(this.storageFilePath));
        } catch (IOException e) {
            System.out.println("Oops! Failed to read from the file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Loads data from the database file and returns it as a list of strings.
     *
     * @return a list of strings representing data loaded from storage
     */
    public List<String> loadOps() {
        List<String> stringTasksList = this.readOps();
        return stringTasksList;
    }

    /**
     * Writes provided list of strings to the database file.
     *
     * @param stringList the list of strings to be written to the file
     */
    public void writeOps(List<String> stringList) {
        try {
            File file = new File(this.storageFilePath);
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(this.storageFilePath);

            for (String line : stringList) {
                fileWriter.write(line + "\n");
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Oops! Failed to write to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Clears the contents of the storage file
     */
    public void clearFile() {
        try {
            Files.write(Paths.get(storageFilePath), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String storageFilePath = "data/duketest.txt";
        DatabaseHandler databaseHandler = new DatabaseHandler(storageFilePath);

        databaseHandler.clearFile();

        List<String> input = new ArrayList<>();

        input.add("E | 0 | netflix marathon | 01 December 2022 00:00 | 15 December 2022 23:59");
        input.add("Test was a success!");

        databaseHandler.writeOps(input);

        List<String> output = databaseHandler.readOps();

        for (String line : output) {
            System.out.println(line);
        }
        System.out.println(Objects.equals(input, output));
    }
}
