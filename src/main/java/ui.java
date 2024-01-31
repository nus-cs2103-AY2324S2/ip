import java.util.Objects;
import java.util.Scanner;


public class ui {
    static String nameLogo =
            "__/\\\\\\________/\\\\\\_____/\\\\\\\\\\\\\\\\\\_____/\\\\\\____________________________/\\\\\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\____        \n" +
            " _\\/\\\\\\_______\\/\\\\\\___/\\\\\\\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\__________________________/\\\\\\///////\\\\\\____/\\\\\\/////\\\\\\____/\\\\\\/////\\\\\\____/\\\\\\/////\\\\\\__       \n" +
            "  _\\/\\\\\\_______\\/\\\\\\__/\\\\\\/////////\\\\\\_\\/\\\\\\_________________________/\\\\\\______\\//\\\\\\__/\\\\\\____\\//\\\\\\__/\\\\\\____\\//\\\\\\__/\\\\\\____\\//\\\\\\_      \n" +
            "   _\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\________________________\\//\\\\\\_____/\\\\\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_     \n" +
            "    _\\/\\\\\\/////////\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_________________________\\///\\\\\\\\\\\\\\\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_    \n" +
            "     _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\/////////\\\\\\_\\/\\\\\\___________________________\\////////\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_   \n" +
            "      _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_________________________/\\\\________/\\\\\\__\\//\\\\\\____/\\\\\\__\\//\\\\\\____/\\\\\\__\\//\\\\\\____/\\\\\\__  \n" +
            "       _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\____________\\//\\\\\\\\\\\\\\\\\\\\\\/____\\///\\\\\\\\\\\\\\/____\\///\\\\\\\\\\\\\\/____\\///\\\\\\\\\\\\\\/___ \n" +
            "        _\\///________\\///__\\///________\\///__\\///////////////______________\\///////////________\\///////________\\///////________\\///////_____\n";

    static String divider = "______________________________________________________________________________";

    public static void main(String[] args) {
        // greet user
        System.out.println(divider);
        System.out.println("Good afternoon, gentlemen. I am\n" + nameLogo);
        System.out.println("How may I assist you?");
        System.out.println(divider);

        Storage newTodoList = new Storage();

        //take in input
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String userInput = scanner.nextLine();
            String[] userInputArray = userInput.split(" ");

            System.out.println(divider);
            if (userInput.equalsIgnoreCase("bye")) {
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                newTodoList.listTasks();

            } else if (userInputArray[0].equalsIgnoreCase("mark")) {
                String taskString = newTodoList.markAsDone(Integer.parseInt(userInputArray[1]) - 1);
                System.out.println("Nice! I've marked this task as done:\n" + taskString);

            } else if (userInputArray[0].equalsIgnoreCase("unmark")) {
                String taskString = newTodoList.markAsUndone(Integer.parseInt(userInputArray[1]) - 1);
                System.out.println("OK, I've marked this task as not done yet:\n" + taskString);

            } else if (userInputArray[0].equalsIgnoreCase("todo") ||
                       userInputArray[0].equalsIgnoreCase("deadline") ||
                       userInputArray[0].equalsIgnoreCase("event")) {

                String taskString = newTodoList.addTask(userInput);

                // If there is no error, display the messages that task has been added
                if (!Objects.equals(taskString, "error")) {
                    System.out.println("Roger. I have added this task.");
                    System.out.println(taskString);
                    System.out.printf("Now you have %d tasks in the list.", newTodoList.getNumberOfTasks());
                }

            } else {
                // Unknown keyword error
                try {
                    throw new HALException("Unknown Keyword!");
                } catch (HALException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("\n" + divider);

        }

        System.out.println(divider);
        System.out.println("This mission is too important for me to allow you to jeopardize it. Goodbye.");
        System.out.println(divider);

    }
}
