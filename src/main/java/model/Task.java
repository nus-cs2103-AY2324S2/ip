package model;

public class Task {
    public Boolean isCompleted = false; // Default value = false
    public String name;

    public Task(String name) {
        this.name = name;
    }

    public void markTaskCompleted() {
        this.isCompleted = true;
    }

    public void markTaskNotCompleted() {
        this.isCompleted = false;
    }

    // @@author SherisseTJW-reused
    // Reused from https://nus-cs2103-ay2324s2.github.io/website/schedule/week2/project.html, Level-3 Extension A-Classes Partial Solution
    // with only function name modification
    private String getCompletedIcon() {
        return (isCompleted ? "X" : " ");
    }

    public String getTypeIcon() {
        return null;
    };

    public String toTaskListStringFormat() {
        String completedValue = this.isCompleted ? "1" : "0";
        return String.format("%s|%s", completedValue, this.name);
    }


    @Override
    public String toString() {
        return "[" + this.getTypeIcon() + "][" + this.getCompletedIcon() + "] " + this.name;
    }
}
