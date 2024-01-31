//// Solution below adapted by week2 iP Level-3 Partial solution
import java.time.LocalDate;
public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String destription, LocalDate by){
        super(destription);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
