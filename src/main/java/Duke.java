import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Georgie";
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>();
        int taskCounter = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    if (tasks.isEmpty()) {
                        throw new DukeException("There are no tasks in your list.");
                    }

                    System.out.println("Here " + (taskCounter == 1 ? "is the task" : "are the tasks") + " in your list:");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).getStatusIcon());
                    }
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index).getStatusIcon());
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(index).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index).getStatusIcon());
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("Umm... The todo command is incomplete!");
                    }
                    String description = userInput.substring(5);

                    tasks.add(new ToDo(description));
                    taskCounter++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(taskCounter - 1).getStatusIcon());
                    System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() <= 9) {
                        throw new DukeException("Oops! The deadline command is incomplete.");
                    }

                    String[] description = userInput.substring(9).split(" /by ");

                    if (description.length < 2) {
                        throw new DukeException("Oops! Both description and deadline are required for a deadline task.");
                    }

                    tasks.add(taskCounter, new Deadline(description[0], description[1]));
                    taskCounter++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(taskCounter - 1).getStatusIcon());
                    System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
                } else if (userInput.startsWith("event")) {
                    if (userInput.length() <= 6) {
                        throw new DukeException("Uh oh! The event command is incomplete.");
                    }

                    String[] description = userInput.substring(6).split(" /from | /to ");

                    if (description.length < 3) {
                        throw new DukeException("Uh oh! The event command requires both start and end details.");
                    }

                    tasks.add(taskCounter, new Event(description[0], description[1], description[2]));
                    taskCounter++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(taskCounter - 1).getStatusIcon());
                    System.out.println("Now you have " + taskCounter + " task" + (taskCounter == 1 ? "" : "s") + " in the list.");
                } else if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;

                    if (index < 0 || index >= tasks.size()) {
                        throw new DukeException("Task not found. Please provide a valid task number.");
                    }

                    Task removedTask = tasks.remove(index);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask.getStatusIcon());
                    System.out.println("Now you have " + tasks.size() + " " + (tasks.size() <= 1 ? "task" : "tasks") + " in the list.");
                } else {
                    throw new DukeException("I don't understand what you mean :c");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}