package exceptions;

public class InvalidSlashParameterException extends LuluException {
    public InvalidSlashParameterException() {
        super("Could not decipher command after slash");
    }
}
