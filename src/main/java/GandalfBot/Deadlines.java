package GandalfBot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    String deadlineDate;
    LocalDateTime formattedDate;
    transient DateTimeFormatter formatter;
    public Deadlines(String nameOfTask, String deadlineDate){
        super(nameOfTask);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
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
