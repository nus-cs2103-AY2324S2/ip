package exception;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Input in the form:\nevent Hackathon /from 8/10/2024 2000 /to 12/10/2024 1600");
    }
}
