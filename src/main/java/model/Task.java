package model;

public class Task {
    private Boolean completed = false; // Default value = false
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void setTaskCompleted() {
        this.completed = true;
    }

    public void setTaskUncompleted() {
        this.completed = false;
    }

    // TODO: Add getStatusIcon(), See: https://nus-cs2103-ay2324s2.github.io/website/schedule/week2/project.html under Level-3 Partial Solution

    @Override
    public String toString() {
        String value = "[";

        if (this.completed) {
            value += "x] ";
        }
        else {
            value += " ] ";
        }

        value += this.name;

        return value;
    }
}
