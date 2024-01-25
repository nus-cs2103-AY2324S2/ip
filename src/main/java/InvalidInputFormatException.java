public class InvalidInputFormatException extends Exception{
    public InvalidInputFormatException(String task) {
        super(String.format("Invalid format for %s task!", task));
    }
}
