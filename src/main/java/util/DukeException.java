package util;

import java.lang.Exception;

public class DukeException {
    protected Exception exception;
    public DukeException(Exception exception) {
        this.exception = exception;
    }

    public String toString(){
        if (this.exception instanceof StringIndexOutOfBoundsException) {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
        else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
