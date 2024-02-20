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

    private String databaseFilePath;

    /**
     * Initializes the database handler with the specified file path.
     *
     * @param databaseFilePath the path to the database file
     */
    public DatabaseHandler(String databaseFilePath) {
        this.databaseFilePath = databaseFilePath;
        try {
            File file = new File(this.databaseFilePath);
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
    public List<String> readFromFile() {
        try {
            return Files.readAllLines(Paths.get(this.databaseFilePath));
        } catch (IOException e) {
            System.out.println("Oops! Failed to read from the file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Writes the given list of strings to the database file.
     *
     * @param stringList the list of strings to be written to the file
     */
    public void writeToFile(List<String> stringList) {
        try {
            File file = new File(this.databaseFilePath);
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(this.databaseFilePath);

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
     * Loads data from the database file and returns it as a list of strings.
     *
     * @return a list of strings representing data loaded from the database file
     */
    public List<String> loadData() {
        List<String> stringTasksList = this.readFromFile();
        return stringTasksList;
    }

    /**
     * Clears the contents of the database file.
     */
    public void clearFile() {
        try {
            Files.write(Paths.get(databaseFilePath), new byte[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String databaseFilePath = "data/duketest.txt";
        DatabaseHandler databaseHandler = new DatabaseHandler(databaseFilePath);

        // Clear and initialize the data/duke.txt
        databaseHandler.clearFile();

        List<String> input = new ArrayList<>();

        input.add("E | 0 | netflix marathon | 01 December 2022 00:00 | 15 December 2022 23:59");
        input.add("Test was a success!");

        databaseHandler.writeToFile(input);

        List<String> output = databaseHandler.readFromFile();

        for (String line : output) {
            System.out.println(line);
        }
        System.out.println(Objects.equals(input, output));
    }
}
