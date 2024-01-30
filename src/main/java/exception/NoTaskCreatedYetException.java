package exception;

public class NoTaskCreatedYetException extends Exception {
    public NoTaskCreatedYetException(){
        super("There appears to be some problem with your user input.");
    }
}
