public class InvalidCmdException extends Exception {
    public InvalidCmdException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "________________________________________________\n"
                + super.getMessage() +
                "\n________________________________________________";
    }
}
