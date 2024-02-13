package tasklist;

import parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DateTimeException;

public class Deadline extends Task {
    private LocalDateTime dateTime;
    public Deadline (String desc, String dateOrDateTimeUnformatted) {
        super(desc);
        try {
            this.dateTime = Parser.inputConvertToDateTime(dateOrDateTimeUnformatted);
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public Deadline (String desc, boolean isDone, String dateOrDateTimeStorage) {
        super(desc, isDone);
        this.dateTime = Parser.storageConvertToDateTime(dateOrDateTimeStorage);
    }
    @Override
    public String saveStorage(){
        String details = super.saveStorage();
            details = "D|" + details +"|" +  Parser.convertDateTimeToStringStorage(this.dateTime);
            return details;
    }
    public String toString() {
        return "[D]" + super.toString() + "(" + Parser.convertDateTimeToStringUI(this.dateTime) + ")";
    }
}
