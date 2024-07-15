package hanxiao.exception;

/**
 * Exception for index error
 */
public class WrongIndexException extends HanxiaoException {
    private int listSize;
    /**
     * Constructor
     */
    public WrongIndexException(int listSize) {
        super();
        this.listSize = listSize;
    }

    /**
     * Print the correct range
     *
     * @return Print the correct range of index
     */
    @Override
    public String getMessage() {
        if (listSize == 0) {
            return "you should add a task first";
        }
        return String.format("%s the index should in range [1:%s]", super.getMessage(), listSize);
    }
}
