package Commands;

import java.io.IOException;

public class InvalidCommand extends Command{
    String errorMessage;
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    @Override
    public String execute() {
        return this.errorMessage;
    }
}

