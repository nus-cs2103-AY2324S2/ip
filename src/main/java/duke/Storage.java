package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Handles the saving and loading of tasks to and from a file.
 */
public class Storage {
    private File file;
    private List<Task> tasks;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.tasks = new ArrayList<>();
    }

    /**
     * Saves the tasks from the list to the file.
     *
     * @param myList The list containing tasks to be saved.
     */
    public void save(MyList myList) {
        assert myList != null : "MyList should not be null";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task task : myList.getTasksForSaving()) {
                writer.write(task.toSave());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing task to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns a list of tasks.
     *
     * @return The list of loaded tasks.
     * @throws FileNotFoundException If the file is not found.
     * @throws DukeException         If there is an issue with the file content.
     * @throws IOException           If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws FileNotFoundException, DukeException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loadInput = line.split("\\|");
                String type = loadInput[0].trim();
                String isDone = loadInput[1].trim();

                Task task;
                switch (type) {
                case "T":
                    task = new Todo(loadInput[2].trim());
                    if ("1".equals(isDone)) {
                        task.markAsDone();
                    }
                    break;
                case "D":
                    task = createDeadlineTask(loadInput);
                    break;
                case "E":
                    task = createEventTask(loadInput);
                    break;
                default:
                    throw new DukeException("Please check for a valid task type in the load file");
                }
                this.tasks.add(task);
            }
        }
        return this.tasks;
    }

    private Task createDeadlineTask(String[] loadInput) throws DukeException {
        String taskString = loadInput[2].trim();
        String byString = loadInput[3].trim();

        validateDateTimeFormat(byString);

        LocalDateTime dateTime = LocalDateTime.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Task task = new Deadline(taskString, dateTime);

        if ("1".equals(loadInput[1].trim())) {
            task.markAsDone();
        }
        return task;
    }

    private Task createEventTask(String[] loadInput) throws DukeException {
        String taskString = loadInput[2].trim();
        String fromString = loadInput[3].trim();
        String toString = loadInput[4].trim();

        validateDateTimeFormat(fromString);
        validateDateTimeFormat(toString);

        LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime dateTimeTo = LocalDateTime.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Task task = new Event(taskString, dateTimeFrom, dateTimeTo);

        if ("1".equals(loadInput[1].trim())) {
            task.markAsDone();
        }
        return task;
    }

    private void validateDateTimeFormat(String dateTimeString) throws DukeException {
        String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

        if (!Pattern.matches(dateTimePattern, dateTimeString)) {
            throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
        }
    }
}
