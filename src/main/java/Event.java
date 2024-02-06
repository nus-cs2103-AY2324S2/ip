import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{
    private String  startDate;
    private String endDate;
    public Event(String desc, String startDate, String endDate){
        super(desc);
        this.startDate = Parser.convertToDateOnly(startDate);
        this.endDate = Parser.convertToDateOnly(endDate);
    }
    public Event(String desc, boolean isDone, String startDateStorage, String endDateStorage){
        super(desc, isDone);
        this.startDate = startDateStorage;
        this.endDate = endDateStorage;
    }

    @Override
    public String saveStorage(){
        String details = super.saveStorage();
        details = "E|" + details + "|" + startDate + "|" + endDate;
        return details;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + "(" + startDate + " - " + endDate + ")";
    }
}
