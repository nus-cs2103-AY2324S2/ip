package lemona.exceptions;

/**
 * An exception class handling when instructions by user are already done.
 */
public class DuplicateInstructionException extends Exception{

    /**
     * Prints the error messages corresponding to the type of command.
     *
     * @param string the type of command user used.
     * @return the string message to be printed
     */
    public String toString(String string) {
        String str;
        switch (string) {
        case "mark":
            str = "I think you haven't had enough vitamin D."
                    + "\nYour task is already marked!";
            break;
        case "unmark":
            str = "I think you haven't had enough vitamin D."
                    + "\nYour task is already unmarked!";
            break;
        default:
            // Same task already present in taskList. C-DetectDuplicates
            str = "I think you haven't had enough vitamin D."
                    + "\nYour task is already existing in the list!";
            break;
        }
        str = str + "\nI suggest you take some LEMONA";
        return str;
    }
}
