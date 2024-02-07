public class DukeException extends Exception {
    private String errorMessage;
    public DukeException(){
        super();
    }

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
