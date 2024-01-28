public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        DateFormatter dateFormat = new DateFormatter(); 
        this.by = dateFormat.convertedDate(by);
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description, isDone); 
        DateFormatter dateFormat = new DateFormatter(); 
        this.by = dateFormat.convertedDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return "D|" + strDone + "|" + this.description + "|" + this.by;
    }
}