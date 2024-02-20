package task;

import java.util.Arrays;

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
        if (!word.isEmpty()) {
            while(word.charAt(word.length() - 1) == ' ') {
                word = word.substring(0, word.length() - 1);
            }
        }
        return word;
    }

    public boolean checkMatch(String match) {
        String[] brokenDesc = this.description.split("\\s+");
        for (String word : brokenDesc) {
            if (word.equals(match)) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
