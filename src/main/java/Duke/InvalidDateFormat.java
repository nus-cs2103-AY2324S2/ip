package Duke;
public class InvalidDateFormat extends DukeException {
    public InvalidDateFormat() {
        super("Invalid DateTime Format! Please use yyyy-mm-dd");
    }
}
