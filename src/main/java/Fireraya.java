import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Fireraya {

    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void start() {
        System.out.println("--------------------------------");
        System.out.println("Hello, my name is Fireraya!");
        System.out.println("What can I do for you today sir?");
        System.out.println("--------------------------------");
    }

    public static void addTask(String a) {
        tasks.add(new Task(a));
        System.out.println("added: " + a);
    }

    public static void listTasks() {
        System.out.println("Here are your tasks, please don't dissapoint me");
    for (int i = 0; i < tasks.size(); i++) {
        Task current = tasks.get(i);
        int num = i + 1;
        System.out.println( num + "." + current.getCompletionIcon() + current.getTask());
        }
    }

    public static void listTask(int i) {
        Task current = tasks.get(i);
        int num = i + 1;
        System.out.println( num + "." + current.getCompletionIcon() + current.getTask());
    }

    public static void end() {
        System.out.println("Bye, hope to see you soon!");
    }

    public static void main(String[] args) {

        start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                end();
                break;
            }

            if (input.equals("list")) {
                listTasks();
                continue;
            }

            if (input.length() >= 4) {
                if (input.substring(0, 4).equals("mark")) {
                    int curr = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(curr).markAsDone();
                    System.out.println("About time you did something with your life:");
                    listTask(curr);
                    continue;
                }
            }

            if (input.length() >= 6) {
                if (input.substring(0, 6).equals("unmark")) {
                    int curr = Integer.parseInt(input.substring(7)) - 1;
                    tasks.get(curr).markAsUndone();
                    System.out.println("Get a grip. What are you doing man. Task Undone.");
                    listTask(curr);
                    continue;
                }
            }

            addTask(input);
        }
        scanner.close();
    }
}
