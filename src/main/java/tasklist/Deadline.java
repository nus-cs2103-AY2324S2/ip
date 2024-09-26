package tasklist;

import jux.JuxException;
import parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.DateTimeException;

public class Deadline extends Task {
    private LocalDateTime dateTime;
    public Deadline (String desc, String dateOrDateTimeUnformatted) throws JuxException {
        super(desc);
        this.dateTime = DateTimeParser.inputConvertToDateTime(dateOrDateTimeUnformatted);
    }
    public Deadline (String desc, boolean isDone, String dateOrDateTimeStorage) {
        super(desc, isDone);
        this.dateTime = DateTimeParser.storageConvertToDateTime(dateOrDateTimeStorage);
    }
    @Override
    public String saveStorage(){
        String details = super.saveStorage();
            details = "D|" + details +"|" +  DateTimeParser.convertDateTimeToStringStorage(this.dateTime);
            return details;
    }
    public String toString() {
        return "[D]" + super.toString() + "(" + DateTimeParser.convertDateTimeToStringUI(this.dateTime) + ")";
    }
}
