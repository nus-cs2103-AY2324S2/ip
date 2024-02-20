package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private File infoStored; // File where tasks are stored
    private String filepath; // Path to the file

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        filepath = filePath;
        makeNewDirectory(); // Creates a new data file in the same folder if not already existing
        makeNewFile(filePath); // Creates a new file in filepath if not already existing
    }

    /**
     * Writes the tasks from the task list to the file, else print a statement.
     *
     * @param tasklist The list of tasks to be written to the file.
     */
    public void writeToFile(TaskList tasklist) {
        String filePath = filepath; // Specify the file path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Iterate through the list of tasks
            for (int i = 0; i < tasklist.length(); i++) {
                Task task = tasklist.getTask(i);
                // Write the task's information to the file
                writer.write(task.typeid() + "~" + task.markstatus() + "~" + task.getDescription() + task.timeprint());
                writer.newLine(); // Add a newline character after each task
            }
        } catch (IOException e) {
            System.out.println("Error implementing changes!");
        }
    }

    /**
     * Creates a new directory if it doesn't exist.
     */
    public void makeNewDirectory() {
        File location = new File("./data");
        if (location.mkdirs()) {
            System.out.println("Successfully made new directory!");
        } else {
            System.out.println("Data file already exists.");
        }
    }

    /**
     * Creates a new file if it doesn't exist.
     *
     * @param filepath The path to the file.
     */
    public void makeNewFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                // Create the file if it doesn't exist
                if (file.createNewFile()) {
                    System.out.println("File successfully created.");
                }
            } catch (IOException e) {
                // Use a default filepath if the provided one is invalid
                file = new File("./data/tasks.txt");
                System.out.println("Filepath invalid. Data has been stored to the filepath ./data/tasks.txt");
            }
        }
        infoStored = file;
    }

    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (infoStored.length() == 0) {
            return tasks;
        }
        try {
            Scanner s = new Scanner(infoStored);
            while (s.hasNext()) {
                String nextinput = s.nextLine();
                Task task = convert(nextinput);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return tasks;
    }

    /**
     * Converts a string representation of a task into a Task object depending on its label.
     *
     * @param input The string representation of the task.
     * @return The Task object.
     */
    public Task convert(String input) {
        String[] inputs = input.split("~");
        String type = inputs[0];
        boolean marked = inputs[1].equals("marked");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        switch (type) {
        case "T":
            Task task = new ToDo(inputs[2]);
            if (marked) {
                task.mark();
            }
            return task;
        case "E":
            LocalDate start = LocalDate.parse(inputs[3], dateFormatter);
            LocalDate end = LocalDate.parse(inputs[4], dateFormatter);
            Task task2 = new Event(inputs[2], start, end);
            if (marked) {
                task2.mark();
            }
            return task2;
        case "D":
            LocalDate due = LocalDate.parse(inputs[3], dateFormatter);
            Task task3 = new Deadline(inputs[2], due);
            if (marked) {
                task3.mark();
            }
            return task3;
        default:
            return new Task("Error");
        }
    }

}
