package tasklist;

import parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime  startDateTime;
    private LocalDateTime endDateTime;
    public Event(String desc, String startDate, String endDate){
        super(desc);
        try{
            this.startDateTime = Parser.inputConvertToDateTime(startDate);
            this.endDateTime = Parser.inputConvertToDateTime(endDate);
        } catch(DateTimeException e) {
            System.out.println(e.getMessage());
        }

    }
    public Event(String desc, boolean isDone, String startDateStorage, String endDateStorage){
        super(desc, isDone);
        this.startDateTime = Parser.storageConvertToDateTime(startDateStorage);
        this.endDateTime = Parser.storageConvertToDateTime(endDateStorage);
    }

    @Override
    public String saveStorage(){
        String details = super.saveStorage();
        details = "E|" + details + "|" + Parser.convertDateTimeToStringStorage(startDateTime)
                + "|" + Parser.convertDateTimeToStringStorage(endDateTime);
        return details;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + Parser.convertDateTimeToStringUI(startDateTime)
                + " - " + Parser.convertDateTimeToStringUI(endDateTime) + ")";
    }
}
