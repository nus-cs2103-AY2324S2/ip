package duke;

import duke.Task.Task;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "duke.txt";

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return The ArrayList of tasks loaded from the file.
     */
    static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    tasks.add(Parser.parseTaskFromString(line));
                } catch (DukeException e) {
                    System.out.println("Error parsing task from file: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }


    /**
     * Saves the given tasks to the file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     */
    static void saveTasksToFile(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}