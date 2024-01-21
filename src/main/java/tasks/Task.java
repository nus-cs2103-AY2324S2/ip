package tasks;

abstract class Task {  // default access modifier
    private String description;
    private boolean isDone;

    Task(String description) {  // default access modifier
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    String markAsDone() {  // default access modifier
        isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    String markAsUndone() {  // default access modifier
        isDone = false;
        return "OK, I've marked this task as not done yet:\n  " + this;
    }

    String getStatusIcon() {  // default access modifier
        return isDone ? "X" : " ";
    }
}
