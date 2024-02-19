package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import exception.GeePeeTeeException;
import task.Deadline;
import task.Event;
import task.Priority;
import task.Task;
import task.ToDo;
import utils.EnumConverter;

/**
 * Represents a storage class that handles the loading and saving of task lists
 * to
 * the hard disk.
 * <p>
 * This class is responsible for loading and saving task lists to the hard disk,
 * allowing the task list to be persisted across multiple sessions of the
 * application.
 * </p>
 */
public class Storage {

    public static final String BASE_PATH = System.getProperty("user.dir");
    private String filePath;

    /**
     * Constructs a new {@code Storage} instance with the specified file name.
     * 
     * @param fileName The name of the file to be associated with the storage.
     * @throws FileNotFoundException If the file specified by the file name does not
     *                               exist and cannot be created.
     * @throws IOException           If an I/O error occurs while creating the file.
     */
    public Storage(String fileName) throws FileNotFoundException, IOException {
        assert fileName != null : "File name cannot be null.";
        try {
            this.filePath = BASE_PATH + "/" + fileName;
            assert Paths.get(this.filePath).isAbsolute() : "File path must be absolute.";
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Loads the task list from the hard disk.
     * 
     * @return The task list loaded from the hard disk.
     * @throws FileNotFoundException If the file specified by the file name does not
     *                               exist and cannot be created.
     * @throws GeePeeTeeException    If the file contains an invalid task type.
     * @throws IOException           If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> loadTaskList() throws FileNotFoundException, GeePeeTeeException, IOException {
        ArrayList<Task> result = new ArrayList<Task>();
        File f = new File(filePath);
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = parseTaskFromLine(line);
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Parses a task from a line in the task list file.
     * 
     * @param line The line in the task list file
     * @return The task parsed from the line
     * @throws GeePeeTeeException If the file contains an invalid task type.
     */
    private Task parseTaskFromLine(String line) throws GeePeeTeeException {
        String[] parts = line.split(" \\| ");
        validateParts(parts);

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        Priority priority = EnumConverter.convertStringToPriority(parts[2]);
        String description = parts[3];

        Task task;
        switch (type) {
        case "T":
            task = createToDoTask(description, priority);
            break;
        case "D":
            LocalDate deadlineDate = LocalDate.parse(parts[4]);
            task = createDeadlineTask(description, priority, deadlineDate);
            break;
        case "E":
            LocalDate startDate = LocalDate.parse(parts[4]);
            LocalDate endDate = LocalDate.parse(parts[5]);
            task = createEventTask(description, priority, startDate, endDate);
            break;
        default:
            throw new GeePeeTeeException("File contains invalid task type.");
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Validates the parts of a line in the task list file.
     * 
     * @param parts The parts of a line in the task list file
     * @throws GeePeeTeeException If the number of parts in the line is invalid.
     */
    private void validateParts(String[] parts) throws GeePeeTeeException {
        if (parts.length < 4 || parts.length > 6) {
            throw new GeePeeTeeException("Invalid number of parts in task line.");
        }
    }

    /**
     * Creates a todo task with the specified description and priority.
     * 
     * @param description The description of the todo task
     * @param priority    The priority of the todo task
     * @return The todo task
     */
    private Task createToDoTask(String description, Priority priority) {
        ToDo todo = new ToDo(description);
        todo.setPriority(priority);
        return todo;
    }

    /**
     * Creates a deadline task with the specified description, priority and deadline
     * date.
     * 
     * @param description  The description of the deadline task
     * @param priority     The priority of the deadline task
     * @param deadlineDate The deadline date of the deadline task
     * @return The deadline task
     */
    private Task createDeadlineTask(String description, Priority priority, LocalDate deadlineDate) {
        Deadline deadline = new Deadline(description, deadlineDate);
        deadline.setPriority(priority);
        return deadline;
    }

    /**
     * Creates an event task with the specified description, priority, start date
     * and end date.
     * 
     * @param description The description of the event task
     * @param priority    The priority of the event task
     * @param startDate   The start date of the event task
     * @param endDate     The end date of the event task
     * @return The event task
     */
    private Task createEventTask(String description, Priority priority, LocalDate startDate, LocalDate endDate) {
        Event event = new Event(description, startDate, endDate);
        event.setPriority(priority);
        return event;
    }

    /**
     * Saves the task list to the hard disk.
     *
     * @param inputList The task list to be saved to the hard disk.
     * @throws GeePeeTeeException If an error occurs while saving the task list to
     *                            the
     *                            hard disk.
     */
    public void saveTaskList(ArrayList<Task> inputList) throws GeePeeTeeException {
        File file = new File(filePath);

        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : inputList) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new GeePeeTeeException("Error saving the task list file.");
        }
    }
}
