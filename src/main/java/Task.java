
class Task {
    protected SecretaryW.TaskType type;
    protected String description;
    protected boolean isDone;
    protected String time1;
    protected String time2;

    //constructor for TODO
    public Task(SecretaryW.TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    //constructor for DEADLINE
    public Task(SecretaryW.TaskType type, String description, String time1) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.time1 = time1;
    }

    // Constructor for creating TODO Task from file data
    public Task(SecretaryW.TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    // Constructor for creating DEADLINE Task from file data
    public Task(SecretaryW.TaskType type, String description, String time1, boolean isDone) {
        this.type = type;
        this.description = description;
        this.time1 = time1;
        this.isDone = isDone;
    }

    // Constructor for creating EVENT Task from file data
    public Task(SecretaryW.TaskType type, String description, String time1, String time2, boolean isDone) {
        this.type = type;
        this.description = description;
        this.time1 = time1;
        this.time2 = time2;
        this.isDone = isDone;
    }

    //constructor for EVENT
    public Task(SecretaryW.TaskType type, String description, String time1, String time2) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.time1 = time1;
        this.time2 = time2;
    }
    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    // Method to convert a Task to a string for writing to the file
    public String toFileString() {
        switch (type) {
            case TODO:
                return "T | " + (isDone ? "1" : "0") + " | " + description;
            case DEADLINE:
                return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + time1;
            case EVENT:
                return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + time1 + " | " + time2;
            default:
                return "";
        }
    }
    @Override
    public String toString() {
        switch (type) {
            case TODO: //todo
                return "[T]" + getStatusIcon() + " " + description;
            case DEADLINE: // deadline
                return "[D]" + getStatusIcon() + " " + description + " (by: " + time1 + ")";
            case EVENT: // event
                return "[E]" + getStatusIcon() + " " + description + " (from: " + time1 + " to: " + time2 + ")";
            default:
                return ""; // Other types
        }
    }
}

enum TaskType {
    TODO, DEADLINE, EVENT
}