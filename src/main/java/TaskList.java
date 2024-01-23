public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        Format.printSepLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size()  + " tasks in the list.");
        Format.printSepLine();
    }

    public void markTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        Task t = this.tasks.get(listIndex);
        t.markDone();
        Format.printSepLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        Format.printSepLine();
    }

    public void unmarkTask(int index) throws CoDriverException{
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        Task t = this.tasks.get(listIndex);
        t.markNotDone();
        Format.printSepLine();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(t);
        Format.printSepLine();
    }

    public void listTasks() {
        Format.printSepLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks.get(i));
        }
        Format.printSepLine();
    }
}
