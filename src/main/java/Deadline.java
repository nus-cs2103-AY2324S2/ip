public class Deadline extends Task{
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String show() {
        super.status = isDone? "X": " ";
        String byFormat = "(by: " + this.by + ")";
        return "[D]" + "[" + status + "]" + " " + this.description + " " + byFormat;
    }
}
