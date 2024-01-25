public class Deadline extends Task {
    String dueDate;
    public Deadline(String name, String dueDate) {
        super(name, "D");
        this.dueDate = dueDate;
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        return "[D] " + status + " " + this.name + " (" + this.dueDate + ")";
    }
}
