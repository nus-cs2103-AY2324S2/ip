package nihao.exception;

public class IndexOutOfBoundsException extends Exception{
    public IndexOutOfBoundsException(int index, int size) {
        super("IndexOutOfBoundsException: " + "index " + index + " out of bounds for size " + size + ".");
    }
}
