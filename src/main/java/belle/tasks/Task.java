package belle.tasks;

import belle.others.BelleException;

/**
 * Represents tasks.
 */
public class Task {

    private boolean isDone;
    private String name;

    private enum Type {
        E,
        D
    }

    /**
     * Constructs Task.
     *
     * @param name Name of task.
     * @param isDone Boolean that indicates
     *             whether task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getIsDone() {
        String doneMark = "X";
        if (this.isDone) {
            return doneMark;
        } else {
            return " ";
        }
    }

    public void setTaskDone() {
        this.isDone = true;
    }

    public void setTaskUndone() {
        this.isDone = false;
    }

    /**
     * Returns Task in correct String format.
     *
     * @return Task in correct String format.
     */
    @Override
    public String toString() {
        return " [" + getIsDone() + "] " + this.name;
    }

    public String getType() {
        return "general";
    }

    public String getDone() {
        return Boolean.toString(this.isDone);
    }


    /**
     * Snoozes task and edits deadline.
     *
     * @param snoozeTask Task to be snoozed.
     * @param deadline New deadline.
     * @throws BelleException If a todo task
     *         is to be snoozed.
     */
    public void snooze(Task snoozeTask, String deadline) throws BelleException {
        if (snoozeTask.getType().equals(Type.D.name())) {
            DeadlineTask currTask = (DeadlineTask) snoozeTask;
            currTask.setDeadline(deadline);
        } else if (snoozeTask.getType().equals(Type.E.name())) {
            EventTask currTask = (EventTask) snoozeTask;
            currTask.setDeadline(deadline);
        } else {
            throw new BelleException("Trying to snooze an invalid Task type");
        }
    }
}
