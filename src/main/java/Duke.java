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
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here " + (taskCounter == 1 ? "is the task" : "are the tasks") + " in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon());
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
            } else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                tasks[taskCounter] = new ToDo(description);
                taskCounter++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCounter - 1].getStatusIcon());
                System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
            } else if (userInput.startsWith("deadline")) {
                String[] description = userInput.substring(9).split(" /by ");
                tasks[taskCounter] = new Deadline(description[0], description[1]);
                taskCounter++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCounter - 1].getStatusIcon());
                System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
            } else if (userInput.startsWith("event")) {
                String[] description = userInput.substring(6).split(" /from | /to ");
                tasks[taskCounter] = new Event(description[0], description[1], description[2]);
                taskCounter++;
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCounter - 1].getStatusIcon());
                System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
            }
        }

        scanner.close();
    }
}