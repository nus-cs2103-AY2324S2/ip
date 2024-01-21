public class TaskList {
    private int numTasks = 0;
    private String[] tasks = new String[100];

    public void addTask(String task) {
        this.tasks[numTasks] = task;
        this.numTasks++;
    }

    public void displayTasks() {
        if (this.numTasks == 0) {
            System.out.println("No tasks in list yet!");
            return;
        }
        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println("  " + i + ". " + this.tasks[i - 1]);
        }
    }
}
