package dav;

class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String toDataString() {
        return "";
    }

    public static Task parseTask(String data) {
        if (data.startsWith("T")) {
            return TodoTask.parseTask(data);
        } else if (data.startsWith("D")) {
            return DeadlineTask.parseTask(data);
        } else if (data.startsWith("E")) {
            return EventTask.parseTask(data);
        }

        System.out.println("Error parsing task from data: " + data);
        return null;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

