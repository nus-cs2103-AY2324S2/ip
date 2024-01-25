public class MissingArgumentException extends DukeException {
    private String command;

    public MissingArgumentException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s The '%s' command is missing arguments! ;(\nPlease fill it in.\n",
                super.toString(),
                command);
    }
}
