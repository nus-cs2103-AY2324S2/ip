package Task;

public class InvalidTaskException extends Exception{

    @Override
    public String toString() {
        return "Invalid Task Name assigned.";
    }

    @Override
    public String getMessage() {
        return "Invalid Task Name assigned.";
    }


}
