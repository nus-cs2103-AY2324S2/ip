import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Jiayou {
    private static final String LINE = "____________________________________________________________";
    private ArrayList<Task> taskList = new ArrayList<Task>();
    private int counter = 0;

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
    }

    private void parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String content = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list":
                printList();
                break;
            case "mark":
                markTask(content);
                break;
            case "unmark":
                unmarkTask(content);
                break;
            default:
                this.counter += 1;
                Task newTask = new Task(this.counter, input);
                this.taskList.add(newTask);
                System.out.println("added: " + input);
        }
    }

    private void markTask(String content) {
        int taskId = Integer.parseInt(content);
        Task task = this.taskList.get(taskId - 1);
        task.setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    private void unmarkTask(String content) {
        int taskId = Integer.parseInt(content);
        Task task = taskList.get(taskId - 1); // taskList is 0-based indexing
        task.setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public static void main(String[] args) {
        Jiayou jiayou = new Jiayou();
        Scanner sc = new Scanner(System.in);
        System.out.println(Jiayou.LINE);
        System.out.println("Hello! I'm Jiayou.");
        System.out.println("What can I do for you?");
        System.out.println(Jiayou.LINE);

        while (true) {
            String input = sc.nextLine();
            System.out.println(Jiayou.LINE);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(Jiayou.LINE);
                break;
            } else if (input.equals("list")) {
                jiayou.printList();
            } else {
                jiayou.parse(input);
            }
            System.out.println(Jiayou.LINE);
        }
        sc.close();
    }
}
