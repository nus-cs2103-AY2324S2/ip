public class Deadline extends Task {

    protected String by;
    public Deadline(String desc) {
        String[] splitDesc = desc.split(" /by ");
        this.description = splitDesc[0];
        this.by = splitDesc[1];
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}