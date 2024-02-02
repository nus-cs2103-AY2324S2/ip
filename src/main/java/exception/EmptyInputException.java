package exception;

public class EmptyInputException extends DukeException {
    private String message;

    public EmptyInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        String str;
        if (message.equals("find")) {
            str =  "    OOPS!!! No keyword was provided.\n"
                    + "    ------------------------------------------------\n";
        } else {
            str =  "    OOPS!!! The description of a " + this.message + " cannot be empty.\n"
                    + "    ------------------------------------------------\n";
        }

        return str;
    }

}

