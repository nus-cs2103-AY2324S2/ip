public class HarperInvalidDeadlineException extends HarperException {
    public HarperInvalidDeadlineException() {
        super("_________________________________________________________\n"
                + "Please follow the format: \"deadline [description] /by [deadline]\"\n"
                + "to add a deadline task into your list.\n"
                + "All fields are required.\n"
                + "Only the whitespace after \"deadline\" is required.\n"
                + "_________________________________________________________");
    }
}
