public class Storage {
    private Task[] tasks;
    private static int size = 0;

    public Storage() {
        this.tasks = new Task[100];
    }

    public void storeTask(String task) {
        Task newTask = new Task(task);
        this.tasks[size] = newTask;
        size++;
    }

    public String getTask(int index) {
        return this.tasks[index].getDescription();
    }

    public int getSize() {
        return size;
    }

}
