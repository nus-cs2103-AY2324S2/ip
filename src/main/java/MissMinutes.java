import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MissMinutes {
    private final Scanner stdin;
    private final List<Task> tasks;
    private static final String separator = "-".repeat(60) + "\n";
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

    public void greet() {
        this.sendMsg("Hello! I'm \n" + logo
                    + "What can I do for you\n");
    }

    public void exit() {
        this.sendMsg("Bye. Hope to see you again soon!");
        this.stdin.close();
    }

    public String getSerializedTasks() {
        StringBuilder reply = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            reply.append((i + 1))
                    .append(". ")
                    .append(this.tasks.get(i))
                    .append("\n");
        }
        return reply.toString();
    }

    public void run() {
        while (true) {
            String request = this.stdin.nextLine();
            if (request.equals("bye")) {
                break;
            } else if (request.equals("list")) {
                this.sendMsg(this.getSerializedTasks());
            } else if (request.startsWith("mark") || request.startsWith("unmark")) {
                String[] split = request.split(" ");
                int idx = Integer.parseInt(split[1]) - 1;
                Task curr = this.tasks.get(idx);

                String reply;
                if (request.startsWith("mark")) {
                    curr.markAsDone();
                    reply = "Nice! I've marked this one as done: \n" + curr + "\n";
                } else {
                    curr.unmark();
                    reply = "OK, I've marked this task as not done yet: \n" + curr + "\n";
                }
                this.sendMsg(reply);
            } else {
                this.tasks.add(new Task(request));
                this.sendMsg("added: " + request + "\n");
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