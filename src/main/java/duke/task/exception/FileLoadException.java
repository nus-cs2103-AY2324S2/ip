package duke.task.exception;

public class FileLoadException extends Exception {

    public FileLoadException(String message) {
        super(message);
    }

    public static class InitializationError extends FileLoadException {
        public InitializationError() {
            super("I believe there's some issues regarding your file. " +
                    "(Error: you haven't initialized the file properly)");

            System.err.println("Maybe try adding something to the file, " +
                    "so that I can retrieve it next time!");
        }
    }
}

