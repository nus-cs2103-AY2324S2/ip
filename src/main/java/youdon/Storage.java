package youdon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage handler for saving and loading tasks to and from a file.
 * This class provides methods to save and load tasks from a file.
 */
public class Storage {
    private final String filepath;
    private final File saveFile;

    /**
     * Constructs a new instance of the Storage class with the specified file path.
     * Creates a new save file if it doesn't exist.
     *
     * @param filepath The file path of the save file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.saveFile = new File(filepath);

        try {
            if (!saveFile.exists()) {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    /**
     * Saves the tasks from the task list to the save file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveData(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(this.filepath);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            writer.write(task.toString() + "\n");
        }
    }

    /**
     * Loads tasks from the save file and returns them as an ArrayList.
     *
     * @return The list of tasks loaded from the save file.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (saveFile.length() > 0) {
                Scanner scanner = new Scanner(saveFile);

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    // identify task type
                    String taskType = data.substring(1, 2);

                    if (taskType.equals("T")) {
                        // identify task description
                        int startIndex = data.indexOf("[") + 7;
                        String description = data.substring(startIndex);
                        tasks.add(new Todo(description));
                    }

                    if (taskType.equals("D")) {
                        // identify task description
                        int startIndex = data.indexOf("[") + 7;
                        int endIndex = data.indexOf(" (by:");
                        String description = data.substring(startIndex, endIndex).trim();
                        // identify task deadline
                        startIndex = data.indexOf("(by: ") + 5;
                        endIndex = data.indexOf(")", startIndex);
                        String deadline = data.substring(startIndex, endIndex);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
                        tasks.add(new Deadline(description, deadlineDateTime));
                    }

                    if (taskType.equals("E")) {
                        // identify task description
                        int startIndex = data.indexOf("[") + 7;
                        int endIndex = data.indexOf(" (from:");
                        String description = data.substring(startIndex, endIndex);
                        // identify task start time
                        startIndex = data.indexOf("from: ") + 6;
                        endIndex = data.indexOf(" to:", startIndex);
                        String start = data.substring(startIndex, endIndex);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                        LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
                        // identify task end time
                        startIndex = data.indexOf(" to:") + 5;
                        endIndex = data.indexOf(")", startIndex);
                        String end = data.substring(startIndex, endIndex);
                        LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);
                        tasks.add(new Event(description, startDateTime, endDateTime));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error!" + e.getMessage());
        }
        return tasks;
    }
}
