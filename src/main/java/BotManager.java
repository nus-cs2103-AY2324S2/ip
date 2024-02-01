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

    private Task createTask(String prompt) {
        String[] order = prompt.split(" ", 2);
        String taskType = order[0];
        switch (taskType) {
        case "todo":
            return new Todo(order[1]);
        case "deadline":
            String[] deadline = order[1].split(" /by ");
            return new Deadline(deadline[0], deadline[1]);
        default:
            String[] eventFrom = order[1].split(" /from ");
            String endTime = order[1].split(" /to ")[1];
            return new Event(eventFrom[0], eventFrom[1].split(" /to ")[0], endTime);
        }
    }

    private void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println();
    }

    private void mark(int num) {
        Task task = tasks.get(num - 1);
        task.mark();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }

    private void unmark(int num) {
        Task task = tasks.get(num - 1);
        task.unmark();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }

    private void addTask(String prompt) {
        Task task = createTask(prompt);
        tasks.add(task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list\n");
        } else {
            System.out.println(String.format("    Now you have %d tasks in the list\n", tasks.size()));
        }
    }

    void answer(String prompt) {
        String[] order = prompt.split(" ");
        switch (order[0]) {
        case "list":
            list();
            break;
        case "mark":
            mark(Integer.parseInt(order[1]));
            break;
        case "unmark":
            unmark(Integer.parseInt(order[1]));
            break;
        default:
            addTask(prompt);
        }
    }
}
