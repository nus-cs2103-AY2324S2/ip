package CinnamoRoll;

/**
 * Raises an error is index input from the user is out of the range of items
 * in the list
 */
class CinnamoIndexException extends CinnamoException {
    /**
     * Outputs error message raised to the users in a string format
     */
    @Override
    public String toString() {
        return "The list index you provided is not on our to-do list yet :(( "
                + "Try it again! Cinnamo is always here >.<";
    }
}
