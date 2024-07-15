package hanxiao.exception;

/**
 * Exception for lack of description of certain things
 */
public class EmptyTextException extends HanxiaoException {
    private String field;
    private String task;

    /**
     * Constructor
     *
     * @param field
     * @param task
     */
    public EmptyTextException(String field, String task) {
        this.field = field;
        this.task = task;
    }

    /**
     * Override the getMessage method
     *
     * @return xxx of yyy cannot be empty
     */
    @Override
    public String getMessage() {
        return String.format("%s the %s of %s cannot be empty.", super.getMessage(), this.field, this.task);
    }
}
