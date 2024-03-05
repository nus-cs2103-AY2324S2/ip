package duke;

import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Encapsulates the file to store data and its path.
 */
public class Storage {
    public final Path path;
    public File file;

    /**
     * Constructs the class Storage.
     *
     * @param filePath Path for the storage file.
     * @throws InvalidPathException The path is incorrect.
     */
    public Storage(String filePath) throws InvalidPathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidPathException(filePath, "Storage file should end with '.txt'");
        }

        if (!Files.exists(path)) {
            makeFile();
        }

        file = new File(filePath);
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    private void makeFile() {
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the data from the file into a TaskList.
     *
     * @param tasks The TaskList where the data is loaded into.
     */
    public void load(TaskList tasks) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Stored tasks file does not exists: " + e.getMessage());
            tasks = new TaskList();
        }
        if (fileScanner != null) {
            while (fileScanner.hasNext()) {
                String currentLine = fileScanner.nextLine();
                String[] taskInfo = currentLine.split("/");
                switch (taskInfo[0]) {
                    case "T":
                        tasks.createToDo(taskInfo[2]);
                        break;
                    case "D":
                        tasks.createDeadline(taskInfo[2], LocalDate.parse(taskInfo[3]));
                        break;
                    case "E":
                        tasks.createEvent(taskInfo[2], LocalDate.parse(taskInfo[3]), LocalDate.parse(taskInfo[4]));
                        break;
                }
                if (taskInfo[1].equals("X")) {
                    tasks.mark(tasks.size());
                }

            }
        }
    }

    /**
     * Stores a TaskList into the storage file.
     *
     * @param tasks The TaskList that is going to be stored.
     */
    public void store(TaskList tasks) {
        reset();
        for (Task task : tasks.tasks) {
            try {
                task.writeToFile(file.getPath());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private void reset() {
        try {
            FileWriter fw = new FileWriter(file.getPath(), false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
