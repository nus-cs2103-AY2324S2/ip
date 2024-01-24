package entity;

public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }
    @Override
    public String toString() {
        if (this.marked) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }
}
