public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        return codeBox + statusBox + " " + description;
    }
}
