import java.util.Scanner;
import java.util.ArrayList;

public class Jiayou {
    private static final String LINE = "____________________________________________________________";
    private ArrayList<Task> taskList = new ArrayList<Task>();

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
            case "todo":
                ToDo newToDo = new ToDo(content);
                taskList.add(newToDo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newToDo);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                break;
            case "deadline":
                String[] deadlineParts = content.split(" /by ");
                Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                taskList.add(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newDeadline);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                break;
            case "event":
                String[] eventParts = content.split(" /from | /to ");
                Event newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                taskList.add(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newEvent);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                break;
            default:
                System.out.println("Command not recognized: " + input + "!");
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
            } else {
                jiayou.parse(input);
            }
            System.out.println(Jiayou.LINE);
        }
        sc.close();
    }
}
