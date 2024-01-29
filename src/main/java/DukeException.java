public class DukeException {
    public static class TODONoTaskException extends Exception {
        public TODONoTaskException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DEADLINEException extends Exception {
        public DEADLINEException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class UnsupportedTaskException extends Exception {
        public UnsupportedTaskException(String errorMessage) {
            super(errorMessage);
        }
    }
}
