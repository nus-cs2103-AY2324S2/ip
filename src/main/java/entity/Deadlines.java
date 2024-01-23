package entity;

public class Deadlines extends Task {
    private String dateTime;
    public Deadlines(String title, String dateTime) {
        super(title);
        this.dateTime = dateTime;
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
