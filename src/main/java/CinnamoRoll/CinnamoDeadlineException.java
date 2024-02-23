package CinnamoRoll;

/**
 * Raises an error is taskname and time input from the user is not in the expected format
 * for deadline class
 */
class CinnamoDeadlineException extends CinnamoException {
    /**
     * Outputs error message raised to the users in a string format
     */
    @Override
    public String toString() {
        return "Hi! Please provide more details for the todo activity in "
                + "the correct format with \"/by\" followed by the specific deadline:) -- Cinnamo >.<";
    }
}
