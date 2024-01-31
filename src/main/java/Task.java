abstract class Task {
    protected final String taskname;
    boolean is_marked;

    Task(String str) {
        this.taskname = str;
        this.is_marked = false;
    }

    Task(String str, boolean marked) {
        this.taskname = str;
        this.is_marked = marked;
    }

    protected void marked() {
        this.is_marked = true;
    }

    protected void unmarked() {
        this.is_marked = false;
    }

    abstract String getStatusIcon();

    abstract String added(int i);

    public String toString() {
        return this.getStatusIcon() + " " + this.taskname;
    }
}