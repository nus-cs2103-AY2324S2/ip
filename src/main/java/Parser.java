public class Parser {

    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public static Command parse(String userInput) throws JamieException {
        String[] words = userInput.split(" ", 2);
        if (words.length < 1) {
            throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String command = words[0].toLowerCase();
        String details = words.length > 1 ? words[1] : "";

        switch (command) {
            case "bye":
                return new ExitTaskCommand();

            case "list":
                return new ListTasksCommand();

            case "done":
                return new CompleteTaskCommand(Integer.parseInt(details));

            case "delete":
                return new DeleteTaskCommand(Integer.parseInt(details));

            case "todo":
                if (details.trim().isEmpty()) {
                    throw new JamieException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new AddTaskCommand(new ToDo(details));

            case "deadline":
                String[] deadlineDetails = details.split(" /by ");
                if (deadlineDetails.length != 2 || deadlineDetails[0].trim().isEmpty() || deadlineDetails[1].trim().isEmpty()) {
                    throw new JamieException("OOPS!!! Invalid format for deadline. Please use: deadline <description> /by <date>");
                }
                return new AddTaskCommand(new Deadline(deadlineDetails[0], deadlineDetails[1]));

            case "event":
                String[] eventDetails = details.split(" /from ");
                if (eventDetails.length != 2) {
                    throw new JamieException("OOPS!!! Invalid format for event. Please use: event <description> /from <start> /to <end>");
                }
                String[] eventTiming = eventDetails[1].split(" /to ");
                if (eventTiming.length != 2 || eventTiming[0].trim().isEmpty() || eventTiming[1].trim().isEmpty()) {
                    throw new JamieException("OOPS!!! Invalid format for event timing. Please use: event <description> /from <start> /to <end>");
                }
                return new AddTaskCommand(new Event(eventDetails[0], eventTiming[0], eventTiming[1]));

                default:
                    throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
