package entity;

public abstract class Task {
    protected String title;
    protected boolean marked;

    Task(String title) {
        this.title = title;
        this.marked = false;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public void setMarked(boolean bool) {
        this.marked = bool;
    }

    public abstract String save();

}
