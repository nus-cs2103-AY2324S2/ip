package louie;

/**
 * To be thrown when command option parsing fails, which occurs for incorrect user input.
 */
public class LouieOptionParsingException extends LouieException {
    public LouieOptionParsingException(String message) {
        super(message);
    }
}
