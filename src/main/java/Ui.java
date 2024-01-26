import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "-".repeat(60);

    private static final String MISS_MINUTES_LOGO =
            " __  __ _           __  __ _             _                  \n" +
            " |  \\/  (_)         |  \\/  (_)           | |              \n" +
            " | \\  / |_ ___ ___  | \\  / |_ _ __  _   _| |_ ___  ___    \n" +
            " | |\\/| | / __/ __| | |\\/| | | '_ \\| | | | __/ _ \\/ __| \n" +
            " | |  | | \\__ \\__ \\ | |  | | | | | | |_| | ||  __/\\__ \\\n" +
            " |_|  |_|_|___/___/ |_|  |_|_|_| |_|\\__,_|\\__\\___||___/  \n" +
            "                                                            \n";

    private final Scanner stdin;

    public Ui() {
        this.stdin = new Scanner(System.in);
    }

    public void greet() {
        this.sendMsg("Hello! I'm \n" + MISS_MINUTES_LOGO
                + "What can I do for you");
    }

    public void exit() {
        this.sendMsg("Bye. Hope to see you again soon!");
        this.stdin.close();
    }

    public String getInput() {
        return this.stdin.nextLine();
    }

    public void sendMsg(String body) {
        System.out.println(SEPARATOR);
        System.out.println(body);
        System.out.println(SEPARATOR);
    }

    public void printTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            this.sendMsg("There are no tasks in your list.");
            return;
        }
        StringBuilder reply = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            reply.append("\n")
                    .append((i + 1))
                    .append(". ")
                    .append(tasks.get(i));
        }
        this.sendMsg(reply.toString());
    }
}
