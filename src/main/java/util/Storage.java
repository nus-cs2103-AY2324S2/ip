package util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import exception.NarutoException;
import task.Task;

/**
 * The Storage class is responsible for reading and writing tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "src/logs/tasks.txt";

    /**
     * Constructs a Storage object with the given list of tasks.
     *
     * @param tasks The list of tasks to be stored.
     * @throws IOException If an I/O error occurs.
     */
    Storage(ArrayList<Task> tasks) throws IOException {
        initStorage(tasks);
    }

    /**
     * Reads from the file and initializes the list of tasks.
     *
     * @param tasks The list of tasks to be initialized.
     * @throws IOException If an I/O error occurs.
     */
    private static void initStorage(ArrayList<Task> tasks) throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Create the parent directories if they do not exist
        Files.createDirectories(filePath.getParent());

        // Create the file if it doesn't exist
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (Scanner sc = new Scanner(filePath)) {
            while (sc.hasNext()) {
                try {
                    tasks.add(new CsvUtil(sc.nextLine()).toTask());
                } catch (IllegalArgumentException e) {
                    PrintUtil.print(NarutoException.createFileCorruptedException().getMessage());
                    resetStorage();
                    break;
                }
            }
        }
    }

    /**
     * Resets the storage by deleting the file and creating a new one.
     *
     * @throws IOException If an I/O error occurs.
     */
    public static void resetStorage() throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Delete the file if it exists
        Files.deleteIfExists(filePath);

        // Create the parent directories if they do not exist
        Files.createDirectories(filePath.getParent());

        // Create the file
        Files.createFile(filePath);
    }

    /**
     * Writes the tasks to the file.
     *
     * @param tasks The list of tasks to be written.
     * @throws IOException If an I/O error occurs.
     */
    protected void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.format().toCsv());
        }
        fw.close();
    }
}
