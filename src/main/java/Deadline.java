public class Deadline extends Task {
    String dueDate = "";
    public Deadline(String description, String dueDate) {
        super(description, "D");
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        String due = "(by: " + this.dueDate + ")";
        return codeBox + statusBox + " " + description + " " + due;
    }
}
