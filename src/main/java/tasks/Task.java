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
     * Updates the name of the task.
     *
     * @param newName
     */
    public void updateTaskName(String newName) {
        this.taskName = newName;
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
        String currentStatusIcon = this.taskDone ? "X" : " ";
        assert (currentStatusIcon.equals("X") || currentStatusIcon.equals(" ")) : "Invalid getStatusIcon!";
        return currentStatusIcon;
    }

    public void setDate(String[] dates) {
        assert dates.length == 2 : "Invalid initialization of primitive dates array! "
                + "It should be of length 2";
        this.dateFromAndTo[0] = dates[0];
        this.dateFromAndTo[1] = dates[1];
    }

    /**
     * Sets the time for the task based on the given array of times.
     *
     * @param times An array of times representing the start and end times of the task.
     */
    public void setTime(String[] times) {
        assert times.length == 2 : "Invalid initialization of primitive times array! "
                + "It should be of length 2";
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
        assert action.equals("mark") || action.equals("unmark") : "Action should be either "
                + "mark or unmark!";
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
        assert timeFromAndTo.length == 2 : "Invalid initialization of primitive times array! "
                + "It should be of length 2";
        return this.timeFromAndTo;
    }

    public String[] getDates() {
        assert dateFromAndTo.length == 2 : "Invalid initialization of primitive dates array! "
                + "It should be of length 2";
        return this.dateFromAndTo;
    }
}
