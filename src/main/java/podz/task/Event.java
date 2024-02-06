package podz.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String from, to, formattedFrom, formattedTo;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        
        // splits the from/to information to an array containing its date and time
        String[] fromDateTime = from.split(" ");
        String[] toDateTime = to.split(" ");

        this.formattedFrom = formatDT(fromDateTime);
        this.formattedTo = formatDT(toDateTime);
    }

    private String formatDT(String[] dateAndTime) {
        String formattedString;
        if (dateAndTime.length >= 2) {
            LocalDate d1 = LocalDate.parse(dateAndTime[0]);
            int hour = Integer.parseInt(dateAndTime[1].substring(0, 2));
            int minute = Integer.parseInt(dateAndTime[1].substring(2));
            LocalDateTime dateTime = d1.atTime(hour, minute);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm");
            formattedString = dateTime.format(formatter);
        } else {
            LocalDate d1 = LocalDate.parse(dateAndTime[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
            formattedString = d1.format(formatter);
        }

        return formattedString;
    }

    @Override
    public String savedFormat() {
        return "E " + "|" + super.savedFormat() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.formattedFrom 
                + " to: " +  this.formattedTo + ")";
    }
}