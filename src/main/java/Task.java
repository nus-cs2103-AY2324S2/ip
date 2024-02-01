abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public static Task parseFromFileString(String string) {
        String[] components = string.split("\\]");
        String type = components[0].substring(1);
        boolean isDone = Boolean.parseBoolean(components[1]);
        String description = components[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, isDone);
            case "E":
                return new Event(description, isDone);
            default:
                return null;

        }
    }

    public abstract String toString();
}

