package ezra;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }

    public String toString2() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ToDo t)) {
            return false;
        }
        return this.description.equals(t.description);
    }
}
