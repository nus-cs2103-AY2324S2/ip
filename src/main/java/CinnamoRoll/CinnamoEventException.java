package CinnamoRoll;

/**
 * Raises an error is taskname and time input from the user is not in the expected format
 * for event class
 */
class CinnamoEventException extends CinnamoException {
    /**
     * Outputs error message raised to the users in a string format
     */
    @Override
    public String toString() {
        return "Hi! Please provide detailed schedule for the event with \"/from\" to indicate "
                + "the starting time and \"/to\" to indicate ending time of the event:) -- Cinnamo >.<";
    }
}
