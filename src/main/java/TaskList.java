public class TaskList {
    private int numTasks = 0;
    private Task[] tasks = new Task[100];

    public void addTask(String description) {
        this.tasks[numTasks] = new Task(description);
        this.numTasks++;
    }

    public void markTask(String mark, int taskNum) {
        this.tasks[taskNum].setDone(mark);
    }

    public void displayTasks() {
        if (this.numTasks == 0) {
            System.out.println("No tasks in list yet!");
            return;
        }
        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println("  " + i + ". " + this.tasks[i - 1].getStatusIcon());
        }
    }
}
