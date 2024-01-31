package henry;
public class HenryException extends Exception {
    public HenryException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Whoops... " + this.getMessage();
    }
}