package youdon;

public class YoudonException extends Throwable {
    public static class EmptyDescException extends Exception {
        public EmptyDescException(String errorMessage) {
            super(errorMessage);
        }

        public static void detectMissingDesc(String input) throws EmptyDescException {
            if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
                throw new YoudonException.EmptyDescException("Hey! The task description is empty!");
            }
        }
    }

    public static class InvalidCommandException extends Exception {
        public InvalidCommandException(String errorMessage) {
            super(errorMessage);
        }

        public static void detectInvalidCommand(String input) throws InvalidCommandException {
            for (ValidCommands command: ValidCommands.values()) {
                if (input.contains(command.getCommand())) {
                    return;
                }
            }
            throw new YoudonException.InvalidCommandException("Sorry, I do not recognise that command.");
        }
    }
}
