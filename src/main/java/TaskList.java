public class TaskList {
    private int numTasks = 0;
    private int remTasks = 0;
    private Task[] tasks = new Task[100];

    public void addTask(Task newTask) {

        this.tasks[numTasks] = newTask;
        this.numTasks++;
        this.remTasks++;
    }

    public void markTask(String mark, int taskNum) {
        boolean before = this.tasks[taskNum].getDone();
        this.tasks[taskNum].setDone(mark);
        boolean after = this.tasks[taskNum].getDone();
        if (before != after) {
            if (before) {
                remTasks++;
            } else {
                remTasks--;
            }
        }
    }

    public void displayTasks() {
        if (this.numTasks == 0) {
            System.out.println("No tasks in list yet!");
            return;
        }
        for (int i = 1; i < numTasks + 1; i++) {
            System.out.println("  " + i + ". " + this.tasks[i - 1].toString());
        }
    }

    public void report() {
        String out = String.format("  You have %s undone tasks in the list.", remTasks);
        System.out.println(out);
    }

    public String latest() {
        return this.tasks[numTasks - 1].toString();
    }
}
