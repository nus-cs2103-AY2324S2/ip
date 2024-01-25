public class EmptyTaskNameException extends Exception {
    public EmptyTaskNameException() {
        super("Sorry! Please describe the task that you want me to add.\nButo is not a mind reader!");
    }
}
