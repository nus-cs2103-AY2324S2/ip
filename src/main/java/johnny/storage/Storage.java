package johnny.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import johnny.exceptions.JohnnyException;
import johnny.tasks.Deadline;
import johnny.tasks.Event;
import johnny.tasks.Task;
import johnny.tasks.TaskList;
import johnny.tasks.ToDo;

/**
 * Controls data read and written to a text file.
 */
public class Storage {

    /** Format of DateTime in Tasks for storing in Storage. */
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    /** Where the data is read from and written to. */
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Where the data is read from and written to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads Tasks from given file path and stores them into TaskList.
     * If directory or file does not exist, create them and return empty TaskList.
     *
     * @return Populated or empty TaskList.
     * @throws JohnnyException If directory or file cannot be created.
     */
    public List<Task> load() throws JohnnyException {
        try {
            File file = new File(filePath);
            List<Task> tasks = new ArrayList<>();

            if (!file.isFile() && file.getParentFile().mkdir()) {
                file.createNewFile();
                return tasks;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] parsedInput = input.split(" \\| ");
                Task task;
                String taskType = parsedInput[0];

                switch(taskType) {
                case "T":
                    task = new ToDo(parsedInput[2]);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parsedInput[3], INPUT_DATE_FORMAT);
                    task = new Deadline(parsedInput[2], by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parsedInput[3], INPUT_DATE_FORMAT);
                    LocalDateTime to = LocalDateTime.parse(parsedInput[4], INPUT_DATE_FORMAT);
                    task = new Event(parsedInput[2], from, to);
                    break;
                default:
                    throw new JohnnyException("The file has been corrupted bro.");
                }

                if (parsedInput[1].equals("1")) {
                    task.mark();
                }
                tasks.add(task);
            }

            scanner.close();
            return tasks;
        } catch (IOException e) {
            throw new JohnnyException("I can't create a new file bro: " + e.getMessage());
        }
    }

    /**
     * Writes all Tasks in TaskList into the file.
     *
     * @param tasks TaskList to be stored.
     * @throws JohnnyException If file cannot be written to.
     */
    public void rewriteFile(TaskList tasks) throws JohnnyException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).addToFile());
            }
        } catch (IOException e) {
            throw new JohnnyException("Cannot write to file bro: " + e.getMessage());
        }
    }

    /**
     * Adds a new Task at the bottom the file.
     *
     * @param task Task to be added to file.
     * @throws JohnnyException If file cannot be written to.
     */
    public void appendToFile(Task task) throws JohnnyException {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(task.addToFile());
        } catch (IOException e) {
            throw new JohnnyException("Cannot write to file bro: " + e.getMessage());
        }
    }

}
