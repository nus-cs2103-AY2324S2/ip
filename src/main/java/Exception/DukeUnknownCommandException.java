package Exception;

public class DukeUnknownCommandException extends DukeException{

    public DukeUnknownCommandException() {
        super("Sorry boss your command is invalid :-(");
    }
}
