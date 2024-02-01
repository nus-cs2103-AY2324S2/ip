public class TaskList {
    private Task[] tasks;
    private int counter;

    public TaskList() {
        tasks = new Task[100]; // Or any initial size you prefer
        counter = 0;
    }

    public void addTask(Task task) {
        tasks[counter] = task;
        counter++;
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > counter) {
            throw new IllegalArgumentException("Invalid task number");
        }

        for (int i = taskNumber - 1; i < counter - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[counter - 1] = null;
        counter--;
    }

    public void markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > counter) {
            throw new IllegalArgumentException("Invalid task number");
        }

        tasks[taskNumber - 1].markAsDone( );
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getCounter() {
        return counter;
    }
}
