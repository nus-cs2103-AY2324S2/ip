import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Georgie";
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
                }
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index].getStatusIcon() + " " + tasks[index].description);
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index].getStatusIcon() + " " + tasks[index].description);
            } else {
                tasks[taskCounter] = new Task(userInput);
                taskCounter++;
                System.out.println("added: " + userInput);
            }
        }

        scanner.close();
    }
}