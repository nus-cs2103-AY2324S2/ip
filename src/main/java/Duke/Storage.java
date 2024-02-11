package Duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles storage operations for Duke application tasks.
 * <p>
 * Responsible for saving tasks to a file and reading tasks from a file.
 * It interacts with the Ui component for error reporting and the Parser component for parsing tasks.
 */
public class Storage {

    final Parser parser;

    /**
     * Initializes a new Storage object.
     *
     * @param parser The Parser component used for parsing tasks.
     */
    public Storage (Parser parser) {
        this.parser = parser;
    }

    /**
     * Formats a Task object into a string representation suitable for file storage.
     * <p>
     * The format includes the task type, its completion status, and its description.
     * For Deadline and Event tasks, their respective dates are also included.
     *
     * @param task The Task object to be formatted.
     * @return The formatted string representation of the task.
     */
    public String formatTaskForFile(Task task) {
        String returnString = "";
        String type =
                task instanceof Todo ? "T" :
                        task instanceof Deadline ? "D" :
                                task instanceof Event ? "E" : "";
        int status = task.isDone();
        String details = task.getDescription();
        returnString += type + " | " + status + " | " + details;
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            returnString += " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            returnString += " | " + event.getFrom() + " | " + event.getTo();
        }
        return returnString;
    }

    /**
     * Saves a single Task object to the storage file.
     * <p>
     * The task is appended to the end of the file. If an IOException occurs, an error message is displayed.
     *
     * @param task The Task object to be saved.
     */
    public void saveTaskToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter("./data/duke.txt", true);
            String taskLine = formatTaskForFile(task);
            fileWriter.write(taskLine + "\n");
            fileWriter.close();
        } catch (IOException ignored) {

        }

    }

    /**
     * Saves a list of Task objects to the storage file.
     * <p>
     * This method overwrites the existing file with the entire list of tasks.
     * If an IOException occurs, an error message is displayed.
     *
     * @param taskList The list of Task objects to be saved.
     */
    public void saveAllTasksToFile(ArrayList<Task> taskList) {
        // overwrites all files for mark/unmark functions
        try {
            FileWriter fileWriter = new FileWriter("./data/duke.txt", false);
            for (Task task : taskList) {
                String taskLine = formatTaskForFile(task);
                fileWriter.write(taskLine + "\n");
            }
            fileWriter.close();
        } catch (IOException ignored) {

        }
    }
}
