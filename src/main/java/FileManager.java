import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    // Referenced HusseinSafwan02's code and AI
    private static final String FILE_PATH = "./data/duke.txt";
    private static final String DIRECTORY_PATH = "./data";

    public static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseTask(line);

                    if (task != null) {
                        tasks.add(task);
                    }
                }

                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new file!");
        }

        return tasks;
    }

    private static Task parseTask(String line) {
        String[] splitParts = line.split(" \\| ");
        String taskType = splitParts[0];
        boolean isDone = splitParts[1].equals("1");
        String description = splitParts[2];
        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "D":
            task = new Deadline(description, splitParts[3], isDone);
            break;
        case "E": // Event format in File is E | (isDone) | (Name) | (From) | (To)
            task = new Event(description, splitParts[3], splitParts[4], isDone);
            break;
        }

        return task;
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }

            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to the file: " + e.getMessage());
        }
    }

    private static String taskToFileString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        } else {
            throw new IllegalArgumentException("Invalid task type!");
        }
    }
}
