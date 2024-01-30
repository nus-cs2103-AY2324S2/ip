import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reacher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> memory = new ArrayList<>();
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "What do u want?\n" +
                "Pls say bye");
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input,"bye")) { //check for end request
                System.out.println("bye");
                break;
            }
            else if (Objects.equals(input, "list")) {// check for list request
                System.out.println("Tasks:");
                int c = 1;
                for(Task task:memory) {
                    System.out.println(c + task.toString());
                    c++;
                }
            }
            else if (Objects.equals(input, "edit")) {
                System.out.println("Which task number would u like to edit?");
                int num = scanner.nextInt();
                scanner.nextLine();
                Task task = memory.get(num - 1);
                System.out.println("Mark Done or Undone?");
                String change = scanner.nextLine();
                if (Objects.equals(change, "done")) {
                    task.markDone();
                    System.out.println("Task " + num + " marked done");
                }
                else  {
                    task.markNotDone();
                    System.out.println("Task " + num + " marked Undone");
                }
            }
            else {
                Task t = new Task(input);
                memory.add(t);
                System.out.println("I've added " + input);
            }
        }
        scanner.close();
    }
}
