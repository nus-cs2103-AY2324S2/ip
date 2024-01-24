public class TodoList {
    private final Task[] taskList = new Task[100];
    private int length = 0;

    public void addTask(Task task) {
        taskList[length] = task;
        length++;
    }

    public void markTask(int i) {
        taskList[i].mark();
    }

    public void unmarkTask(int i) {
        taskList[i].unmark();
    }

    public Task getTask(int i) {
        return taskList[i];
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (int i = 0; i < length; i++) {
            txt.append(i > 0 ? Checkbot.INDENTATION : "")
                    .append(i + 1)
                    .append(". ")
                    .append(taskList[i])
                    .append(i < length - 1 ? "\n" : "");
        }
        return txt.toString();
    }
}
