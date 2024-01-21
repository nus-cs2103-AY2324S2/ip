public class InvalidTaskException extends Exception {
    public InvalidTaskException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "________________________________________________\n"
                + super.getMessage() +
                "\n________________________________________________";
    }
}
