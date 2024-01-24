public abstract class MissingArgumentException extends CheckbotException {
    public MissingArgumentException(String arg) {
        super("Sorry, the argument \"" + arg + "\" is missing. Please provide that by typing \"/" + arg + "\".");
    }
}
