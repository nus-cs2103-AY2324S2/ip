abstract class Task {
    private String description;
    private boolean marked;
    private int num;

    Task(String description, int num) {
        this.description = description;
        this.marked = false;
        this.num = num;
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

    public int getNum() {
        return this.num;
    }

    @Override
    public String toString() {
        return this.description;
    }

    abstract String identifier();
    
}
