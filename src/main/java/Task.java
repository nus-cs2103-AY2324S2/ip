public abstract class Task {
    private String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    // To mark the task as completed
    public void mark() {
        this.completed = true;
    }

    // To mark the task as uncompleted
    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}

class ToDo extends Task {
    public ToDo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String by;
    public Deadline (String deadline) {
        String[] token = deadline.split(" /by ");
        super(token[0]);
        this.by = token[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;
    public Event(String event) {
        String[] token = event.split(" /from |\\ /to ");
        super(token[0]);
        this.from = token[1];
        this.to = token[2];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to;
    }
}