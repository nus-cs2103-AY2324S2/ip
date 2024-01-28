public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public String toFile() {
        if(isDone){
            return "D|1|" + description + "|" + by;
        } else {
            return "D|0|" + description + "|" + by;
        }
    }
}
