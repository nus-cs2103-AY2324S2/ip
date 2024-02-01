package lex.tasks;

public class Deadline extends Task {
    protected String end;

    public Deadline(String title, String end) {
        super(title);
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), end);
    }
}
