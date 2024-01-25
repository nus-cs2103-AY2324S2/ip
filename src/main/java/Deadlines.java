public class Deadlines extends Task {
    private String deadline;
    
    Deadlines(String description, String deadline, int num) {
        super(description, num);
        this.deadline = deadline;
    }

    public String toString() {
        if (this.isMarked()) {
            return "[D][X] " + super.toString() + "(by: " + this.deadline.substring(3) + ")";
        } else {
            return "[D][ ] " + super.toString() + "(by: " + this.deadline.substring(3) + ")";
        }
    }

    @Override
    public String identifier() {
        return "[D]";
    }


    
}
