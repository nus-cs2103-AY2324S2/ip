public class TaskList {
    private final Task[] tasks;
    int index;

    public TaskList(int size) {
        this.tasks = new Task[size];
        this.index = 0;
    }

    public void addTask(Task task) {
        this.tasks[index] = task;
        index++;
        printSepLine();
        System.out.println("added: " + task);
        printSepLine();
    }

    public void markTask(int index) {
        int listIndex = index - 1;
        Task t = this.tasks[listIndex];
        t.markDone();
        printSepLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toStringInList());
        printSepLine();
    }

    public void unmarkTask(int index) {
        int listIndex = index - 1;
        Task t = this.tasks[listIndex];
        t.markNotDone();
        printSepLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(t.toStringInList());
        printSepLine();
    }

    public void listTasks() {
        printSepLine();
        for (int i = 0; i < index; i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks[i].toStringInList());
        }
        printSepLine();
    }

    private static void printSepLine() {
        System.out.println("------------------------------------------------");
    }
}
