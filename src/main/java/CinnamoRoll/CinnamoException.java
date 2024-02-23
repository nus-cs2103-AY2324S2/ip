package CinnamoRoll;

/**
 * Raises an (general) error if user command is not in the list of command specified
 * in the enum class
 */
class CinnamoException extends Exception {
    /**
     * Outputs error message raised to the users in a string format
     */
    @Override
    public String toString() {
        return "Hi! I'm Cinnamo:) Please provide me correct instruction again >.<";
    }
}
