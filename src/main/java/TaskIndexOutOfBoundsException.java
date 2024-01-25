public class TaskIndexOutOfBoundsException extends InputException {

    TaskIndexOutOfBoundsException(int index) {
        super("Task not found: task number " + index);
    }
}
