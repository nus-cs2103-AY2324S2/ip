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
        String horizontalLine = "_____________________________________________________\n";
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        System.out.println(logo + horizontalLine
                + "Oink Oink!\nI'm Chris P Bacon but you can call me ChrisP! Oink!\n"
                + "What can I do for you today? :D\n"
                + "~Type 'help' for more command info~\n" + horizontalLine);

        // Get user's first input.
        userInput = scan.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(horizontalLine + ">> " + userInput + " <<");
//            // If input = "help", list down the command words.
//            if (userInput.equals("help")) {
//                System.out.println("Oink! Here are the Command Words:\n'list' - displays the list of task\n'task' - to add new task\n"
//                        + "'deadline' - to add task with deadline\n'event' - to add an event\n"
//                        + "'bye' - to exit the chatbot");
//            }
            System.out.println(horizontalLine);
            userInput = scan.nextLine();
        }
        // if user entered "bye", close scanner and exit chatbot.
        scan.close();
        System.out.println(horizontalLine + "Oink! Okie byee... See you soon! :)\n"
                + horizontalLine);
    }
}
