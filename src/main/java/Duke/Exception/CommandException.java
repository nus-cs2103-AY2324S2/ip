package Duke.Exception;

public class CommandException extends Throwable {
    public CommandException(String msg) {
        System.out.println(msg);
    }
}
