package Thames.task;

public class Task {
    protected String name;
    protected Boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getDone() {
        return this.isDone;
    }
    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + (isDone? "X":" ") + "] " + this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            return ((Task) o).name.equals(this.name) &&
                    ((Task) o).isDone.equals(this.isDone);
        }
        return false;
    }

}
