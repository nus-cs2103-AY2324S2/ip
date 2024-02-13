package Aaron.Task;

import Aaron.Exception.DoubleMarkException;
import Aaron.Exception.TaskNoNameException;

/**
 * class that represents a task instantiated in the tasklist of aaronbot
 */
public abstract class Task {
    private boolean isDone;
    private String taskString;

    public Task(String taskString) throws TaskNoNameException {
        this.isDone = false;
        if (taskString.trim().isEmpty()) {
            throw new TaskNoNameException("STUDENT!! You need a task description :)");
        } else {
            this.taskString = taskString;
        }
    }

    public Task(String taskString, boolean isDone) {
        this.isDone = isDone;
        this.taskString = taskString;
    }

    public void markDone() throws DoubleMarkException {
        if (isDone) {
            throw new DoubleMarkException("Task is already marked \n " + this.toString());
        } else {
            isDone = true;
        }
    }

    public boolean unmarkDone() throws DoubleMarkException {
        if (!isDone) {
            throw new DoubleMarkException("Task is already unmarked \n " + this.toString());
        } else {
            isDone = false;
            return true;
        }
    }

    @Override
    public String toString() {
        char completeTick = isDone ? 'X' : ' ';
        return "[" + completeTick + "] | " + taskString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return (this.taskString.equals(task.taskString));
    }

    /**
     * Method to return if a task contains a keyword
     * 
     * @param userInput user input
     * @return boolean if the task contains the keyword
     */
    public boolean searchWord(String userInput) {
        return taskString.contains(userInput);
    }
}
