package jivox.exception;

/**
 * Represents a Jivox exception when a task cannot be found in database.
 */
public class JivoxNoTaskFoundException extends JivoxException {
    private int taskNum;

    /**
     * Constructor for the JivoxNoTaskFoundException.
     *
     * @param taskNum the number of task in the
     */
    public JivoxNoTaskFoundException(int taskNum) {
        this.taskNum = taskNum;
    }


    @Override
    public String toString() {
        return String.format("%s Unable to find the task with number %d!",
                super.toString(),
                this.taskNum
        );
    }
}
