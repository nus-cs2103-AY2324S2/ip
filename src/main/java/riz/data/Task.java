package riz.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class consists of the task description and
 * information on if the task is already completed or not.
 */
public abstract class Task {
    private String task;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    /**
     * To mark the task as completed
     */

    public void mark() {
        this.isCompleted = true;
    }

    /**
     * To mark the task as uncompleted
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * To search if a particular word is found in the task description.
     * @param word
     * @return a boolean true/false indicating whether the word was found
     * in the task description. True if found, false otherwise.
     */
    public boolean find(String word) {
        return this.task.contains(word);
    }

    /**
     * String representation of the portion on whether the task is completed or not.
     * @return String representation of if the task is completed/uncompleted.
     * 'X' for completed, ' ' for uncompleted.
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return " | X | " + this.task;
        } else {
            return " |   | " + this.task;
        }
    }
}