public class TaskNotFoundException extends Throwable {
//    private int taskNum;
    private List taskList;

    public TaskNotFoundException(List taskList) {
//        this.taskNum = taskNum;
        this.taskList = taskList;
    }
    @Override
    public String toString() {
        if (this.taskList.getListSize() == 0) {
            return "\nTask not found: list is empty. Please add a task first.";
        }
        return "\nTask not found: Task number should be between 1 and " + this.taskList.getListSize()
                + ".";
    }
}
