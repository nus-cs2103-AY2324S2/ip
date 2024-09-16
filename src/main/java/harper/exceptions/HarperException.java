package harper.exceptions;

//CHECKSTYLE.OFF: MissingJavadocType
public class HarperException extends RuntimeException {
    public HarperException(String message) {
        super("\n" + message + "\n");
    }
}
