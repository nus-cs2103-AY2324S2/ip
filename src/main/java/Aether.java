import java.util.Scanner;
public class Aether {

    private static void printHorizontalLine() {
        System.out.println(" _____________________________");
    }

    public static void main(String[] args) {
        String chatbotName = "Aether";
        Scanner scanner = new Scanner(System.in);

        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + chatbotName + "!");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

//        String[] tasks = new String[100];
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        String input;

        do {
            input = scanner.nextLine();
            printHorizontalLine();

            if (input.equalsIgnoreCase("bye")) {
                printHorizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCounter > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " +
                        tasks[i].getDescription());
                    }
                } else {
                    System.out.println("No tasks added yet.");
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCounter) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[taskIndex].getStatusIcon() + " " +
                            tasks[taskIndex].getDescription());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (input.startsWith("unmark")) {
                // Mark a task as not done
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCounter) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[taskIndex].getStatusIcon() + " " + tasks[taskIndex].getDescription());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else {
                tasks[taskCounter] = new Task(input);
                taskCounter++;
                System.out.println("added: " + input);

            }
            printHorizontalLine();


        } while (!input.equalsIgnoreCase("bye"));
        scanner.close();
    }
}

