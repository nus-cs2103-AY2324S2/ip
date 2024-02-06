class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("     [X] %s\n", this.name);
        } else {
            return String.format("     [ ] %s\n", this.name);
        }
    }
}