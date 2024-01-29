import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {
    public static boolean exited = false;
    public static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    /**
     * This method generates the greeting message by the Chaterpillar chatbot.
     * It also prints the horizontal lines as dividers before and after the message.
     */
    public static void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm Chaterpillar");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * This method exits the program.
     * It sets the exited flag to true, and prints the exit message.
     */
    public static void exit() {
        exited = true;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * This method prints out the message given in the String argument.
     * @param s the message to be printed
     */
    public static void echo(String s) {
        System.out.println(s);
    }

    /**
     * This method prints out a horizontal line, typically used to segment
     * the start and end of a message by the chatbot.
     */
    public static void printHorizontalLine() {
        String line = "-".repeat(50);
        System.out.println(line);
    }

    /**
     * This method parses the input from the user, then calls the respective
     * methods to deal with the various actions of the chatbot.
     * @param reader used to read from System.in
     * @throws IOException if there are any input/output errors
     * @see IOException
     */
    public static void parseInput(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        String[] inputSplit = input.split(" ");
        printHorizontalLine();

        int num;
        Task currTask;
        String name;
        String[] temp;
        switch(inputSplit[0]) {
        case "list":
            echo("Here are the tasks in your list: ");
            int i = 1;
            for (Task eachTask : listOfTasks) {
                echo(i++ + ". " + eachTask);
            }
            break;
        case "mark":
            echo("Nice! I've marked this task as done:");
            num = Integer.parseInt(inputSplit[1]);
            currTask = listOfTasks.get(num-1);
            currTask.mark();
            echo(currTask.toString());
            break;
        case "unmark":
            echo("Ok, I've marked this task as not done yet:");
            num = Integer.parseInt(inputSplit[1]);
            currTask = listOfTasks.get(num-1);
            currTask.unmark();
            echo(currTask.toString());
            break;
        case "bye":
            // exits the program
            exit();
            break;
        case "todo":
            try {
                name = input.substring(5);
                currTask = new TodoTask(name);
                addTask(currTask);
            } catch (StringIndexOutOfBoundsException e) {
                echo("Sorry, the name of the task todo cannot be empty.\n" +
                        "The way to use the command is as such: todo taskname");
            }
            break;
        case "deadline":
            try {
                temp = input.split("/");
                name = temp[0].substring(9);
                String date = temp[1].substring(3);
                currTask = new DeadlineTask(name, date);
                addTask(currTask);
            } catch (IndexOutOfBoundsException e) {
                echo("Sorry, this command is in the wrong format.\n" +
                        "The way to use the command is: deadline taskname /by date_and_time");
            }
            break;
        case "event":
            try {
                temp = input.split("/");
                name = temp[0].substring(6);
                String date1 = temp[1].substring(5);
                String date2 = temp[2].substring(3);
                currTask = new EventTask(name, date1, date2);
                addTask(currTask);
            } catch (IndexOutOfBoundsException e) {
                echo("Sorry, this command is in the wrong format.\n" +
                        "The way to use the command is: event taskname /from date_and_time /to date_and_time");
            }
            break;
        case "delete":
            try {
                temp = input.split(" ");
                int index = Integer.parseInt(temp[1])-1;
                deleteTask(index);
            } catch (NumberFormatException e) {
                echo("Sorry, there is no number detected.\n" +
                        "The correct way to use the command is: delete number");
            } catch (IndexOutOfBoundsException e) {
                echo("Sorry, the format for this command is wrong.\n" +
                        "The correct way to use the command is: delete number");
            }
            break;
        case "help":
            String helpMessage = "Hi! Here are the list of commands I recognise: \n\n" +
                    "'list' - lists the tasks registered in the list\n" +
                    "'todo' - adds an item that has no due date\n" +
                    "'deadline' - adds an item with a due date\n" +
                    "'event' - adds an item that has a duration\n" +
                    "'mark' - marks the task as completed\n" +
                    "'unmark' - marks the task as not completed\n" +
                    "'help' - opens the list of commands available\n" +
                    "'bye' - exits the chatbot";
            echo(helpMessage);
            break;
        default:
            String unrecognised = "Oops, I have no idea what that means. " +
                    "Use 'help' for a list of commands I recognise.";
            echo(unrecognised);
            break;
        }
        printHorizontalLine();
    }

    /**
     * This method adds the specified task (in the argument) to the
     * static ArrayList to be tracked by the chatbot.
     * @param task object containing the specified task
     */
    public static void addTask(Task task) {
        listOfTasks.add(task);
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * This method deletes the task at the specified index,
     * then shifts all the tasks in the index behind it up by 1.
     * @param index integer specifying the number of the task to be deleted
     */
    public static void deleteTask(int index) {
        Task task = listOfTasks.remove(index);
        echo("Got it. I've removed this task:");
        echo(task.toString());
        echo("Now you have " + listOfTasks.size() + " tasks in the list.");

    }
    public static void main(String[] args) {
        greet();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader((System.in)));

        while (!exited) {
            try {
                parseInput(reader);
            } catch (IOException e) {
                System.out.println("IOException occurred.");
            }
        }
    }
}
