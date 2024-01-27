package task;

import exceptions.InvalidStatusUpdateException;

import java.time.LocalDate;

public class Task {
    protected String name;
    protected boolean isMarked;

    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    public void updateStatus(boolean status) throws InvalidStatusUpdateException {
        if (this.isMarked == status) {
            throw new InvalidStatusUpdateException();
        }
        this.isMarked = status;
    }

    public boolean getStatus() {
        return this.isMarked;
    }

    public boolean queryByDate(LocalDate date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + (this.isMarked ? "[X] " : "[ ] ") + this.name;
    }

}
