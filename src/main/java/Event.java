import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String start, String end, String task) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public String isEvent() {
        return "[E]";
    }
    public String getEvent() {
         try {
             LocalDate startDate = LocalDate.parse(start);
             LocalDate endDate = LocalDate.parse(end);
             return ("from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + ")");
         } catch (DateTimeParseException e) {
            return "(from: " + start + " to: " + end + ")";
        }
    }

    @Override
    public String add() {
            return "    " + this.isEvent() + this.marked() + " "
                    + this.getTask() + this.getEvent();
    }

    @Override
    public void writeToFile(File filePath) {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            fw.write (this.isEvent() + this.marked() + " "
                    + this.getTask() + this.getEvent() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("file not found! try again xx");
        }
    }
}
