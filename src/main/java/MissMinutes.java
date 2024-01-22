import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MissMinutes {
    private final Scanner stdin;
    private final List<Task> tasks;
    private static final String separator = "-".repeat(60);
    private static final String logo =
            " __  __ _           __  __ _             _                  \n" +
            " |  \\/  (_)         |  \\/  (_)           | |              \n" +
            " | \\  / |_ ___ ___  | \\  / |_ _ __  _   _| |_ ___  ___    \n" +
            " | |\\/| | / __/ __| | |\\/| | | '_ \\| | | | __/ _ \\/ __| \n" +
            " | |  | | \\__ \\__ \\ | |  | | | | | | |_| | ||  __/\\__ \\\n" +
            " |_|  |_|_|___/___/ |_|  |_|_|_| |_|\\__,_|\\__\\___||___/  \n" +
            "                                                            \n";

    public MissMinutes() {
        this.stdin = new Scanner(System.in);
        this.tasks = new ArrayList<Task>(100);
    }

    public void sendMsg(String body) {
        System.out.println(separator);
        System.out.println(body);
        System.out.println(separator);
    }

    public Task createTask(String input) {
        String[] split = input.split(" ", 2);

        if (split[0].equalsIgnoreCase("TODO")) {
            return Todo.fromStr(split[1]);
        }

        if (split[0].equalsIgnoreCase("DEADLINE")) {
            return Deadline.fromStr(split[1]);
        }

        if (split[0].equalsIgnoreCase("EVENT")) {
            return Event.fromStr(split[1]);
        }

        return null;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        String reply = "Got it. I've added this task: \n" +
                task + "\n" +
                "Now you have " + this.tasks.size() + " tasks in the list.";
        this.sendMsg(reply);
    }

    public void greet() {
        this.sendMsg("Hello! I'm \n" + logo
                    + "What can I do for you");
    }

    public void exit() {
        this.sendMsg("Bye. Hope to see you again soon!");
        this.stdin.close();
    }

    public void printTasks() {
        if (this.tasks.isEmpty()) {
            this.sendMsg("There are no tasks in your list.");
            return;
        }
        StringBuilder reply = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 0; i < this.tasks.size(); i++) {
            reply.append("\n")
                    .append((i + 1))
                    .append(". ")
                    .append(this.tasks.get(i));
        }
        this.sendMsg(reply.toString());
    }

    public void markTask(int idx) {
        Task curr = this.tasks.get(idx);
        curr.markAsDone();
        String reply = "Nice! I've marked this task as done: \n" + curr;
        this.sendMsg(reply);
    }

    public void unmarkTask(int idx) {
        Task curr = this.tasks.get(idx);
        curr.unmark();
        String reply = "OK, I've marked this task as not done yet: \n" + curr;
        this.sendMsg(reply);
    }

    public void run() {
        while (true) {
            String request = this.stdin.nextLine();
            if (request.equals("bye")) {
                break;
            } else if (request.equals("list")) {
                printTasks();
            } else if (request.startsWith("mark") || request.startsWith("unmark")) {
                String[] split = request.split(" ");
                int idx = Integer.parseInt(split[1]) - 1;
                if (request.startsWith("mark")) {
                    markTask(idx);
                } else {
                    unmarkTask(idx);
                }
            } else {
                Task task = this.createTask(request);
                this.addTask(task);
            }
        }
    }

    public static void main(String[] args) {
        MissMinutes mm = new MissMinutes();

        mm.greet();
        mm.run();
        mm.exit();
    }
}