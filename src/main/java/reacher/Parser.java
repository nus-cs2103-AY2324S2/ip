package reacher;
public class Parser {
    public static Command parse(String input) throws ReacherException {
        if (input.equalsIgnoreCase("bye")) { //check for end request
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {// check for list request
            return new ListCommand();
        } else if (input.equalsIgnoreCase("edit")) {
            return new EditCommand();
        } else if (input.equalsIgnoreCase("add")){
            return new AddCommand();
        } else {
            throw new ReacherException("Not a valid command!");
        }
    }
}
