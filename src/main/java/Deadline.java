import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{

    private LocalDate deadline;
    private boolean isValid = true;

    public Deadline(String description, String timing) {
        super(description);
        try {
            timing = timing.trim();
            System.out.println(timing);
            this.deadline = LocalDate.parse(timing);
        } catch (DateTimeException e) {
            System.out.println(e);
            deadline = null;
            isValid = false;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        if(this.isDone) {
            return  "[D][X] " + description + " (by: " + deadline.format(formatter) +")";
        }

        return "[D][ ] " + description + " (by: " + deadline.format(formatter) +")";
    }

    public String getDeadline() {
        return deadline.toString();
    }

    public  String getFormatDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return deadline.format(format);
    }


    public boolean getValid() {
        return isValid;
    }
}
