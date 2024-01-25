public class Task {
    String command;
    boolean completed;

    public Task(String command) {
        this.command = command;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String checkIfCompleted = "X";
        if (!this.completed) {
            checkIfCompleted = " ";
        }
        String s = "["+ checkIfCompleted +"] " + this.command;
        return s;
    }
}
