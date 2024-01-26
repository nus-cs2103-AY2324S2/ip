public class IndexOutOfRange extends DukeException {
    public IndexOutOfRange(int idx, int size) {
        super(idx + " is not valid! It must be between 1 and the number of tasks in the list. There are " + size + " tasks.");
    }
}
