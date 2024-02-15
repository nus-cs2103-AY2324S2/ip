package tasklist;

import jux.JuxException;
import parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime  startDateTime;
    private LocalDateTime endDateTime;
    public Event(String desc, String startDate, String endDate) throws JuxException {
        super(desc);
            this.startDateTime = DateTimeParser.inputConvertToDateTime(startDate);
            this.endDateTime = DateTimeParser.inputConvertToDateTime(endDate);
    }
    public Event(String desc, boolean isDone, String startDateStorage, String endDateStorage){
        super(desc, isDone);
        this.startDateTime = DateTimeParser.storageConvertToDateTime(startDateStorage);
        this.endDateTime = DateTimeParser.storageConvertToDateTime(endDateStorage);
    }

    @Override
    public String saveStorage(){
        String details = super.saveStorage();
        details = "E|" + details + "|" + DateTimeParser.convertDateTimeToStringStorage(startDateTime)
                + "|" + DateTimeParser.convertDateTimeToStringStorage(endDateTime);
        return details;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + DateTimeParser.convertDateTimeToStringUI(startDateTime)
                + " - " + DateTimeParser.convertDateTimeToStringUI(endDateTime) + ")";
    }
}
