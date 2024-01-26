public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description;
    }
}
