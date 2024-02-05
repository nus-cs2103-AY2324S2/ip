public class Deadline extends Task {
    String by;

    Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    Deadline(String desc, boolean isDone, String by) {
        super(desc, isDone);
        this.by = by;
    }

    public String toSave() {
        // need to store status as well
        return "D - " + super.toSave() + " - " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

}
