package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final String line = "\t______________________________________________________";
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, inputFormat);
        this.endTime = LocalDateTime.parse(endTime, inputFormat);
    }

    @Override
    public String print() {
        String str = "";
        try {
            String start = startTime.format(outputFormat);
            String end = endTime.format(outputFormat);
            str = "[E]" + super.print() + "(from: " +
                    start + ") (to: " + end + ")";
        } catch (DateTimeParseException e) {
            System.out.println(line);
            System.out.println("\t I think you haven't had enough vitamin C."
                    + "\n\t Your time format should be :"
                    + "\n\t\t { dd/MM/yyyy HHmm }"
                    + "\n\t I suggest you take some LEMONA.");
            System.out.println(line);
        }
        return str;
    }
    @Override
    public String getDescription() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        String str = "[E] " + super.getDescription() + " " + start + " " + end;
        return str;
    }

    @Override
    public String getTaskInfo() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        return "[E] " + "/ [" + super.getStatusIcon() + "] / " + super.getTaskInfo() + "/ " + start
                + "/ " + end;
    }
}
