import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    /**
     * Print out a line on the screen
     */
    public static void drawLine() {
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Add input task into storage
     *
     * @param type Type of the task.
     * @param task The task to be done.
     * @param storage The storage to store the task
     */
    public static void addTask(String type, String task, List<Task> storage) {
        Task newTask;

        if (type.equals("todo")) {
            newTask = new ToDo(task);
        } else if (type.equals("deadline")) {
            String[] taskArr = task.split("/");
            String by = taskArr[1].split(" ")[1];
            String description = taskArr[0].substring(0, taskArr[0].length() - 1);
            newTask = new Deadline(description, by);
        } else if (type.equals("event")) {
            String[] taskArr = task.split("/");
            String[] fromArr = taskArr[1].split(" ");
            String by = fromArr[1] + " " + fromArr[2];
            String to = taskArr[2].split(" ")[1];
            String description = taskArr[0].substring(0, taskArr[0].length() - 1);
            newTask = new Event(description, by, to);
        } else {
            newTask = new ToDo(task);
        }

        storage.add(newTask);
        drawLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTask);
        String temp = storage.size() > 1 ? " tasks" : " task";
        System.out.println("Now you have " + storage.size() + temp + " in the list.");
        drawLine();
    }

    /**
     * List out all the task from the storage
     * @param storage where the task are kept in
     */
    public static void listTask(List<Task> storage) {
        drawLine();
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". " + storage.get(i));
        }
        drawLine();
    }

    /**
     * To mark a task as done
     * @param storage where the task is kept in
     * @param index index of the task to be marked as done
     */
    public static void markDone(List<Task> storage, int index) {
        Task curr = storage.get(index - 1);
        drawLine();
        curr.markDone();
        drawLine();
    }

    /**
     * To unmark a task
     * @param storage where the task is kept in
     * @param index index of the task to be unmarked
     */
    public static void markUndone(List<Task> storage, int index) {
        Task curr = storage.get(index - 1);
        drawLine();
        curr.markUndone();
        drawLine();
    }

    /**
     * Provide commands to communicate with chatbot
     */
    public static void startChat() {

        drawLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        drawLine();
        Scanner scanner = new Scanner(System.in);
        List<Task> storage = new ArrayList<>();

        while (true) {
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ");

            if (command.equals("bye")) {
                drawLine();
                System.out.println("Bye. Hope to see you again soon!");
                drawLine();
                break;
            } else if (command.equals("list")) {
                listTask(storage);
            } else if (commandArr[0].equals("mark")) {
                markDone(storage, Integer.valueOf(commandArr[1]));
            } else if (commandArr[0].equals("unmark")) {
                markUndone(storage, Integer.valueOf(commandArr[1]));
            } else {
                addTask(commandArr[0], command.replace(commandArr[0] + " ", ""), storage);
            }
        }
    }


    public static void main(String[] args) {
        startChat();
    }
}
