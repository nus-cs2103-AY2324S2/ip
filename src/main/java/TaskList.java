public class TaskList {
    String[] tasks;
    int index;

    public TaskList(int size) {
        tasks = new String[size];
        index = 0;
    }

    public void addTask(String task) {
        tasks[index] = task;
        index++;
        printSepLine();
        System.out.println("added: " + task);
        printSepLine();
    }

    public void listTasks() {
        printSepLine();
        for (int i = 0; i < index; i++) {
            int listIndex = i + 1;
            System.out.println(listIndex + ". " + tasks[i]);
        }
        printSepLine();
    }

    private static void printSepLine() {
        System.out.println("------------------------------------------------");
    }
}
