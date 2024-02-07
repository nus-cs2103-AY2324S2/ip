package storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import task.TaskList;

public class Storage {

    protected String filePath;
    private static final String DIVIDER = "        ------------------------------------------------------------";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadTasks() throws IOException {
        Path retrievedFilePath = Paths.get(filePath);

        // Create the parent directories if they do not exist
        if (!Files.exists(retrievedFilePath)) {
            Files.createDirectories(retrievedFilePath.getParent());
            Files.createFile(retrievedFilePath);
        }

        try {
            Scanner sc = new Scanner(filePath);
            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                System.out.println(taskDetails);
            }
        } finally {

        }
    }

    /**
     * Saves task list to text file upon each change.
     *
     * @param tasks List of tasks.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(DIVIDER + "\n");
        for (int j = 1; j < tasks.size() + 1; j++) {
            task.Task currentTask = tasks.get(j - 1);
            fw.write("        " + j + ". " + currentTask.toString() + "\n");
        }
        fw.write(DIVIDER);
        fw.close();
    }
}
