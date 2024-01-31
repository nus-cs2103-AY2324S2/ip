package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Duke.command.*;

public class Event extends task {
    LocalDate from_date;
    LocalDate to_date;
    public Event(String message, LocalDate from_date, LocalDate to_date){
        super(message);
        this.from_date = from_date;
        this.to_date = to_date;
    }

    @Override
    public String toString(){
        String msg;
        if (access_state()){
            msg = "[E][X] "+ access_message();
        }
        else{
            msg = "[E][ ] "+ access_message();
        }
        return msg+" (from: "+from_date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                +" to: "+ to_date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
    }
}
