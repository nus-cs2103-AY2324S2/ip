package CinnamoRoll;

/**
 * Creates a todo task with tasklist as an input
 */
class Todos extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param str The description of the Todo task.
     */
    Todos(String str) {
        super(str);
    }

    /**
     * Creates a new Todo task with the given description and marked status.
     *
     * @param str     The description of the Todo task.
     * @param marked  A boolean indicating whether the task is marked as completed.
     */
    Todos(String str, boolean marked) {
        super(str, marked);
    }

    String getStatusIcon() {
        return (this.getMarked() ? "[T][X]" : "[T][ ]");
    }

    /**
     * Returns a string that describes the number of tasks in the list,
     * description of the (deadline) task, task type and the marked status
     *
     * @param length denotes the length of the list
     */
    String addTask(int length) {
        return String.format("Got it. I've added this task:%n   "
                + "%s%nNow you have %d tasks in the list", super.toString(), length);
    }

    /**
     * Returns a string of the task so that it can be stored into the
     * database with a correct format to load data in future
     */
    public String storeInFile() {
        if (this.isMarked) {
            return "T | X | " + this.taskname;
        } else {
            return "T |   | " + this.taskname;
        }
    }
}
