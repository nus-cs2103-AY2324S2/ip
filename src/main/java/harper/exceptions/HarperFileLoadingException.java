package harper.exceptions;

/**
 * Exception that indicates an error is occurring during loading from the file.
 */
public class HarperFileLoadingException extends HarperException {
    //CHECKSTYLE.OFF: MissingJavadocMethod
    public HarperFileLoadingException() {
        super("Error occurs during loading!\n"
                + "Please make sure the content of the file harper.txt follows the expected format:\n"
                + "task.ToDo: \"T | [0 or 1] | [description]\"\n"
                + "task.Deadline: \"D | [0 or 1] | [description] | [by]\"\n"
                + "task.Event: \"E | [0 or 1] | [description] | [start] - [end]");
    }
}
