/** Encapsulates TODO task. Inherits from Task.
 * @author Tan Qin Yong
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}