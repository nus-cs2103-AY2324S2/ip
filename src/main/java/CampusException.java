public class CampusException extends Exception {
    public CampusException() {
        super("An error has occurred");
    }
    public CampusException(String s) {
        super(s);
    }
}
