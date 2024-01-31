package blu.task;

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

    public boolean getIsMarked() {
        return isMarked;
    }

    public abstract String toCsv();

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[X] " + this.title;
        }
        return "[ ] " + this.title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return this.title.equals(other.title) && this.isMarked == other.isMarked;
    }
}
