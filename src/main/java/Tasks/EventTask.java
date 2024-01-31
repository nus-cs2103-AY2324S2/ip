package Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final String  startTime;
    private final String  endTime;
    private final String formattedEndTime;
    private final String formattedStartTime;
    private static final DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MMM-dd");
    private static final DateTimeFormatter  timeformatter = DateTimeFormatter.ofPattern("hh:mm a");
    private static final String  year = "24-";

    public EventTask(String startTime, String endTime, String task) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
        LocalTime stime,etime;
        LocalDate startdate;
        // if start > ... --> extract month(0,5), time --> 6
        //end --> extract time entire thing

        etime = LocalTime.parse(endTime.toUpperCase().trim(), timeformatter);
        formattedEndTime = etime.format(timeformatter);
        if (startTime.trim().length() > 8) {
            stime = LocalTime.parse(startTime.substring(6).toUpperCase().trim(), timeformatter);
            startdate = LocalDate.parse(year+startTime.substring(0,5), DateTimeFormatter.ofPattern("yy-MM-dd"));
            formattedStartTime = startdate.format(dateformatter) + " " + stime.format(timeformatter);
        } else {
            stime = LocalTime.parse(startTime.toUpperCase().trim(), timeformatter);
            formattedStartTime = stime.format(timeformatter);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + formattedStartTime + " to " + formattedEndTime + ")";
    }
    @Override
    public String logString(){return 'E' + super.logString() + '|' + startTime +'|' + endTime;}
}