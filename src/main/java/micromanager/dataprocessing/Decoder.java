package micromanager.dataprocessing;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import micromanager.Launcher;
import micromanager.exceptions.DukeException;
import micromanager.tasks.Deadline;
import micromanager.tasks.Event;
import micromanager.tasks.Task;

/**
 * Decoder class provides methods to decode task information from string representations.
 */
public class Decoder {
    /**
     * Decodes the specified string representation of a task into a Task object.
     *
     * @param s The string representation of the task.
     * @return The Task object decoded from the string representation.
     * @throws DukeException If an error occurs during decoding.
     */
    public static Task decodeTask(String s) throws DukeException {
        String[] attributes = s.split(",");
        Task task = null;

        try {
            switch (attributes[0]) {
            case "T":
                task = new Launcher.Todo(attributes[2]);
                break;
            case "D":
                task = new Deadline(attributes[2], LocalDate.parse(attributes[3]));
                break;
            case "E":
                task = new Event(attributes[2], LocalDate.parse(attributes[3]), LocalDate.parse(attributes[4]));
                break;
            default:
                throw new DukeException("OOPS!!! File input is invalid.");
            }

            if (Boolean.parseBoolean(attributes[1])) {
                task.markDone();
            } else {
                task.unmarkDone();
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("OOPS!!! File input is invalid.");
        }

        return task;
    }
}
