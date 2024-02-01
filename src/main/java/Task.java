public class Task {
    private boolean marked;
    private String item;

    public Task(String item, boolean marked) {
        this.item = item;
        this.marked = marked;
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

    public String getItem() {
        return this.item;
    }

    public String stringify() {
        return this.item;
    }
}