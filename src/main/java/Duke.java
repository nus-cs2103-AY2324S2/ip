import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Duke {
    public static void main(String[] args) {

        String userInput;

        // To read in user input
        Scanner sc = new Scanner(System.in);


        Task[] tasksList = new Task[100];
        int taskCount = 0;


        String welcomeMessage = "\t ____________________________________________________________\n" +
                "\t Hello! I'm JeromeGPT\n" +
                "\t What can I do for you?\n" +
                "\t ____________________________________________________________";

        System.out.println(welcomeMessage);

        while (true) {
            // Keep reading user input until they type "bye"
            userInput = sc.nextLine();

            if (userInput.equals("list")) {
                // Print out all the tasks.
                System.out.println("\t ____________________________________________________________");

                for (int i = 0; i < taskCount; i++) {
                    int humanReadableId = i + 1;
                    Task currentTask = tasksList[i];
                    System.out.println("\t " + humanReadableId + ". " + currentTask.toString());
                }

                System.out.println("\t ____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                // TODO: possibly need to handle a task that is called "mark..."
                int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                tasksList[idToMark].setDone(true);

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t " + tasksList[idToMark].toString());
                System.out.println("\t ____________________________________________________________");
            } else if (userInput.startsWith("unmark")) {
                // TODO: possibly need to handle a task that is called "mark..."
                int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                tasksList[idToMark].setDone(false);

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t " + tasksList[idToMark].toString());
                System.out.println("\t ____________________________________________________________");
            } else if (userInput.equals("bye")) {
                // Use this construct because we don't want to echo the bye message.
                break;
            } else if (userInput.startsWith("todo")) {
                // We further do another Regex search

                // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
                Pattern pattern = Pattern.compile("^todo (.+)$");
                Matcher matcher = pattern.matcher(userInput);

                if (matcher.find()) {
                    String task = matcher.group(1);

                    tasksList[taskCount] = new Todo(task);
                    taskCount += 1;
                    System.out.println(
                            "\t ____________________________________________________________\n" +
                                    "\t Got it. I've added this task:\n" +
                                    "\t added: " + tasksList[taskCount - 1].toString() + "\n" +
                                    "\t Now you have " + taskCount + " tasks in the list\n" +
                                    "\t ____________________________________________________________"
                    );

                } else {
                    // TODO: Handle failure in terms of missing variable.
                    System.out.println("Please enter the correct input");
                }

            } else if (userInput.startsWith("deadline")) {
                // We further do another Regex search

                // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
                Pattern pattern = Pattern.compile("^deadline (.+) \\/by (.+)$");
                Matcher matcher = pattern.matcher(userInput);

                if (matcher.find()) {
                    String task = matcher.group(1);
                    String deadline = matcher.group(2);

                    tasksList[taskCount] = new Deadline(task, deadline);
                    taskCount += 1;
                    System.out.println(
                            "\t ____________________________________________________________\n" +
                                    "\t Got it. I've added this task:\n" +
                                    "\t added: " + tasksList[taskCount - 1].toString() + "\n" +
                                    "\t Now you have " + taskCount + " tasks in the list\n" +
                                    "\t ____________________________________________________________"
                    );

                } else {
                    // TODO: Handle failure in terms of missing variable.
                    System.out.println("Please enter the correct input");
                }

            } else if (userInput.startsWith("event")) {
                // We further do another Regex search

                // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
                Pattern pattern = Pattern.compile("^event (.+) \\/from (.+) \\/to (.+)$");
                Matcher matcher = pattern.matcher(userInput);

                if (matcher.find()) {
                    String task = matcher.group(1);
                    String from = matcher.group(2);
                    String to = matcher.group(3);

                    tasksList[taskCount] = new Event(task, from, to);
                    taskCount += 1;
                    System.out.println(
                            "\t ____________________________________________________________\n" +
                                    "\t Got it. I've added this task:\n" +
                                    "\t added: " + tasksList[taskCount - 1].toString() + "\n" +
                                    "\t Now you have " + taskCount + " tasks in the list\n" +
                                    "\t ____________________________________________________________"
                    );

                } else {
                    // TODO: Handle failure in terms of missing variable.
                    System.out.println("Please enter the correct input");
                }


            } else {
                System.out.println("\t ____________________________________________________________");
                System.out.println("\t Please enter the correct commands.");
                System.out.println("\t ____________________________________________________________");

            }


        }

        String goodByeMessage = "\t ____________________________________________________________\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t ____________________________________________________________\n";

        System.out.println(goodByeMessage);
    }
}
