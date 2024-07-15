package hanxiao.exception;

/**
 * Exception for all kinds of buggy inputs
 */
public class HanxiaoException extends Exception {

    /**
     * Constructor
     */
    public HanxiaoException() {
        super();
    }

    /**
     * Print this message when error caught.
     *
     * @return A starter of every error reply.
     */
    @Override
    public String getMessage() {
        return String.format("err... Please accept my sincere apologies, but");
    }
}
