public class Deadline extends Task {
    protected String end;

    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }    

    public Deadline(String name, boolean isDone, String end) {
        super(name, isDone);
        this.end = end;
    }    

    @Override
    public String toSave() {
        return "D" + super.toSave() + " | " + end;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";
    }
    
}
