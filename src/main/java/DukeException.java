public class DukeException extends UnsupportedOperationException{
    private String error;
    public DukeException(String error) {
        this.error = error;
    }
    @Override
    public String toString() {
        return error;
    }
}
