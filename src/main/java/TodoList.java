public class TodoList {
    private final Task[] taskList = new Task[100];
    private int length = 0;

    public void addTask(String name) {
        taskList[length] = new Task(name);
        length++;
    }

    @Override
    public String toString() {
        String txt = "";
        for (int i = 0; i < length; i++) {
            txt += (i > 0 ? Checkbot.INDENTATION : "") + (i+1) + ". " + taskList[i] + (i < length - 1 ? "\n" : "");
        }
        return txt;
    }
}
