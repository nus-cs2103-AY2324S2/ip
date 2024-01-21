/**
 * Exception for index error
 */
public class WrongIndexException extends DukeException {

    /**
     * Constructor
     */
    public WrongIndexException() {
        super();
    }

    /**
     * Print the correct range
     * @return Print the correct range of index
     */
    @Override
    public String getMessage() {
        if (Task.task_list.size()==0) {
            return "you should add a task first";
        }
        return String.format("%s the index should in range [1:%s]", super.getMessage(), Task.task_list.size());
    }
}
