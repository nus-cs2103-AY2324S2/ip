import java.lang.Exception;
public class UnknownInputException extends Exception {
    final String msg = "WHAT DO YOU MEANNNNN";

    public UnknownInputException() {
        super();
    }

    public String output() {
        return this.msg;
    }
}
