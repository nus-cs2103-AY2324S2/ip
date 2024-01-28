public class InvalidTaskIndexException extends DukeException {
    private int currentSize;
    public InvalidTaskIndexException(int size) {
        super();
        this.currentSize = size;
    }

    @Override
    public String toString() {
        return String.format(" Stop trolling and pick a number\n within %d\n", currentSize);
    }

}
