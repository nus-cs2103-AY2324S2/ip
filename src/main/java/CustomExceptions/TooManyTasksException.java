package CustomExceptions;

public class TooManyTasksException extends Exception{
    public TooManyTasksException(){
        super("There are too many tasks.. So much that the array cannot hold anymore.");
    }
}
