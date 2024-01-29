package entity;

public class Deadline extends Task {
    private String dateTime;
    public Deadline(String title, String dateTime) {
        super(title);
        this.dateTime = dateTime;
    }

    @Override
    public String save() {
        if (this.marked) {
            return "D | Done | " + this.title;
        } else {
            return "D | Not Done | " + this.title;
        }
    }
    @Override
    public String toString() {
        if (this.marked) {
            return "[D][X] " + this.title + "by " + this.dateTime;
        } else {
            return "[D][ ] " + this.title + "by " + this.dateTime;
        }
    }
}
