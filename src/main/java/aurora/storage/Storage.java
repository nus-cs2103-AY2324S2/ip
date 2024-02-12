package aurora.storage;

import aurora.objects.Deadline;
import aurora.objects.DukeException;
import aurora.objects.Event;
import aurora.objects.Task;
import aurora.objects.Todo;
import aurora.parser.Parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Storage is a class that handles file storage and retrieval.
 */
public class Storage {
    /** The filepath to store or load data from. */
    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @params filePath. filePath to the file to be retrieved and operated on
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that returns an ArrayList of tasks after loading the data from the filePath specified.
     *
     * @return ArrayList of tasks in the file.
     * @throws IOException If there is no file located at the path.
     * @throws DukeException If the file is corrupted.
     */
    public ArrayList<Task> loadTasks() throws IOException, DukeException {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        if (file.exists()) {
            List<String> fileLines = Files.readAllLines(file.toPath());
            for (String line : fileLines) {
                try {
                    Task task = fileLinesToTask(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                } catch (DukeException e) {
                    throw new DukeException("Corrupted line in data file: " + line);
                }
            }
        }
        return taskList;
    }

    /**
     * Method that writes an ArrayList of tasks to a filePath.
     *
     * @param taskList TaskList to be written to file.
     * @throws IOException If there is a problem in saving the file to the specified directory.
     */
    public void saveTasks(ArrayList<Task> taskList) throws IOException, DukeException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        assert !taskList.isEmpty() : "Nothing to save";
        for (Task task : taskList) {
            bufferedWriter.write(taskToFileLine(task) + "\n");
        }

        bufferedWriter.close();
    }

    /**
     * Method that parses a line from the loaded file and returns a task object based on the contents of the line.
     *
     * @param fileLine Line from file loaded to be parsed.
     * @return Task parsed from the file line.
     * @throws DukeException If the particular line is corrupted.
     */
    private Task fileLinesToTask(String fileLine) throws DukeException {
        String[] components = fileLine.split(" \\| ");
        if (components.length < 3) {
            throw new DukeException("Invalid line");
        }
        String type = components[0];
        boolean isDone = components[1].trim().equals("1");
        String description = components[2].trim();

        try {
            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.setDone();
                }
                return todo;
            case "D":
                if (components.length < 4) {
                    throw new DukeException("Invalid format for a deadline.");
                }
                LocalDateTime dateLdt = Parser.parseDateFromStorage(components[3].trim());
                Deadline deadline = new Deadline(description, dateLdt);
                if (isDone) {
                    deadline.setDone();
                }
                return deadline;
            case "E":
                if (components.length < 5) {
                    throw new DukeException("Invalid format for an event.");
                }
                LocalDateTime startLdt = Parser.parseDateFromStorage(components[3].trim());
                LocalDateTime endLdt = Parser.parseDateFromStorage(components[4].trim());
                Event event = new Event(description, startLdt, endLdt);
                if (isDone) {
                    event.setDone();
                }
                return event;
            default:
                throw new DukeException("Unknown task type.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format in task: " + e.getMessage());
        }
    }

    /**
     *  Converts a task to a fileLine to ba saved into the txt file.
     *
     * @param task Task to be converted into a fileLine
     * @return fileLine in String format
     */
    private String taskToFileLine(Task task) {
        if (task instanceof Todo) {
            Todo currTask = (Todo) task;
            return currTask.toFileString();
        } else if (task instanceof Deadline) {
            Deadline currTask = (Deadline) task;
            return currTask.toFileString();
        } else {
            Event currTask = (Event) task;
            return currTask.toFileString();
        }
    }
}
