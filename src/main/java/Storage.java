public class Storage {
    private final Task[] todoList = new Task[100];
    private int index;

    public Storage() {
        this.index = 0;
    }

    public void addTask(String taskDesc) {
        Task t = new Task(taskDesc);
        todoList[index] = t;
        index++;
    }

    public String markAsDone(int taskIndex) {
        Task t = todoList[taskIndex];
        t.markAsDone();
        String taskString = String.format("[%s] %s\n", t.getStatusIcon(), t.getDescription());
        return taskString;
    }

    public String markAsUndone(int taskIndex) {
        Task t = todoList[taskIndex];
        t.markAsUndone();
        String taskString = String.format("[%s] %s\n", t.getStatusIcon(), t.getDescription());
        return taskString;
    }

    public void listTasks() {
        System.out.println("Tasks:");

        for (int i = 1; i <= index; i++) {
            Task t = todoList[i - 1];
            System.out.printf("%d.[%s] %s\n", i, t.getStatusIcon(), t.getDescription());
        }
    }
}
