package exceptions;

public class InvalidInputException extends LuluException {
    public InvalidInputException() {
        super("Could not decipher input string");
    }
}
