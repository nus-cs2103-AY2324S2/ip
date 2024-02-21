package jivox.exception;

import jivox.task.Task;

public class JivoxDuplicateTaskException extends JivoxException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s There is a duplicate task in your list!", super.toString());
    }
}
