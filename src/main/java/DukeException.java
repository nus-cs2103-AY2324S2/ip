public class DukeException extends Exception {
    //nothing here at the moment
    public DukeException() {
        super("This is a duke exception of unknown cause.");
    }

    public DukeException(String s){
        super(s);
    }
}
