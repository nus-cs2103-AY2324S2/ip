package ganAnWo.exception;

/**
 * Exception used when description format is wrong.
 *
 */
public class DescriptionFormatException extends Exception {

    /**
     * Constructor for DescriptionFormatException.
     *
     * @param e error message.
     */
    public DescriptionFormatException(String e) {
        super(e);
    }
}
