package Tasks;
import Exceptions.InvalidDescriptionException;
import Utils.utils;

public class Todo extends Task {

    public static Todo from(String s) throws InvalidDescriptionException {
        //Expects a string in the format "todo <description>"

        //get rid of the command
        String description = utils.discardFirstWord(s.trim()).trim();

        if (!description.isEmpty()){
            return new Todo(description);
        }else {
            throw new InvalidDescriptionException("The description of a todo cannot be empty.");
        }
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
