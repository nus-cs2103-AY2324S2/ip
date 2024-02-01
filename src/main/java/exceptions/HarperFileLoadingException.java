package exceptions;

public class HarperFileLoadingException extends HarperException {
    public HarperFileLoadingException() {
        super("Error occurs during loading!\n"
                + "Please make sure the content of the file harper.txt follows the expected format:\n"
                + "ToDo: \"T | [0 or 1] | [description]\"\n"
                + "Deadline: \"D | [0 or 1] | [description] | [by]\"\n"
                + "Event: \"E | [0 or 1] | [description] | [start] - [end]");
    }
}
