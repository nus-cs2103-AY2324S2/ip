import Exceptions.InvalidInputException;
import org.w3c.dom.Text;

import java.util.regex.Pattern;
public class TextReader {
    private boolean isActive;

    public TextReader() {
        this.isActive = true;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void exit() {
        this.isActive = false;
    }

    public Actions getAction(String input) throws InvalidInputException {
        if (input.equals("bye")) {
            return Actions.BYE;
        }
        if (input.equals("list")) {
            return Actions.LIST;
        }
        if (input.equals("todo")) {
            throw new InvalidInputException(TextTemplate.TODO_NO_DESC);
        }
        if (Pattern.matches("todo .+", input)) {
            return Actions.TODO;
        }
        if (Pattern.matches("deadline .+ /by .+", input)) {
            return Actions.DEADLINE;
        }
        if (Pattern.matches( "event .+ /from .+ /to .+", input)) {
            return Actions.EVENT;
        }
        if (Pattern.matches("mark \\d+", input)) {
            return Actions.MARK;
        }
        if (Pattern.matches("unmark \\d+", input)) {
            return Actions.UNMARK;
        }
        if (Pattern.matches("delete \\d+", input)) {
            return Actions.DELETE;
        }
        return Actions.INVALID;
    }
}
