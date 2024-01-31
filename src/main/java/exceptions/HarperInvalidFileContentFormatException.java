package exceptions;

public class HarperInvalidFileContentFormatException extends HarperException {
    public HarperInvalidFileContentFormatException() {
        super("_________________________________________________________\n"
                + "Please make sure the content of the file harper.txt follows the expected format:\n"
                + "ToDo: \"T | [0 or 1] | [description]\"\n"
                + "Deadline: \"D | [0 or 1] | [description] | [by]\"\n"
                + "Event: \"E | [0 or 1] | [description] | [start] - [end]\"\n"
                + "_________________________________________________________");
    }

}
