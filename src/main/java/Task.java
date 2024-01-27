public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public abstract String toFileString();

    // Convert a string from the file to a task
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");

        String taskType = parts[0].trim();
        boolean isDone = Integer.parseInt(parts[1].trim()) == 1;
        String description = parts[2].trim();

        Task task;
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            String by = parts[3].trim();
            task = new Deadline(description, by);
        } else if (taskType.equals("E")) {
            String from = parts[3].split(" - ")[0].trim();
            String to = parts[3].split(" - ")[1].trim();
            task = new Event(description, from, to);
        } else {
            // Handle unknown task type
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}