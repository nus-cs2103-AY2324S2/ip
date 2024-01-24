package model;

public class Task {
    private Boolean isCompleted = false; // Default value = false
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void markTaskCompleted() {
        this.isCompleted = true;
    }

    public void markTaskNotCompleted() {
        this.isCompleted = false;
    }

    // @author Sherisse-reused
    // Reused from https://nus-cs2103-ay2324s2.github.io/website/schedule/week2/project.html, Level-3 Extension A-Classes Partial Solution
    // with only function name modification
    private String getCompletedIcon() {
        return (isCompleted ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getCompletedIcon() + "] " + this.name;
    }
}
