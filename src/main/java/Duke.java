import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String userInput;

        // To read in user input
        Scanner sc = new Scanner(System.in);


        Task[] tasksList = new Task[100];
        int taskCount = 0;


        String welcomeMessage = "\t ____________________________________________________________\n" +
                "\t Hello! I'm JeromeGPT \n" +
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
                    System.out.println("\t " + humanReadableId + ".[" + currentTask.getStatusIcon() + "] " + currentTask.description);
                }

                System.out.println("\t ____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                // TODO: possibly need to handle a task that is called "mark..."
                int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                tasksList[idToMark].isDone = true;

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t  Nice! I've marked this task as done:");
                System.out.println("\t [" + tasksList[idToMark].getStatusIcon() + "] " + tasksList[idToMark].description);
                System.out.println("\t ____________________________________________________________");
            } else if (userInput.startsWith("unmark")) {
                // TODO: possibly need to handle a task that is called "mark..."
                int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                tasksList[idToMark].isDone = false;

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t  OK, I've marked this task as not done yet:");
                System.out.println("\t [" + tasksList[idToMark].getStatusIcon() + "] " + tasksList[idToMark].description);
                System.out.println("\t ____________________________________________________________");
            } else if (userInput.equals("bye")) {
                // Use this construct because we don't want to echo the bye message.
                break;
            } else {
                // Default allow user to add task
                tasksList[taskCount] = new Task(userInput);
                taskCount += 1;
                System.out.println(
                        "\t ____________________________________________________________\n" +
                                "\t added: " + userInput + "\n" +
                                "\t ____________________________________________________________"
                );


            }

        }

        String goodByeMessage = "\t ____________________________________________________________\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t ____________________________________________________________\n";

        System.out.println(goodByeMessage);
    }
}
