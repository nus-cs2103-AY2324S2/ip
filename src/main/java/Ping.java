import java.util.ArrayList;

public class Ping {
    final String name = "Ping";
    private ArrayList<String> tasks = new ArrayList<>();


    public void addTask(String i) {
        tasks.add(i);
        System.out.println("added: " + i);
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("No ability added yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int idx = i + 1;
                System.out.printf("%d. " + tasks.get(i) + "%n", idx);
            }
        }
    }

    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
