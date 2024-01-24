import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "                             -------,\n"
                    + "                            |   --  |\n"
                    + "                            |  |  | |\n"
                    + " ---- -   -  ---  ---  ---  |   --  |\n"
                    + "|     |   | |   |  |  |   | |  ,----'\n"
                    + "|     |---| |---   |   `-_  |  |     \n"
                    + "|     |   | | \\    |  |   | |  |     \n"
                    + " ---- -   - -  -  ---  ---   --      \n";
        String horizontalLine = "_____________________________________________________";
        String userInput = "";
        Task[] taskList = new Task[100];
        int listCount = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println(logo + horizontalLine
                + "\nOink Oink!\nI'm Chris P Bacon but you can call me ChrisP! Oink!\n"
                + "What can I do for you today? :D\n"
                + "~Type 'help' for more command info~\n" + horizontalLine);

        // Get user's first input.
        userInput = scan.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(horizontalLine);
            // If input = "help", list down the command words.
            if (userInput.equalsIgnoreCase("help")) {
                System.out.println("Oink! Here are the Command Words:\n'list' - displays the list of task\n"
                        + "'todo' - to add new task\n'deadline' - to add task with deadline\n"
                        + "'event' - to add an event\n'bye' - to exit the chatbot");

            } else if (userInput.equalsIgnoreCase("list")) {
                if (listCount == 0) {
                    System.out.println("Oink! There are no tasks! Yeehaww");

                } else {
                    System.out.println("Oink! Here are the tasks:");
                    for (int i = 1; i <= listCount; i++) {
                        System.out.println(i + ". " + taskList[i-1]);
                    }
                }
            } else if (userInput.startsWith("mark")) {
                int num = userInput.charAt(5) - '0';
                taskList[num-1].markDone();

            } else if (userInput.startsWith("unmark")) {
                int num = userInput.charAt(7) - '0';
                taskList[num-1].markUndone();

            } else {
                if (userInput.startsWith("todo")){
                    // Adds a new task to the list.
                    taskList[listCount] = new Todo(userInput.substring(5));

                } else if (userInput.startsWith("deadline")) {
                    //Adds a new deadline task to the list.
                    String name = userInput.substring(9, userInput.lastIndexOf("/by")-2);
                    String date = userInput.substring(userInput.lastIndexOf("/by")+4);
                    taskList[listCount] = new Deadline(name, date);

                } else if (userInput.startsWith("event")) {
                    // Adds a new event to the list.
                    String name = userInput.substring(6, userInput.lastIndexOf("/from")-1);
                    String from = userInput.substring(userInput.lastIndexOf("/from")+6,
                            userInput.lastIndexOf("/to")-1);
                    String to = userInput.substring(userInput.lastIndexOf("/to")+4);
                    taskList[listCount] = new Event(name, from, to);
                }
                listCount++;
                System.out.println("Oink! Nice I have added this task:\n"
                        + "   " + taskList[listCount-1] + "\nOink's task count: " + listCount);
            }
            System.out.println(horizontalLine);
            userInput = scan.nextLine();
        }
        // if user entered "bye", close scanner and exit chatbot.
        scan.close();
        System.out.println(horizontalLine + "Oink! Okie byee... See you soon! :)\n"
                + horizontalLine);
    }
}
