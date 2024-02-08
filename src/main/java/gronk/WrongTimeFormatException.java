package gronk;

public class WrongTimeFormatException extends Exception {
    public WrongTimeFormatException() {}

    public WrongTimeFormatException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "\tError, please input the time in yyyy-MM-dd HH:mm format.";
    }
}