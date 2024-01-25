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
            int taskIndex;
            String command = parts[0].toLowerCase();

            switch (command) {
                case "bye":
                    System.out.println("Bye!");
                    scanner.close();
                    return;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int x = 0; tasks[x] != null; x++) {
                        System.out.println((x + 1) + "." + tasks[x]);
                    }
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < index) {
                        tasks[taskIndex].markDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskIndex]);
                    }
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < index) {
                        tasks[taskIndex].markNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskIndex]);
                    }
                    break;
                case "todo":
                    tasks[index] = new ToDo(parts[1]);
                    index++;
                    System.out.println("Got it. I've added this task:\n  " + tasks[index - 1]);
                    System.out.println("Now you have " + index + " tasks in the list.");
                    break;

                case "deadline":
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    tasks[index] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    index++;
                    System.out.println("Got it. I've added this task:\n  " + tasks[index - 1]);
                    System.out.println("Now you have " + index + " tasks in the list.");
                    break;

                case "event":
                    String[] eventParts = parts[1].split(" /from ", 2);
                    String[] fromTo = eventParts[1].split(" /to ", 2);
                    tasks[index] = new Event(eventParts[0], fromTo[0], fromTo[1]);
                    index++;
                    System.out.println("Got it. I've added this task:\n  " + tasks[index - 1]);
                    System.out.println("Now you have " + index + " tasks in the list.");
                    break;
            }
        }
    }
}

