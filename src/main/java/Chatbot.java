import java.util.ArrayList;

public class Chatbot {
    private final String name;
    private ArrayList<Task> tasks;
    private static final String INDENT = "  ";
    private static final String LINESEP = "____________________________________________________________";

    public Chatbot(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    private void print(String message) {
        System.out.println(INDENT + LINESEP);
        System.out.println(INDENT + message);
        System.out.println(INDENT + LINESEP);
    }

    private void print(String[] messages) {
        System.out.println(INDENT + LINESEP);
        for (String message : messages) {
            System.out.println(INDENT + message);
        }
        System.out.println(INDENT+ LINESEP);
    }

    private String[] getTaskTitles() {
        String[] taskTitles = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            String title = String.format("%d. %s", i+1, this.tasks.get(i).getTitle());
            taskTitles[i] = title;
        }
        return taskTitles;
    }

    public void greet() {
        String[] messages = {"Hello! I'm " + this.name, "What can I do for you?"};
        print(messages);
    }

    public void addTask(String userInput) {
        this.tasks.add(new Task(userInput));
        print("Added: " + userInput);
    }

    public void displayTasks() {
        String[] titles = getTaskTitles();
        print(titles);
    }

    public void exit() {
        print(" Bye. Hope to see you again soon!");
    }
}