package duke.dataprocessing;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Decoder {
    public static Task decodeTask(String s) throws DukeException {
        String[] attributes = s.split(",");
        Task task = null;

        try {
            switch (attributes[0]) {
            case "T":
                task = new Todo(attributes[2]);
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
