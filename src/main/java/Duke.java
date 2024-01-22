import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String name = "Fluffy";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> userInputLog = new ArrayList<>();
        boolean isAcceptingInput = true;


        while (isAcceptingInput) {
            // Print out a prompt for user input
            System.out.print("$ ");
            String input = scanner.next();

            System.out.println("____________________________________________________________");
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isAcceptingInput = false;
                    break;
                case "list":
                    for (int i = 0; i < userInputLog.size(); i++) {
                        System.out.println(i + 1 + ". " + userInputLog.get(i));
                    }
                    break;
                case "mark":
                    int indexToMark = scanner.nextInt();
                    userInputLog.get(indexToMark - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(userInputLog.get(indexToMark - 1));
                    break;
                case "unmark":
                    int indexToUnmark = scanner.nextInt();
                    userInputLog.get(indexToUnmark - 1).markAsNotDone();
                    System.out.println("Nice! I've marked this task as undone:");
                    System.out.println(userInputLog.get(indexToUnmark - 1));
                    break;
                case "deadline":
                    String deadlineDescription = scanner.nextLine();
                    String[] deadlineDescriptionArray = deadlineDescription.split(" /by ");
                    Task deadlineTask = new Deadline(deadlineDescriptionArray[0], deadlineDescriptionArray[1]);
                    userInputLog.add(deadlineTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadlineTask);
                    System.out.println("Now you have " + userInputLog.size() + " tasks in the list.");
                    break;
                case "event":
                    String eventDescription = scanner.nextLine();
                    String[] eventDescriptionArray = eventDescription.split(" /from ");
                    String eventTitle = eventDescriptionArray[0];
                    eventDescriptionArray = eventDescriptionArray[1].split(" /to ");
                    Task eventTask = new Event(eventTitle, eventDescriptionArray[0], eventDescriptionArray[1]);
                    userInputLog.add(eventTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(eventTask);
                    System.out.println("Now you have " + userInputLog.size() + " tasks in the list.");
                    break;
                case "todo":
                    String todoDescription = scanner.nextLine();
                    Task todoTask = new Todo(todoDescription);
                    userInputLog.add(todoTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todoTask);
                    System.out.println("Now you have " + userInputLog.size() + " tasks in the list.");
                    break;
                default:
                    String description = input + scanner.nextLine();
                    Task task = new Task(description);
                    userInputLog.add(task);
                    System.out.println("added: " + description);
                    break;
            }
            System.out.println("____________________________________________________________");
        }

    }

}
