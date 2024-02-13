package command;

public class CommandResponse {
    private final String message;
    private final boolean isError;

    public CommandResponse(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }

    // Static factory methods for convenience
    public static CommandResponse error(String message) {
        return new CommandResponse(message, true);
    }

    public static CommandResponse success(String message) {
        return new CommandResponse(message, false);
    }
}
