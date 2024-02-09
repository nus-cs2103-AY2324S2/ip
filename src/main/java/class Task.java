class Task {
    private String name;
    private int index;
    private boolean isDone;

    Task(int index, String name) {
        this.isDone = false;
        this.index = index;
        this.name = name;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("%d. [X] %s", this.index, this.name);
        } else {
            return String.format("%d. [ ] %s", this.index, this.name);
        }
    }
}