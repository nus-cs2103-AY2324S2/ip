package hanxiao.exception;

/**
 * Exception for wrong usage
 */
public class WrongUsageException extends HanxiaoException {
    private String usage;

    /**
     * Constructor
     *
     * @param usage correct usage
     */
    public WrongUsageException(String usage) {
        this.usage = usage;
    }

    /**
     * Print the correct usage
     *
     * @return correct usage
     */
    @Override
    public String getMessage() {
        return String.format("%s the correct usage should be: %s", super.getMessage(), this.usage);
    }
}
