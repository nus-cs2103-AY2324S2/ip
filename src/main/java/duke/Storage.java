package duke;

import java.io.*;
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
     * @param mylist The list containing tasks to be saved.
     */
    public void save(MyList mylist) {
        assert mylist != null : "MyList should not be null";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task t : mylist.getTasksForSaving()) {
                writer.write(t.toSave());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing task to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns a list of tasks.
     *
     * @return The list of loaded task.
     * @throws FileNotFoundException If the file is not found.
     * @throws DukeException         If there is an issue with the file content.
     * @throws IOException           If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws FileNotFoundException, DukeException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] loadInput = line.split("\\|");
            String type = loadInput[0].trim();
            String isDone = loadInput[1].trim();
            String taskString;
            String dateTimePattern;
            String byString;
            String fromString;
            String toString;
            Task task;

            switch (type) {
            case "T":
                taskString = loadInput[2].trim();
                task = new Todo(taskString);

                if (isDone.equals("1")) {
                    task.markAsDone();
                }

                this.tasks.add(task);
                break;
            case "D":
                taskString = loadInput[2].trim();
                byString = loadInput[3].trim();
                dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                if (Pattern.matches(dateTimePattern, byString)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(byString, formatter);
                    task = new Deadline(taskString, dateTime);
                    this.tasks.add(task);
                } else {
                    throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                taskString = loadInput[2].trim();
                fromString = loadInput[3].trim();
                toString = loadInput[4].trim();
                dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

                if (Pattern.matches(dateTimePattern, fromString) && Pattern.matches(dateTimePattern, toString)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime dateTimeFrom = LocalDateTime.parse(fromString, formatter);
                    LocalDateTime dateTimeTo = LocalDateTime.parse(toString, formatter);
                    task = new Event(taskString, dateTimeFrom, dateTimeTo);
                    this.tasks.add(task);
                } else {
                    throw new DukeException("Please enter date in the format (yyyy-mm-dd hhmm)");
                }

                if (isDone.equals("1")) {
                    task.markAsDone();
                }
                break;
            default:
                throw new DukeException("Please check for a valid task type in the load file");
            }
        }
        return this.tasks;
    }
}
