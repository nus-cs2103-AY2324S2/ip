import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadlineDateLocalDate;
    private String deadlineDateReformattedString;
    public Deadline(String name) throws NicoleException {
        super();
        if (name.contains("null")) {
            throw new NicoleException("Describe your deadline like this: deadline [task] by YYYY-MM-DD");
        }
        this.parseDate(name);
        super.setName(name);
    }
    private void parseDate(String name) throws NicoleException {
        String[] whiteSpaceSeparatedDate = name.split(" ");
        String date = whiteSpaceSeparatedDate[whiteSpaceSeparatedDate.length - 1];
        try {
            this.deadlineDateLocalDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new NicoleException("Are you sure your date is in the proper [YYYY-MM-DD] format...?");
        }
        if (LocalDate.now().isAfter(this.deadlineDateLocalDate)) {
            throw new NicoleException("Erm, the deadline can't be before now right...");
        }
        this.deadlineDateReformattedString = ""
                + this.deadlineDateLocalDate.getDayOfMonth() + " "
                + this.deadlineDateLocalDate.getMonth().toString() + " "
                + this.deadlineDateLocalDate.getYear();
    }

    @Override
    public LocalDate getDate() {
        return this.deadlineDateLocalDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
