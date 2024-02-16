package hanxiao.exception;

/**
 * Exception for time format incorrect
 */
public class TimeFormatException extends HanxiaoException {

    /**
     * Constructor of this exception.
     */
    public TimeFormatException() {
        super();
    }

    /**
     * Print out the correct format.
     *
     * @return guide for a correct format
     */
    @Override
    public String getMessage() {
        return String.format("%s your time should in this format: yyyy-mm-dd", super.getMessage());
    }
}
