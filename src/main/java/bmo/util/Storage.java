package bmo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class to handle data storage.
 */
public class Storage {

    private static final String FILE_PATH = "data/task_data.txt";
    private static File dataFile;

    /**
     * Constructor for Storage using FILE_PATH.
     */
    public Storage() {
        try {
            dataFile = new File(FILE_PATH);
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
        }
    }

    /**
     * Constructor for Storage with provided file.
     *
     * @param file File object to store data.
     */
    public Storage(File file) {
        try {
            dataFile = file;
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
        }
    }

    // Getter method for dataFile
    public File getDataFile() {
        return dataFile;
    }

    /**
     * Loads data from the file.
     *
     * @return String containing the data.
     * @throws IOException if unable to load data.
     */
    public String loadData() throws IOException {
        try {
            Scanner sc = new Scanner(dataFile);
            StringBuilder output = new StringBuilder();
            while (sc.hasNext()) {
                output.append(sc.nextLine());
                if (sc.hasNext()) {
                    output.append("\n");
                }
            }
            return output.toString();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return "";
        }
    }

    /**
     * Saves data to the file.
     *
     * @param tasks TaskList object to save.
     */
    public void saveData(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(tasks.toSaveData());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save data. " + e.getMessage());
        }
    }

    /**
     * Saves data to file with specified file path.
     *
     * @param tasks    TaskList object to save.
     * @param filePath String containing the file path.
     */
    public void saveData(TaskList tasks, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(tasks.toSaveData());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save data. " + e.getMessage());
        }
    }
}
