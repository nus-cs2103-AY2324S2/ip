public class Task {
    private boolean marked;
    private String item;

    public Task(String item) {
        this.item = item;
        this.marked = false;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public String stringify() {
        return this.item;
    }
}