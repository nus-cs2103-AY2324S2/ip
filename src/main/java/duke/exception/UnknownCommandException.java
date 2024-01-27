package duke.exception;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Bro, it seems that you have entered the wrong duke.command. You can enter one of the following commands:\n"
                + "1. list\n"
                + "2. mark\n"
                + "3. unmark\n"
                + "4. delete\n"
                + "5. todo\n"
                + "6. event\n"
                + "7. deadline");

    }
}
