public class NoSuchCommandException extends Exception {

    public NoSuchCommandException(String command) {
        super("\"" + command + "\" is not within my capabilities");
    }

}
