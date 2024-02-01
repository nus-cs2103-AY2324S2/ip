import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public static Deadline createDeadline(String commandLine) throws CoDriverException {
        String[] arguments = commandLine.split(" ");
        if (arguments.length < 2) {
            throw new CoDriverException("Error! You cannot provide a deadline with no parameters!");
        }

        StringBuilder descriptionBuilder = new StringBuilder();
        int i;
        for (i = 1; i < arguments.length; i++) {
            if (arguments[i].equals("/by")) {
                break;
            }
            descriptionBuilder.append(arguments[i]).append(" ");
        }
        if (descriptionBuilder.length() == 0) {
            throw new CoDriverException("Error! You cannot provide a deadline with no description!");
        }
        descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

        if (i >= arguments.length - 1) { // if the last word is /by or there is no /by
            throw new CoDriverException("Error! You must provide a /by date for a deadline!");
        }

//        StringBuilder dateBuilder = new StringBuilder();
        i++;
        LocalDate date = null;
        for (; i < arguments.length; i++) {
            // check if in yyyy-mm-dd format
            date = Parser.parseDate(arguments[i]);
            if (date == null) {
                throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
            }
//            dateBuilder.append(arguments[i]).append(" ");
        }
        if (date == null) {
            throw new CoDriverException("Error! You must provide a /by date for a deadline!");
        }
        return new Deadline(descriptionBuilder.toString(), date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + this.date;
    }
}