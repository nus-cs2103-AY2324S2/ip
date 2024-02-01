package lex.tasks;

public class Todo extends Task {

    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
