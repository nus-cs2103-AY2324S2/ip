public class Task {
    private String title;
    private boolean marked;

    Task(String title) {
            this.title = title;
            this.marked = false;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean getMarked() {
        return this.marked;
    }

    public void setMarked(boolean bool) {
        this.marked = bool;
    }
}
