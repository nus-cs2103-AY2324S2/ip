public class InvalidArgumentException extends DukeException {
    private String command;

    public InvalidArgumentException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s The '%s' command has invalid argument(s)! ;(\nPlease fill it in.\n",
                super.toString(),
                command);
    }
}
