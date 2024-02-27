package CinnamoRoll;

/**
 * Raises an error is time input from the user is not in the expected format
 */
class CinnamoTimeException extends CinnamoException {
    /**
     * Outputs error message raised to the users in a string format
     */
    @Override
    public String toString() {
        return "Hi! Please provide time information in a correct "
                + "format of \"mm-dd-yyyy hh:mm\" >.<";
    }
}
