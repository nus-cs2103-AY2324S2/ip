public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void unmarkAsDone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "✔" : " "); // mark done task with X
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileString() {
        // " | 0 | task description"
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    public static Task parseFromFileString(String fileString) {
        // "T | 1 | read book"
        String[] parts = fileString.split(" \\| ");

        char taskType = parts[0].charAt(0);  // Extract task type
        int status = Integer.parseInt(parts[1]);  // Extract status as integer
        String description = parts[2];  // Extract description

        Task task;
        if (taskType == 'T') {
            task = new Todo(description);
        } else if (taskType == 'D') {
            // Assuming Deadline constructor takes description and by
            task = new Deadline(description, parts[3]);
        } else if (taskType == 'E') {
            // Assuming Event constructor takes description, from, and to
            task = new Event(description, parts[3], parts[4]);
        } else {
            throw new IllegalArgumentException("Invalid task type");
        }

        // Set task status based on the parsed value
        if (status == 1) {
            task.markAsDone();
        }

        return task;
    }
}
