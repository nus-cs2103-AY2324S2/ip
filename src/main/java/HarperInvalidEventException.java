public class HarperInvalidEventException extends HarperException {
    public HarperInvalidEventException() {
        super("_________________________________________________________\n"
                + "Please follow the format: \"event [description] /from [start time] /to [end time]\"\n"
                + "to add a event task into your list.\n"
                + "All fields are required!\n"
                + "Only the whitespace after \"event\" is required\n"
                + "_________________________________________________________");
    }
}
