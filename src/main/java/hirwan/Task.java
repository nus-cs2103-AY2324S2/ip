package hirwan;

import java.time.format.DateTimeFormatter;

public abstract class Task {

    /**
     * the getMessage method which returns the string of the task tobe printed to the user
     * @return the string to be printed to the user
     */
    abstract public String getMessage();

    /**
     * the updateData method that updates the data in the external text file
     */
    abstract public void updateData();
}
