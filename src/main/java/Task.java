public class Task {
    private String name;
    private boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
    public String toSaveFormat() {
        return "| " + (isDone ? "true" : "false") + " | " + name;
    }
}

class ToDo extends Task {
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T " + super.toSaveFormat() + "\n";
    }
}

class Deadline extends Task {
    private String by;
    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +" (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " | " + by + "\n";
    }
}

class Event extends  Task {
    private String from;
    private String to;
    public Event(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (from: " + from + " to: " + to +")";
    }

    @Override
    public String toSaveFormat() {
        return "E " + super.toSaveFormat() + " | " + from + " | " + to + "\n";
    }
}