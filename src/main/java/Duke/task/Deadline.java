package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Duke.command.*;

public class Deadline extends task {
    LocalDate date;
    public Deadline(String message, LocalDate toDate){
        super(message);
        this.date = toDate;
    }
    @Override
    public String toString(){
        String msg;
        if (access_state()){
            msg = "[D][X] "+ access_message();
        }
        else{
            msg = "[D][ ] "+ access_message();
        }
        return msg+" (by: "+date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
    }
}
