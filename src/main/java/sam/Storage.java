package sam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sam.task.Deadline;
import sam.task.Event;
import sam.task.Task;
import sam.task.ToDo;

/**
 * Represents a Storage object for file IO.
 */
public class Storage {
    private final String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * Initializes the Storage object with the provided file path, which indicates the location
     * where data will be stored or retrieved.
     *
     * @param filePath the file path used by the Storage object
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * Reads task from the file indicated by the FILE_PATH attribute and returns them as an ArrayList.
     * If an IOException occurs during file reading, or if there's an issue with the file format or content,
     * a SamException is thrown.
     *
     * @return an ArrayList containing the tasks loaded from the file
     * @throws SamException if an error occurs during task loading
     * @throws IOException if an I/O error occurs while reading from the file
     */
    public ArrayList<Task> load() throws SamException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            Task task = createTaskFromLine(line);
            if (task != null) {
                taskList.add(task);
            }
        }

        reader.close();
        return taskList;
    }

    /**
     * Saves the given list of tasks to a file.
     *
     * Writes the provided TaskList to the file indicated by the FILE_PATH attribute.
     * If an IOException occurs during file writing, an error message is displayed,
     *
     * @param tasks the TaskList to be saved to the file
     */
    public void save(TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (String item : tasks.getFileStrings()) {
                writer.write(item + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    /**
     * Creates a Task object from the provided line of text.
     *
     * Parses the given line of text to extract task details and constructs a Task object accordingly.
     * If the line format is invalid or incomplete, a SamException is thrown.
     *
     * @param line the line of text containing task details
     * @return the Task object created from the line of text
     * @throws SamException if an error occurs while creating the Task object due to invalid or incomplete data
     */
    @SuppressWarnings("checkstyle:Indentation")
    private static Task createTaskFromLine(String line) throws SamException {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            System.out.println("Skipped invalid line in data file: " + line);
            return null;
        }

        String taskType = parts[0];
        int isDone = Integer.parseInt(parts[1]);
        String description = parts[2];

        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                task = new Deadline(description, by);
                break;
            case "E":
                String fromTo = parts.length > 3 ? parts[3] : "";
                String[] fromToArray = fromTo.split(" to ");
                String from = fromToArray.length > 0 ? fromToArray[0] : "";
                String to = fromToArray.length > 1 ? fromToArray[1] : "";
                task = new Event(description, from, to);
                break;
            default:
                System.out.println("Skipped unknown task type in data file: " + taskType);
                return null;
        }

        if (isDone == 1) {
            task.markAsDone();
        }
        return task;
    }
}
