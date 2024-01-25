public class InvalidCommandException extends DukeException {
    private String command;

    public InvalidCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s I don't know what '%s' means ;(\nPlease try again.\n",
                super.toString(),
                command);
    }
}
