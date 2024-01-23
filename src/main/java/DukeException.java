public class DukeException extends Exception {
    public DukeException(String string) {
        super(string);
    }

    public static class IncorrectInputException extends DukeException {
        public IncorrectInputException(String string) {
            super(string);
        }
    }
}
