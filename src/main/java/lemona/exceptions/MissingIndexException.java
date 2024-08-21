package lemona.exceptions;

/**
 * An exception class handling when index of commands are missing.
 */
public class MissingIndexException extends Exception{

    /**
     * Prints the error messages corresponding to the type of command.
     *
     * @param string the type of command user used.
     * @return the string message to be printed
     */
    public String toString(String string) {
        String str = "I think you haven't had enough vitamin C." +
                "\nYou need to tell me which task to " + string +
                "!\nI suggest you take some LEMONA.";
        return str;
    }
}
