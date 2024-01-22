public abstract class Task {
    private String title;
    private boolean isMarked;

    public Task(String title) {
        this.title = title;
        this.isMarked = false;
    }

    public String getTitle() {
        return this.title;
    }

    public void setMarked() {
        this.isMarked = true;
    }

    public void setUnmarked() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.title;
        }
        return "[ ] " + this.title;
    }
}
