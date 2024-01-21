package model;

public class Task {
    private final String title;

    public Task(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
