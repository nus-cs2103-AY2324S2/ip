//// Solution below adapted by week2 iP Level-3 Partial solution
public class Deadline extends Task{
    protected String by;

    public Deadline(String destription, String by){
        super(destription);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
