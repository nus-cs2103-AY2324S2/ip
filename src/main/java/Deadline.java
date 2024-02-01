import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dueDate;

    public Deadline(String description, String by){
        super(description, 'D');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dueDateParsed = LocalDateTime.parse(by.trim(), formatter);
        this.dueDate = dueDateParsed;
    }

    public String dateInWords(){
        String dayWeek = dueDate.getDayOfWeek().toString();
        int dayMonth = dueDate.getDayOfMonth();
        String month = dueDate.getMonth().toString();
        int year = dueDate.getYear();
        return dayWeek + " " + dayMonth + " " + month + " " +year;
    }
    @Override
    public String toString(){
        String str = String.format(
                super.toString() + " (%s)", this.dateInWords());
        return str;
    }
}
