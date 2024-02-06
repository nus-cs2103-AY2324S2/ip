import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private String dateOrDateTime;
    public Deadline (String desc, String dateOrDateTimeUnformatted) {
        super(desc);
        if (Parser.isDateTime(dateOrDateTimeUnformatted)) {
            this.dateOrDateTime = Parser.convertToDateTime(dateOrDateTimeUnformatted);
        } else {
            this.dateOrDateTime = Parser.convertToDateOnly(dateOrDateTimeUnformatted);
        }

    }
    public Deadline (String desc, boolean isDone, String dateOrDateTimeStorage) {
        super(desc, isDone);
        this.dateOrDateTime = dateOrDateTimeStorage;
    }
    @Override
    public String saveStorage(){
        String details = super.saveStorage();
            details = "D|" + details +"|" +  dateOrDateTime;
            return details;


    }
    public String toString() {
        return "[D]" + super.toString() + "(" + dateOrDateTime + ")";
    }
}
