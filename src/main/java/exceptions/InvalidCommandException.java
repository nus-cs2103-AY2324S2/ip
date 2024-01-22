package exceptions;

public class InvalidCommandException extends LuluException {
    public InvalidCommandException() {
        super("Could not decipher input string");
    }
}
