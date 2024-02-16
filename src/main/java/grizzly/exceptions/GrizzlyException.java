package grizzly.exceptions;

/**
 * This class is the parent class for all Exceptions specific to the Grizzly Bot.
 *
 * @author delishad21
 */
public class GrizzlyException extends Exception {
    /**
     * Creates a GrizzlyException, thrown when an error specific to the grizzlybot happens.
     *
     * @param errorMsg error message to print
     */
    public GrizzlyException(String errorMsg) {
        super(errorMsg);
    }

}
