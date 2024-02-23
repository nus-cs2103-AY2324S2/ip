package dukeexceptions;
/** Exception when correct command is passed but is missing an empty field */
public class EmptyFieldException extends DukeException {
    /**
     * Concatenates the missing message to the corresponding empty field text
     *
     * @param err Error Message
     */
    public EmptyFieldException  (String err) {
        super("\nApologies Sir/Mdm, it appears as though you left the following fields empty:\n" + err);
    }

}
