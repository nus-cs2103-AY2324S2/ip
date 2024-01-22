import java.util.*;

public class William {
    public static List<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        /*
         * String logo = " ____        _        \n"
         * + "|  _ \\ _   _| | _____ \n"
         * + "| | | | | | | |/ / _ \\\n"
         * + "| |_| | |_| |   <  __/\n"
         * + "|____/ \\__,_|_|\\_\\___|\n";
         * System.out.println("Hello from\n" + logo);
         */

        Scanner sc = new Scanner(System.in);
        String logo = "William";
        // To store the task into a list

        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?\n");

        while (true) {
            String input = sc.nextLine();

            // Need a function to split the input to get whether it is a command or not
            // text[0] == command
            // text[1] == additional stuff
            String[] texts = retrieveTexts(input);
            // Need a function to see what kind of commands is it (since it is a String, we
            // can return a Command)
            Commands command = retrieveCommand(texts[0]);

            if (command == null) {
                Task perTask = new Task(input);
                tasks.add(perTask);
                System.out.println("added: " + input + "\n");
                continue;
            } else {
                switch (command) {
                    case list:
                        System.out.println("Here are the tasks in your list: ");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i).toString());
                        }
                        System.out.println("\n");
                        break;
                    case mark:
                        System.out.println("Nice! I've marked this task as done:");
                        markAndUnmark(texts[1]);
                        break;
                    case unmark:
                        System.out.println("OK, I've marked this task as not done yet:");
                        markAndUnmark(texts[1]);
                        break;
                    case bye:
                        System.out.println("Bye. Hope to see you again soon!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Unknown command, please try again!");
                }
            }
        }
    }

    public static String[] retrieveTexts(String input) {
        String[] resultOfSplit = new String[2];
        int indexOfFirstSpace = input.indexOf(" "); // Locate the first space
        if (indexOfFirstSpace == -1) {
            resultOfSplit[0] = input; // If there is no space, the whole input is the command
            resultOfSplit[1] = null; // There is no additional text
        } else {
            resultOfSplit[0] = input.substring(0, indexOfFirstSpace); // Extract the command
            resultOfSplit[1] = input.substring(indexOfFirstSpace + 1); // Extract the additional text 
        }
        return resultOfSplit;
    }

    public static Commands retrieveCommand(String input) {
        try {
            return Commands.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static void markAndUnmark(String input) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        tasks.get(actualId).changeIsDone();
        System.out.println(tasks.get(actualId).toString() + "\n");
    }
}
