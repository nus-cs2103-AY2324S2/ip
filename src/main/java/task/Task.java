public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = cleanWhiteSpace(description);
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String cleanWhiteSpace(String word) {
        if (!word.isEmpty() && word.charAt(word.length() - 1) == ' ') {
            return word.substring(0, word.length() - 1);
        } else {
            return word;
        }
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
