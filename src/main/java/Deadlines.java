import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadlines extends Task{
    String deadlineDate;
    LocalDateTime formattedDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public Deadlines(String nameOfTask, String deadlineDate){
        super(nameOfTask);
        this.deadlineDate = deadlineDate;
        this.formattedDate = LocalDateTime.parse(deadlineDate, formatter);
    }

    @Override
    public String toString(){
        if(status){
            return "[D][X] " + nameOfTask + " " + "(" + formattedDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        }
        else{
            return "[D][ ] " + nameOfTask + " " + "(" + formattedDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        }
    }
}
