/**
 * This is a Task Class.
 * Tasks contain a description and can be marked and unmarked as done.
 */

package task;

import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = cleanWhiteSpace(description);
        this.isDone = false;
    }

    /**
     * Returns "X" if the task has already been completed.
     *
     * @return either "X" or a white space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Removes any white space found at the end of the string provided.
     *
     * @param word the word provided
     * @return string containing no white space
     */
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
