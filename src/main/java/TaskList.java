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
        Format.printSepLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + index + " tasks in the list.");
        Format.printSepLine();
    }

    public void markTask(int index) {
        int listIndex = index - 1;
        Task t = this.tasks[listIndex];
        t.markDone();
        Format.printSepLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        Format.printSepLine();
    }

    public void unmarkTask(int index) {
        int listIndex = index - 1;
        Task t = this.tasks[listIndex];
        t.markNotDone();
        Format.printSepLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(t);
        Format.printSepLine();
    }

    public void listTasks() {
        Format.printSepLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks[i]);
        }
        Format.printSepLine();
    }
}
