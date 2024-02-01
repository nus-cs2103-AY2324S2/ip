package Tasks;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DeadLineTask extends Task {
    private final String  formattedDeadline;
    private final String DeadLineLog;
    private static final DateTimeFormatter  dateformatter = DateTimeFormatter.ofPattern("MMM-dd");
    private static final DateTimeFormatter  timeformatter = DateTimeFormatter.ofPattern("hh:mm a");
    private static final String  year = "24-";


    public DeadLineTask(String dl, String task) {
        super(task);
        DeadLineLog = dl;
        LocalTime time;
        LocalDate deadline;
        if (dl.length() != 5) {
            time = LocalTime.parse(dl.substring(6).toUpperCase(), timeformatter);
            deadline = LocalDate.parse(year+dl.substring(0,5), DateTimeFormatter.ofPattern("yy-MM-dd"));
            formattedDeadline = deadline.format(dateformatter) + " " + time.format(timeformatter);
        } else {
            deadline = LocalDate.parse(year + dl, DateTimeFormatter.ofPattern("yy-MM-dd"));
            formattedDeadline = deadline.format(dateformatter);
        }
    }

    @Override
    public String toString() {
        return"[D]" + super.toString() + " (by " + formattedDeadline + ")";
    }

    @Override
    public String logString() {
        return "D" + super.logString() + "|" + DeadLineLog;
    }
}