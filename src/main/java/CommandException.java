public class CommandException extends InputException {
    public CommandException() {
        super(
                "Sorry, Invalid Command\n"
                + "use /by (date/time) for deadlines or use /from (date/time) and /to (date/time) for events\n"
                + "Please try again!\n"
        );
    }
}
