package lemona.exceptions;

/**
 * An exception class handling when index of commands does not match the tasklist.
 */
public class OutOfIndexException extends Exception {

    /**
     * Prints the error messages corresponding to the type of command.
     *
     * @param index the size of the taskList.
     * @return the string message to be printed
     */
    public String toString(Integer index) {
        String message;
        if (index == 1) {
            message = "\nYou only have 1 task!";
        } else if (index == 0) {
            message = "\nYou do not have any task!";
        } else {
            message = "\nYou only have " + index + " tasks!";
        }
        String str = "I think you haven't had enough vitamin A." +
                message + "\nI suggest you take some LEMONA.";
        return str;
    }
}
