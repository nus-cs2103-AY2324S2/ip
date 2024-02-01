import java.util.Scanner;
import java.util.ArrayList;
import tasks.Task;

public class Stille {
    public static void main(String[] args) {
        String openingMessage = "Hello! I'm Stille\n" + "What can I do for you?\n";
        String closingMessage = "\nBye. Hope to see you again soon!";
        String input;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        boolean exit = false;

        System.out.println(openingMessage);

        while (!exit) {
            System.out.println("\n");
            input = sc.nextLine();
            String[] command = input.split(" ");
            switch (command[0]) {
            case "bye":
                exit = true;
                break;
            case "":
                break;
            case "list":
                System.out.println("\nHere are the tasks in your list:");
                for (int i=0; i < list.size(); i++) {
                    Task t = list.get(i);
                    System.out.println((i + 1) + ".[" + t.getStatusIcon() + "] " + t.getDescription());
                }
                break;
            case "mark" :
                int index = Integer.valueOf(command[1]);
                Task t = list.get(index - 1);
                t.markDone();
                System.out.println("\nNice! I've marked this task as done:");
                System.out.println("  [" + t.getStatusIcon() + "] " + t.getDescription());
                break;
            case "unmark" :
                int index2 = Integer.valueOf(command[1]);
                Task task = list.get(index2 - 1);
                task.markNotDone();
                System.out.println("\nOK, I've marked this task as not done yet:");
                System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
                break;
            default:
                list.add(new Task(input));
                System.out.println("\nadded: " + input);
                break;
            }
        }

        System.out.println(closingMessage);
    }
}
