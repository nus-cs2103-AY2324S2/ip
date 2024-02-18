package dave.tasks;

import java.time.LocalDateTime;

import dave.utils.DateTimeUtil;

public class Task {
    /** Name or description of task. */
    protected String desc;
    /** Completion status of task. */
    protected boolean isDone;

    /**
     * Creates new Task object.
     * 
     * @param desc Name or description of Task object.
     */
    public Task(String descInput) {
        desc = descInput;
        isDone = false;
    }

    public String getStatusIcon() {
        /**
         * Solution below inspired by
         * https://nus-cs2103-ay2324s2.github.io/website/schedule/week2/project.html
         * partial solution under Duke Level-3: Mark as Done
         */
        return (isDone ? "X" : " "); // mark X if task is completed
    }

    public String getTaskName() {
        return desc;
    }

    public void setDone(boolean isDoneInput) {
        isDone = isDoneInput;
    }

    /**
     * Checks if a task has a due date.
     * Only applicable for deadline or event tasks.
     * 
     * @return True for deadline or event tasks, false otherwise.
     */
    public boolean isTaskWithDueDate() {
        return true;
    }

    public LocalDateTime getDueDate() {
        return null;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Checks if a given deadline or event task is due within a week.
     * 
     * @return True if deadline or event task is due within a week, false otherwise.
     */
    public boolean isTaskDueWithinAWeek() {
        if (isTaskWithDueDate()) {
            return DateTimeUtil.isWithinAWeekFromNow(getDueDate());
        }
        return false;
    }

    /**
     * Formats the printing of the Task object when shown to user.
     * 
     * @return Printing of the Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), desc);
    }

    /**
     * Formats the output of the Task object in output file.
     * 
     * @return The output to be written in the output file.
     */
    public String fileString() {
        return String.format("%s | %s", getStatusIcon().equals("X") ? "COMPLETED" : "INCOMPLETE", desc);
    }
}
