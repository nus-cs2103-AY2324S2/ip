package tasks;


public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, String isDone) {
        super(description);
        this.by = by;
        if(isDone.equals("0")){
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")" ; 
    } 

    @Override
    public String toStore() {
        return "D" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + by + "\n" ; 
    }
}
