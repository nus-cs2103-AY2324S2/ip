package podz.exceptions;
public class PodzException extends Exception {
    public PodzException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}