package dukeexceptions;
/** Exception when an empty field is passed for a deadline*/
public class DeadlineEmptyException extends EmptyFieldException {
    /**
     * Asks the user what time the task he
     * @param userInput
     */
    public DeadlineEmptyException (String userInput) {
        super(String.format("by - What time does %s end by?", userInput));
    }
}
