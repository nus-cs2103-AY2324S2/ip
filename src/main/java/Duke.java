import java.util.Scanner;
public class Duke {

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


        Task[] tasks = new Task[100];
        int taskCounter = 0;

        String input;

        do {
            input = scanner.nextLine();
            printHorizontalLine();

            if (input.equalsIgnoreCase("bye")) {
                //printHorizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCounter > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
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
            } else if (input.startsWith("todo")) {
                // Add a ToDo task
                tasks[taskCounter] = new ToDo(input.substring(5).trim());
                taskCounter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCounter - 1]);
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                // Add a Deadline task
                String[] deadlineDetails = input.substring(9).split("/by");
                if (deadlineDetails.length == 2) {
                    tasks[taskCounter] = new Deadline(deadlineDetails[0].trim(), deadlineDetails[1].trim());
                    taskCounter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCounter - 1]);
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                } else {
                    System.out.println("Invalid deadline format. Please use: deadline <description> /by <date/time>");
                }
            } else if (input.startsWith("event")) {
                // Add an Event task
                String[] eventDetails = input.substring(6).split("/from|/to");
                if (eventDetails.length == 3) {
                    tasks[taskCounter] = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2].trim());
                    taskCounter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCounter - 1]);
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                } else {
                    System.out.println("Invalid event format. Please use: event <description> /from <start> /to <end>");
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

