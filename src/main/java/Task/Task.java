package Task;

import NicoleExceptions.NicoleException;

import java.time.LocalDate;

public class Task {
    private boolean completed;
    private String name;

    public Task() {
        this.completed = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Task taskFactory(String name, char taskType) throws NicoleException {
        if (taskType == 'T') {
            return new Todo(name);
        } else if (taskType == 'D') {
            return new Deadline(name);
        } else {
            return new Event(name);
        }
    }

    public String markDone() throws NicoleException {
        if (this.completed) {
            throw new NicoleException("That is already marked complete -_-");
        } else {
            this.completed = true;
            return "Nicole: Marked as completed! Good job :3";
        }
    }

    public String markUndone() throws NicoleException {
        if (!this.completed) {
            throw new NicoleException("That is already marked incomplete -_-");
        } else {
            this.completed = false;
            return "Nicole: Marked as incomplete. We'll get em next time";
        }
    }

    public LocalDate getDate() {
        return LocalDate.parse("+999999999-12-31");
    }

    @Override
    public String toString() {
        return this.completed ? "[C] " + this.name : "[I] " + this.name;
    }

    public boolean equals(Task task) {
        return this.completed == task.completed;
    }
}
