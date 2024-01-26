public class Storage {
    private String[] tasks;
    private static int size = 0;

    public Storage() {
        this.tasks = new String[100];
    }

    public void storeTask(String task) {
        this.tasks[size] = task;
        size++;
    }

    public String getTask(int index) {
        return this.tasks[index];
    }

    public int getSize() {
        return size;
    }

}
