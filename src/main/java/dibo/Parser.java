package dibo;

public class Parser {
    static Command parse(String fullCommand) throws DiboException {
        String[] commandDetails = fullCommand.split(" ");
        switch (commandDetails[0]) {
        case "list":
            return new ListCommand();
        case "unmark":
            try {
                int taskId = Integer.parseInt(commandDetails[1]);
                return new UnmarkCommand(taskId);
            } catch (NumberFormatException e) {
                throw new DiboException("Oh no sir! You have to unmark the items based on their index.\n"
                        + "If you are not sure of the index, enter 'list' to check it out :D");
            }
        case "mark":
            try {
                int taskId = Integer.parseInt(commandDetails[1]);
                return new MarkCommand(taskId);
            } catch (NumberFormatException e) {
                throw new DiboException("Oh no sir! You have to Mark the items based on their index.\n"
                        + "If you are not sure of the index, enter 'list' to check it out :D");
            }
        case "delete":
            try {
                int taskId = Integer.parseInt(commandDetails[1]);
                return new DeleteCommand(taskId);
            } catch (NumberFormatException e) {
                throw new DiboException("Oh no sir! You have to delete the items based on their index."
                        + "If you are not sure of the index, enter 'list' to check it out :D");
            }
        case "bye":
            return new ByeCommand();
        default:
            return new AddCommand(fullCommand);
        }
    }
}
