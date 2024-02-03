package Duke.task;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Duke.command.*;

public class Deadline extends task {
    LocalDate date = null;
    String date1;
    public Deadline(String message, LocalDate toDate){
        super(message);
        this.date = toDate;
    }
    public Deadline(String message, String toDate){
        super(message);
        this.date1 = toDate;
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
        if (date != null){
            return msg+" (by: "+date.format(DateTimeFormatter.ofPattern("MMM dd yyyy").withLocale(Locale.ENGLISH))+")";
        }else{
            return msg+" (by: "+date1+")";
        }
    }
}
