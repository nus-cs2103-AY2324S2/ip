package util;
import exception.*;
import task.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Storage {
    private static final String FILE_PATH = "src/logs/tasks.txt";

    Storage(ArrayList<Task> tasks) throws IOException {
        initStorage(tasks);
    }
    // Reads from the file and adds the tasks to ArrayList.
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
                    tasks.add(new CSVUtil(sc.nextLine()).toTask());
                } catch (IllegalArgumentException e) {
                    PrintUtil.print(NarutoException.createFileCorruptedException().getMessage());
                    resetStorage();
                    break;
                }
            }
        }
    }
    public static void resetStorage() throws IOException {
        Path filePath = Paths.get(FILE_PATH);

        // Delete the file if it exists
        Files.deleteIfExists(filePath);

        // Create the parent directories if they do not exist
        Files.createDirectories(filePath.getParent());

        // Create the file
        Files.createFile(filePath);
    }

    void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.format().toCSV());
        }
        fw.close();
    }
}
