public class AlreadyDoneException extends DukeException {
        public AlreadyDoneException(String message) {
            super(message);
        }

        public static void validate(Task task) throws AlreadyDoneException {
            if (task.isDone) {
                throw new AlreadyDoneException("Task Already Marked As Done");
            }
        }
    }

