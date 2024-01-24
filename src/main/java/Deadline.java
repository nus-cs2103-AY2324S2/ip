public class Deadline extends Task {
    protected String end;

    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }    


    public String toString() {
        return "[D]" + super.toString() + " (by: " + end + ")";
    }
    
}
