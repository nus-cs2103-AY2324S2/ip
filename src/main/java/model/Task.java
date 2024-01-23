package model;

public class Task {
    private String label;

    public Task(String label) {
        this.label = label;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task and all its details
     */
    @Override
    public String toString() {
        return label;
    }
}
