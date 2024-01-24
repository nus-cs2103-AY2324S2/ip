import java.util.ArrayList;

public class Ping {
    final String name = "Ping";


    protected ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task i) {
        tasks.add(i);
        System.out.println("added: " + i.description);
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No ability added yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                int idx = i + 1;
                //System.out.printf("%d. " + tasks.get(i).description + "%n", idx);
                if (tasks.get(i).isDone) {
                    System.out.printf("%d.[X] " + tasks.get(i).description + "%n", idx);
                } else {
                    System.out.printf("%d.[ ] " + tasks.get(i).description + "%n", idx);
                }
            }
        }
    }

    public void markJobs(Task i) {
        System.out.println("Nice! I've marked this task as done:");
        i.isDone = true;
        System.out.printf("[X] " + i.description + "%n");
    }

    public void unMarkJobs(Task i) {
        System.out.println("OK, I've marked this task as not done yet:");
        i.isDone = false;
        System.out.printf("[ ] " + i.description + "%n");
    }
    public void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
