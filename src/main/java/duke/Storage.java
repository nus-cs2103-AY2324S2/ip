package duke;
import duke.Task.Task;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    private static final String FILE_PATH = "duke.txt";

    static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    
                    try {
                        Task task = Parser.parseTaskFromString(line);
                        tasks.add(task);
                    } catch (DukeException e) {
                        System.out.println("Error parsing task from file: " + e.getMessage());
                        // Decide how to handle the DukeException, e.g., logging or skipping the task
                    }

                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

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