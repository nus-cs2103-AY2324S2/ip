package CinnamoRoll;
class Todos extends Task {

    Todos(String str) {
        super(str);
    }

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
