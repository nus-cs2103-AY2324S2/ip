package entity;

public class ToDos extends Task {
    public ToDos(String title) {
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
