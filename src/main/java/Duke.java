import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String userInput;

        // To read in user input
        Scanner sc = new Scanner(System.in);


        String[] tasksList = new String[100];
        int taskCount = 0;


        String welcomeMessage = "\t ____________________________________________________________\n" +
                "\t Hello! I'm JeromeGPT \n" +
                "\t What can I do for you?\n" +
                "\t ____________________________________________________________";

        System.out.println(welcomeMessage);

        while (true) {
            // Keep reading user input until they type "bye"
            userInput = sc.nextLine();

            switch (userInput) {
                case "list":
                    // Print out all the tasks.
                    System.out.println("\t ____________________________________________________________");

                    for (int i = 0; i < taskCount; i++) {
                        int humanReadableId = i + 1;
                        System.out.println("\t " + humanReadableId + ". " + tasksList[i]);
                    }

                    System.out.println("\t ____________________________________________________________");
                    break;


                default:
                    // Default allow user to add task
                    tasksList[taskCount] = userInput;
                    taskCount += 1;
                    System.out.println(
                            "\t ____________________________________________________________\n" +
                                    "\t added: " + userInput + "\n" +
                                    "\t ____________________________________________________________"
                    );
                    break;

                case "bye":
                    break;
            }

            if (userInput.equals("bye")) {
                // Use this construct because we don't want to echo the bye message.
                break;
            }
        }


        String goodByeMessage = "\t ____________________________________________________________\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t ____________________________________________________________\n";

        System.out.println(goodByeMessage);
    }
}
