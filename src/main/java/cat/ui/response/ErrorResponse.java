package cat.ui.response;

public class ErrorResponse extends Response {
    public ErrorResponse(Exception e) {
        super("The cat tilts its head and hands you an error report", e.toString());
        setOutputColor(Colors.BRIGHT_RED);
    }
}
