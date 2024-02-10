package tasks;

import utilities.ResponseHandler;

public class Task {
    private String taskName;
    private boolean taskDone = false;
    private String typeOfTask;

    private String[] dateFromAndTo = new String[] {"NA", "NA"};
    private String[] timeFromAndTo = new String[] {"NA", "NA"};

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName, String typeOfTask) {
        this.taskName = taskName;
        this.typeOfTask = typeOfTask;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public String getTaskType() {
        return this.typeOfTask;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public boolean isTaskDone() {
        return this.taskDone;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return The status icon ("X" if the task is done, " " (space) otherwise).
     */
    public String getStatusIcon() {
        return (this.taskDone ? "X" : " ");
    }

    public void setDate(String[] times) {
        this.dateFromAndTo[0] = times[0];
        this.dateFromAndTo[1] = times[1];
    }

    /**
     * Sets the time for the task based on the given array of times.
     *
     * @param times An array of times representing the start and end times of the task.
     */
    public void setTime(String[] times) {
        this.timeFromAndTo[0] = times[0];
        this.timeFromAndTo[1] = times[1];
    }



    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string including the task's status icon and name.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

    /**
     * Changes the status of the task based on the given action.
     *
     * @param action The action to be performed ("mark" to mark as done, other to mark as not done).
     */
    public String changeStatus(String action) {
        this.taskDone = action.equals("mark");
        return ResponseHandler.markActionPrint(action, this);
    }

    /**
     * Changes the status of the task based on the given action code.
     *
     * @param action The action code (1 to mark as done, 0 to mark as not done).
     */
    public void changeStatus(int action) {
        this.taskDone = action == 1;
    }

    /**
     * Gets the times associated with the task.
     *
     * @return An array of times representing the start and end times of the task.
     */
    public String[] getTimes() {
        return this.timeFromAndTo;
    }

    public String[] getDates() {
        return this.dateFromAndTo;
    }
}
