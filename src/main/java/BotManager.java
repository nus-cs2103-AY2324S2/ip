import java.util.ArrayList;

public class BotManager {
    private final String name;
    private ArrayList<Task> tasks;

    BotManager(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    void greeting() {
        System.out.println("    Hello! I'm " + name);
        System.out.println("    What can I do for you?\n");
    }

    void exit() {
        System.out.println("    Bye. Hope to see you again soon!\n");
    }

    void answer(String prompt) {
        String[] order = prompt.split(" ");
        if (order[0].equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println();
        } else if (order[0].equals("mark")) {
            Task task = tasks.get(Integer.parseInt(order[1]) - 1);
            task.mark();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + task);
        } else if (order[0].equals("unmark")) {
            Task task = tasks.get(Integer.parseInt(order[1]) - 1);
            task.unmark();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + task);
        } else {
            tasks.add(new Task(prompt));
            System.out.println("    added " + prompt + "\n");
        }
    }
}
