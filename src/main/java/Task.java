public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + isDoneStatus() + "] " + description;
    }

    public void toggleIsDone(String isDoneUpdateString) {

        // obsolete toggles
        if (isDoneUpdateString.equals("mark") && isDone) return;
        if (isDoneUpdateString.equals("unmark") && !isDone) return;
        isDone = !isDone;
    }

    private char isDoneStatus() {
        return isDone ? 'X' : ' ';
    }

    public String updateIsDoneMessage() {
        if (isDone) return "Nice! I've marked this task as done:"
                + '\n'
                + "    "
                + toString();

        if (!isDone) return "OK, I've marked this task as not done yet:"
                + '\n'
                + "    "
                + toString();

        return null;
    }
}
