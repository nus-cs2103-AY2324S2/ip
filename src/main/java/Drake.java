import java.util.ArrayList;
import java.util.Scanner;

public class Drake {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up everyone. I'm Drake.");
        System.out.println(" How can I help?");
        System.out.println("____________________________________________________________");
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" See you later, alligator! ");
                System.out.println("____________________________________________________________");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).getStatusIcon() + tasks.get(i).description);
                }
                System.out.println("____________________________________________________________");
            }
            else if (input.split(" ")[0].equals("mark")) {
                Task markedTask = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                markedTask.isDone = true;

                System.out.println("____________________________________________________________");
                System.out.println("Cool. I've marked this task as done:");
                System.out.println(markedTask.getStatusIcon() + markedTask.description);
                System.out.println("____________________________________________________________");
            }

            else if (input.split(" ")[0].equals("unmark")) {
                Task markedTask = tasks.get(Integer.parseInt(input.split(" ")[1]) - 1);
                markedTask.isDone = false;

                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(markedTask.getStatusIcon() + markedTask.description);
                System.out.println("____________________________________________________________");
            }

            else {
                tasks.add(new Task(input));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
