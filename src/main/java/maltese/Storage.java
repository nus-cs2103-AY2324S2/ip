package maltese;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import maltese.action.TaskList;
import maltese.task.Deadline;
import maltese.task.Event;
import maltese.task.Task;
import maltese.task.ToDo;


/**
 * Handles the reading and writing of tasks to and from a file.
 */
public class Storage {
    private File taskFile;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Loads tasks from the file into a TaskList.
     *
     * @return A TaskList containing tasks loaded from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public TaskList loadFromFile() throws FileNotFoundException {
        TaskList tasks = new TaskList();

        try (Scanner scanner = new Scanner(taskFile)) {
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                Task task = parseTask(taskLine);
                tasks.addTask(task);
            }
        } catch (FileNotFoundException e) {
            handleFileNotFound();
            throw e;
        }

        return tasks;
    }

    /**
     * Changes the current file path to the specified file path.
     *
     * @param filePath The new file path.
     */
    public void changeFile(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Parses a task from a string representation.
     *
     * @param taskLine The string representation of the task.
     * @return The parsed Task object.
     */
    private Task parseTask(String taskLine) {
        String[] token = taskLine.split("\\|");
        String taskType = token[0];
        String status = token[1];
        String description = token[2];
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            LocalDate deadlineBy = LocalDate.parse(token[3]);
            task = new Deadline(description, deadlineBy);
            break;
        case "E":
            LocalDate eventFrom = LocalDate.parse(token[3]);
            LocalDate eventTo = LocalDate.parse(token[4]);
            task = new Event(description, eventFrom, eventTo);
            break;
        default:
            throw new IllegalArgumentException("Unexpected task type: " + taskType);
        }

        if (status.equals("X")) {
            task.mark();
        }

        return task;
    }

    /**
     * Writes tasks from a TaskList to the file.
     *
     * @param tasks The TaskList containing tasks to be written to the file.
     * @throws IOException If an I/O error occurs.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        try (FileWriter fw = new FileWriter(this.taskFile)) {
            for (Task t : tasks) {
                String icon = t.getType();
                String status = t.getStatusIcon();
                String description = t.getDescription();
                String taskLine;

                switch (icon) {
                case "T":
                    taskLine = String.format("%s|%s|%s\n", icon, status, description);
                    break;
                case "D":
                    taskLine = String.format("%s|%s|%s|%s\n", icon, status, description, ((Deadline) t).getBy());
                    break;
                case "E":
                    taskLine = String.format("%s|%s|%s|%s|%s\n", icon, status,
                            description, ((Event) t).getFrom(), ((Event) t).getTo());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type: " + icon);
                }

                fw.write(taskLine);
            }

            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the situation when the file is not found.
     * Creates the file if it doesn't exist.
     */
    private void handleFileNotFound() {
        System.err.println("File not found: " + this.taskFile.getAbsolutePath());
        System.err.println("Creating the file...");

        File dataDirectory = new File("././data/");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        try {
            this.taskFile.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Checks if a file exists at the specified path.
     *
     * @param filePath The path to the file.
     * @return true if the file exists, false otherwise.
     */
    public boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * Creates a new file at the specified path.
     *
     * @param filePath The path to the file.
     * @throws IOException If an I/O error occurs.
     */
    public void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            // Create parent directories if they don't exist
            file.getParentFile().mkdirs();
        }
        // Create the file
        file.createNewFile();
    }
}
