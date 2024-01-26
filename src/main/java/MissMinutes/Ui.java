package MissMinutes;

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

    private static final Scanner stdin = new Scanner(System.in);

    public static void sayHello() {
        sendMsg("Hello! I'm \n" + MISS_MINUTES_LOGO
                + "What can I do for you");
    }

    public static void sayBye() {
        sendMsg("Bye. Hope to see you again soon!");
        stdin.close();
    }

    public static String getInput() {
        return stdin.nextLine();
    }

    public static void sendMsg(String body) {
        System.out.println(SEPARATOR);
        System.out.println(body);
        System.out.println(SEPARATOR);
    }

    public static void printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            sendMsg("There are no tasks in your list.");
            return;
        }
        StringBuilder reply = new StringBuilder("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            reply.append("\n")
                    .append((i + 1))
                    .append(". ")
                    .append(tasks.get(i));
        }
        sendMsg(reply.toString());
    }
}
