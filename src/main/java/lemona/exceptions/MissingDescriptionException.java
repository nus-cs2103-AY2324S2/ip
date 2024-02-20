package lemona.exceptions;

/**
 * An exception class handling when descriptions of tasks are missing.
 */
public class MissingDescriptionException  extends Exception{

    /**
     * Prints the error messages corresponding to the type of command.
     *
     * @param string the type of task user used.
     * @return the string message to be printed
     */
    public String toString(String string) {
        String str = "I think you haven't had enough vitamin K." +
                "\nYour input should be in format of:";
        if (string.equalsIgnoreCase("todo")) {
            str = str + "\n\t{ todo (Task) }";
        } else if (string.equalsIgnoreCase("deadline")) {
            str = str + "\n\t{ deadline (Task) (/by DueDate) }";
        } else if (string.equalsIgnoreCase("event")) {
            str = str + "\n\t{ event (Task) (/from StartDate) (/to EndDate) }";
        }
        str = str + "\nI suggest you take some LEMONA.";
        return str;
    }
}
