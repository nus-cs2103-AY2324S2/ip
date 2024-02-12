package rick;

public class RickException extends Exception {
    String message;
    public RickException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
