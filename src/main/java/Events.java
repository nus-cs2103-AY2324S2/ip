import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Events extends Task{
    String startDate;
    String endDate;
    transient DateTimeFormatter formatter;
    LocalDateTime formattedStartDate;
    LocalDateTime formattedEndDate;
    public Events(String nameOfTask, String startDate, String endDate){
        super(nameOfTask);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startDate = startDate;
        this.endDate = endDate;
        this.formattedStartDate = LocalDateTime.parse(startDate.trim(), formatter);
        this.formattedEndDate = LocalDateTime.parse(endDate.trim(), formatter);
    }
    @Override
    public String toString(){
        if(status){
            return "[E][X] " + nameOfTask + " " + "(from: " + formattedStartDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma"))
                    + " to: " + formattedEndDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        }
        else{
            return "[E][ ] " + nameOfTask + " " + "(from: " + formattedStartDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma"))
                    + " to: " + formattedEndDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        }
    }
}
