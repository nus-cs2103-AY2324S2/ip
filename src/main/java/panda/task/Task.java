package panda.task;

public class Task {
    private final String desc;
    private boolean isDone;

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
    
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    private String curStatus() {
        return "[" + (isDone ? 'X' : ' ') + "]";
    }

    public String toString() {
        return curStatus() + ' ' + desc;
    }

    public String saveString() {
        return (isDone ? "1" : "0") + " | " + desc;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }
         
        Task c = (Task) o;

        return desc.equals(c.desc) && isDone == c.isDone;
    }
}
