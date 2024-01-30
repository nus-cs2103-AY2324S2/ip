public class InvalidTaskFormatException extends CheckbotException {
    public InvalidTaskFormatException() {
        super("File does not follow the required format");
    }
}
