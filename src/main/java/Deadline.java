public class Deadline extends Task {
    private String by;

    Deadline(String description, String by, String input) {
        super(description, input);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public void mark() {
        super.setComplete();
        System.out.println("\tNice! I've marked this task as done:\n\t" + this.toString());
    }

    @Override
    public void unmark() {
        this.setIncomplete();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.toString());
    }

    public String toFileFormat() {
        return String.format("Deadline | %s | %s", super.statusNumber, super.input);
    }
}
