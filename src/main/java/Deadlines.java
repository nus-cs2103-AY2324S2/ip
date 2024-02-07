import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Task {
    protected LocalDate date;

    public Deadlines(String description,  String by) {
        super(description.replaceFirst("deadline ", ""));
        String time = by.replaceFirst("by ", "");
        DateTimeFormatter formatter;
        if (time.length() == 11) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        this.date = LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        String statement = "";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDateTime = date.format(dateTimeFormatter);
        statement = "[D]" + super.toString() + "(by:" + formattedDateTime + ")";
        return statement;
    }
}