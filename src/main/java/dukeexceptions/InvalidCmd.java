package dukeexceptions;

/**
 * DukeException for when command is not recognised
 */
public class InvalidCmd extends DukeException {
    /**
     * Passes the command the user tried to type into the error message
     *
     * @param userInput
     */
    public InvalidCmd (String userInput) {
        super(String.format("My apologies Sir/Mdm, %s is simply beyond my comprehension.", userInput));
    }
}
