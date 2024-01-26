public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description;
    }

    static public Task fromText(String description, String done) {
        Task task = new ToDo(description);
        task.isDone = done.equals("1");
        return task;
    }
}
