package CinnamoRoll;

/**
 * Raises an exception when taskname for todo task was not provided by users
 */
class CinnamoTodoException extends CinnamoException {
    /**
     * Outputs error message raised to the users in a string format
     */
    @Override
    public String toString() {
        return "Hi! Please provide details for todo activities "
                + "you want Cinnamo to record! :)";
    }
}
