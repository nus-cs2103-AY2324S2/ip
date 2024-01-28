public class Parser {
    public Parser() {

    }

    public Command parse(String input) throws BelleException {
        String[] inputlist = input.split(" ");

        if (inputlist[0].equals("delete")) {
            return new DeleteCommand(inputlist[1]);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (inputlist[0].equals("mark")) {
            return new MarkCommand(inputlist[1]);
        } else if (inputlist[0].equals("unmark")) {
            return new UnmarkCommand(inputlist[1]);
        } else if (inputlist[0].equals("todo") || inputlist[0].equals("deadline") || inputlist[0].equals("event")) {
            if (inputlist[0].equals("todo")) {
                return new AddTaskCommand("todo", input);
            } else if (inputlist[0].equals("deadline")) {
                return new AddTaskCommand("deadline", input);
            } else {
                return new AddTaskCommand("event", input);
            }
        } else {
            throw new BelleException("Not a valid command");
        }

    }
}
