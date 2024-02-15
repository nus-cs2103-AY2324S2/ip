package duke.exceptions;

public class MissingToDoNameException extends RuntimeException {

    public MissingToDoNameException(String errorMessage) {
        super(errorMessage);
    }
}
