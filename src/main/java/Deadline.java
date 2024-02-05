import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");


    public Deadline(String description, String time) throws DukeException {
        super(description);
        time = time.trim();
        if (description.trim().length() == 0 || time.length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (time.length() == 0) {
                errorMessage += "\na deadline after the '/by' command";
            }
            throw new DukeException(errorMessage);
        }

        try {
            this.by = LocalDateTime.parse(time, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date and time in the following format:\n"+
                    "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234");
        }
    }

    public Deadline(String logic, String description, String time) throws DukeException {
        super(description);
        time = time.trim();
        if (description.trim().length() == 0 || time.length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (time.length() == 0) {
                errorMessage += "\na deadline after the '/by' command";
            }
            throw new DukeException(errorMessage);
        }

        try {
            this.by = LocalDateTime.parse(time, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date and time in the following format:\n"+
                    "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234");
        }

        switch (logic) {
        case "1":
            this.isDone = true;
            break;
        default:
            this.isDone = false;
            break;
        }
    }

    public String getBy() {
        return by.format(inputFormatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(displayFormatter) + ")";
    }
}