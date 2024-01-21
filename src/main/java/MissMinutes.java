import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MissMinutes {
    private final Scanner stdin;
    private final List<String> tasks;
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
        this.tasks = new ArrayList<String>(100);
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
    }

    public void run() {
        while (true) {
            String request = this.stdin.nextLine();
            if (request.equals("bye")) {
                break;
            } else if (request.equals("list")) {
                StringBuilder reply = new StringBuilder();
                for (int i = 0; i < this.tasks.size(); i++) {
                    reply.append((i + 1))
                            .append(". ")
                            .append(this.tasks.get(i))
                            .append("\n");
                }
                this.sendMsg(reply.toString());
            } else {
                this.tasks.add(request);
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