public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[T][" + statusIcon + "] " + description;
    }
}
