package duke.commands;

import duke.exceptions.MeanDukeException;

public class InvalidCommandException extends MeanDukeException {

    private final String errorMessage;

    public InvalidCommandException(String usage) {
        super();
        this.errorMessage = usage;
    }

    public InvalidCommandException() {
        super();
        this.errorMessage = "What are you saying? Read the user manual, it was written for a reason";
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
