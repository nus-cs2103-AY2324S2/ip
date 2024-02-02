import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    private LocalDate deadline;
    public Deadline(String s, String deadline){
        super(s);
        this.deadline = TimeFormatter.stringToTime(deadline);
    }

    public Deadline(String s, boolean mark, String deadline){ //constructor used for loading
        super(s);
        this.deadline = TimeFormatter.loadTimeFromString(deadline);
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }
    @Override
    public String toString(){
        String X = this.getMark() ? "X" : " ";
        return "[D]"+"[" + X + "] " + this.getItem()
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
