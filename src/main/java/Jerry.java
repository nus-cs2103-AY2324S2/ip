import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "Hello! I'm Jerry.\n" + "Anything I can do for you?";
        System.out.println(message);

        Task[] tasks =  new Task[100];
        int index = 0;

        while (true) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);

            if (input.trim().equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }

            else if (input.trim().equalsIgnoreCase("list")) {
                System.out.println("Here are the items in your list:");
                for (int x = 0; tasks[x] != null; x++){
                    System.out.println((x+1) + "." + tasks[x]);
                }
            }
            else if (parts[0].equalsIgnoreCase("mark") && parts.length > 1) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < index) {
                    tasks[taskIndex].markDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskIndex]);
                }
            } else if (parts[0].equalsIgnoreCase("unmark") && parts.length > 1) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < index) {
                    tasks[taskIndex].markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskIndex]);
                }
            } else {
                tasks[index] = new Task(input);
                index++;
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }
}

