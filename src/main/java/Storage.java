public class Storage {
    private final String[] todoList = new String[100];
    private int index;

    public Storage() {
        this.index = 0;
    }

    public void addTask(String task) {
        todoList[index] = task;
        index++;
    }

    public void listTasks() {
        System.out.println("Tasks:");

        for (int i = 1; i <= index; i++) {
            System.out.printf("%d. %s\n", i, todoList[i - 1]);
        }
    }
}
