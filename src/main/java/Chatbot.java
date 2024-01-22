public class Chatbot {
    private final String name;
    private TaskList taskList;
    private static final String LINESEP = "____________________________________________________________";

    public Chatbot(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    private void print(String message) {
        System.out.println(LINESEP);
        System.out.println(message);
        System.out.println(LINESEP);
    }

    private void print(String[] messages) {
        System.out.println(LINESEP);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINESEP);
    }

    public void greet() {
        String[] messages = {"Hello! I'm " + this.name, "What can I do for you?"};
        print(messages);
    }

    public void addTask(Task task) {
        this.taskList.addTask(task);
        String[] messages = {"I have added the task:", task.toString(), 
                            "You have " + this.taskList.getNumberOfTasks() +" tasks currently."};
        print(messages);
    }

    public void markTask(int taskIdx) {
        Task task = this.taskList.getTask(taskIdx);
        task.setMarked();
        String[] messages = {"Marked task as done:", task.toString()};
        print(messages);
    }

    public void unmarkTask(int taskIdx) {
        Task task = this.taskList.getTask(taskIdx);
        task.setUnmarked();
        String[] messages = {"Unmarked task as not done:", task.toString()};
        print(messages);
    }

    public void displayTasks() {
       print(this.taskList.toString());
    }

    public void exit() {
        print("Goodbye!");
    }
}