package duke;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * A class used to parse user input or data to recognizable commands.
 */
public class Parser {


    private static DateTimeFormatterBuilder dtfbuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[ddMMyy HHmm]" + "[ddMM HHmmyyyy]" + "[ddMMyyyyHHmm]"));
    private static DateTimeFormatter dtf = dtfbuilder.toFormatter();
    private static int currentYear = Year.now().getValue();
    private final static String DEFAULT_START = "0000";
    private final static String DEFAULT_END = "2359";

    /**
     * Parses the input if it is a command to add a task.
     * @param input Command to add a task, ie todo, event, deadline
     * @return A Task instance corresponding to the command
     * @throws DukeException If the command format is invalid.
     */
    public static Task parseFromInput(String input) throws DukeException {
        Task task = null;
        String[] arr = input.split(" ", 2);
        if (arr.length <= 1) {
            throw new DukeException("Please use the format: deadline <task> /by <date/time>\n" +
                    "todo <task>\n" +
                    "event <task> /from <date/time> /to <date/time>");
        }
        String cmd = arr[0];
        switch (cmd) {
            case "t":
            case "todo":
                task = new Todo(arr[1]);
                break;
            case "d":
            case "deadline":
                String[] dlarr = arr[1].split("/by ", 2);
                if (dlarr.length <= 1) {
                    throw new DukeException("Please use the format: deadline <task> /by <date/time>.");
                }
                String name = dlarr[0];
                String byStr = dlarr[1].trim();
                LocalDateTime by;
                try {
                    by = LocalDateTime.parse(byStr, dtf);
                    task = new Deadline(name, by);
                } catch (DateTimeParseException e) {
                    try {
                        by = LocalDateTime.parse(byStr + currentYear, dtf);
                        task = new Deadline(name, by);
                    } catch (DateTimeParseException f) {
                        try {
                            by = LocalDateTime.parse(byStr + currentYear + DEFAULT_END, dtf);
                            task = new Deadline(name, by);
                        } catch (DateTimeParseException g) {
                            throw new DukeException("Invalid time format. Please use ddMM, ddMMyy HHmm, or ddMM HHmm");
                        }
                    }

                }

                break;
            case "e":
            case "event":
                String[] frarr = arr[1].split("/from ", 2);
                if (frarr.length <= 1) {
                    throw new DukeException("Please use the format: event /from <date/time> /to <date/time>");
                }
                String[] toarr = frarr[1].split("/to ", 2);
                if (toarr.length <= 1) {
                    throw new DukeException("Please use the format: event /from <date/time> /to <date/time>");
                }
                String name1 = frarr[0];
                String fromStr = toarr[0].trim();
                String toStr = toarr[1].trim();
                LocalDateTime from;
                LocalDateTime to;
                try {
                    from = LocalDateTime.parse(fromStr, dtf);
                    to = LocalDateTime.parse(toStr, dtf);
                    task = new Event(name1, from, to);
                } catch (DateTimeParseException e) {
                    try {
                        from = LocalDateTime.parse(fromStr + currentYear, dtf);
                        to = LocalDateTime.parse(toStr + currentYear, dtf);
                        task = new Event(name1, from, to);
                    } catch (DateTimeParseException f) {
                        try {
                            from = LocalDateTime.parse(fromStr + currentYear + DEFAULT_START, dtf);
                            to = LocalDateTime.parse(toStr + currentYear + DEFAULT_END, dtf);
                            task = new Event(name1, from, to);
                        } catch (DateTimeParseException g) {
                            throw new DukeException("Invalid time format. Please use ddMM, ddMMyy HHmm, or ddMM HHmm");
                        }
                    }

                }

                break;
            default:
                throw new DukeException("Invalid format");
        }

        return task;
    }

    /**
     * Parses string stored in data file.
     * @param input A line in the data file.
     * @return A task corresponding to the line in the data file.
     * @throws DukeException If the input is not recognized or corrupted.
     */
    public static Task parseFromData(String input) throws DukeException {
        Task task = null;
        boolean isDone;
        String[] arr = input.split("/", 5);
        if (arr[0].equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }
        String command = "";
        try {
            command = arr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            Storage.clear();
            throw new DukeException("Invalid data, data cleared");
        }

        if (command.equals("todo")) {
            task = new Todo(arr[2], isDone);
        } else if (command.equals("deadline")) {

            try {
                String name = arr[2];
                String byStr = arr[3];
                LocalDateTime by = LocalDateTime.parse(byStr);
                task = new Deadline(name, by, isDone);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DukeException("Invalid format in data.");
            }

        } else if (command.equals("event")) {
            try {
                String name = arr[2];
                String fromStr = arr[3];
                String toStr = arr[4];
                LocalDateTime from = LocalDateTime.parse(fromStr);
                LocalDateTime to = LocalDateTime.parse(toStr);
                task = new Event(name, from, to, isDone);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DukeException("Invalid format in data.");
            }

        } else {
            throw new DukeException("I don't know what that means.");
        }
        return task;
    }
}
