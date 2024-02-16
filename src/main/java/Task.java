import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Year;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Task {
    String name;

    DateTimeFormatterBuilder dtfbuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[ddMMyy HHmm]" + "[ddMM HHmmyyyy]" + "[ddMMyyyyHHmm]"));
    DateTimeFormatter dtf = dtfbuilder.toFormatter();


    boolean done;

    int currentYear = Year.now().getValue();
    String defaultStartTime = "0000";
    String defaultEndTime = "2359";

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task() {
        this.name = "";
        this.done = false;
    }

    public void mark() {
        this.done = true;
    };

    public void unmark() {
        this.done = false;
    };


    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String getInput() {
        if (this.done) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

    public Task parseFromData(String input) throws DukeException {
        Task task = null;
        boolean done;
        String[] arr = input.split("/", 5);
        if (arr[0].equals("1")) {
            done = true;
        } else {
            done = false;
        }
        String command = "";
        try {
            command = arr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            Data.clear();
            throw new DukeException("Invalid data, data cleared");
        }

        if (command.equals("todo")) {
            task = new Todo(arr[2], done);
        } else if (command.equals("deadline")) {

            try {
                String name = arr[2];
                String byStr = arr[3];
                LocalDateTime by = LocalDateTime.parse(byStr);
                task = new Deadline(name, by, done);
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
                task = new Event(name, from, to, done);
            } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                throw new DukeException("Invalid format in data.");
            }

        } else {
            throw new DukeException("I don't know what that means.");
        }
        return task;
    }

    public Task parseFromInput(String input) throws DukeException {
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
                        by = LocalDateTime.parse(byStr + currentYear + defaultEndTime, dtf);
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
                            from = LocalDateTime.parse(fromStr + currentYear + defaultStartTime, dtf);
                            to = LocalDateTime.parse(toStr + currentYear + defaultEndTime, dtf);
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
}
