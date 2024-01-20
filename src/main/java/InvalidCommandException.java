public class InvalidCommandException extends DukeException{
    public InvalidCommandException() {
        super("Invalid Command",
                "Bro, it seems that you have entered the wrong command. You can enter one of the following commands:\n"
                 + "1. list\n"
                 + "2. mark\n"
                 + "3. unmark\n"
                 + "4. todo\n"
                 + "5. event\n"
                 + "6. deadline");

    }
}
