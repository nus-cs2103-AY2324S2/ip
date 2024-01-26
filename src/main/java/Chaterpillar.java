import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Chaterpillar {
    public static boolean exited = false;
    public static ArrayList<Task> listoftasks = new ArrayList<Task>();
    public static void greet() {
        print_horizontal_line();
        System.out.println("Hello! I'm Chaterpillar");
        System.out.println("What can I do for you?");
        print_horizontal_line();
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void echo(String s) {
        System.out.println(s);
    }
    public static void print_horizontal_line() {
        String line = "-".repeat(50);
        System.out.println(line);
    }
    public static void parse_input(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        String[] input_sp = input.split(" ");
        print_horizontal_line();

        int num;
        Task curr_task;
        String name;
        String[] temp;
        switch(input_sp[0]) {
            case "list":
                echo("Here are the tasks in your list: ");
                int i = 1;
                for (Task each_task : listoftasks) {
                    echo(i++ + ". " + each_task);
                }
                break;
            case "mark":
                echo("Nice! I've marked this task as done:");
                num = Integer.parseInt(input_sp[1]);
                curr_task = listoftasks.get(num-1);
                curr_task.mark();
                echo(curr_task.toString());
                break;
            case "unmark":
                echo("Ok, I've marked this task as not done yet:");
                num = Integer.parseInt(input_sp[1]);
                curr_task = listoftasks.get(num-1);
                curr_task.unmark();
                echo(curr_task.toString());
                break;
            case "bye":
                // exits the program
                exited = true;
                exit();
                break;
            case "todo":
                try {
                    name = input.substring(5);
                    curr_task = new TodoTask(name);
                    add_task(curr_task);
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
                    curr_task = new DeadlineTask(name, date);
                    add_task(curr_task);
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
                    curr_task = new EventTask(name, date1, date2);
                    add_task(curr_task);
                } catch (IndexOutOfBoundsException e) {
                    echo("Sorry, this command is in the wrong format.\n" +
                            "The way to use the command is: event taskname /from date_and_time /to date_and_time");
                }
                break;
            case "help":
                String help_message = "Hi! Here are the list of commands I recognise: \n\n" +
                        "'list' - lists the tasks registered in the list\n" +
                        "'todo' - adds an item that has no due date\n" +
                        "'deadline' - adds an item with a due date\n" +
                        "'event' - adds an item that has a duration\n" +
                        "'mark' - marks the task as completed\n" +
                        "'unmark' - marks the task as not completed\n" +
                        "'help' - opens the list of commands available\n" +
                        "'bye' - exits the chatbot";
                echo(help_message);
                break;
            default:
                String unrecognised = "Oops, I have no idea what that means. " +
                        "Use 'help' for a list of commands I recognise.";
                echo(unrecognised);
                break;
        }
        print_horizontal_line();
    }
    public static void add_task(Task task) {
        listoftasks.add(task);
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo("Now you have " + listoftasks.size() + " tasks in the list.");
    }
    public static void main(String[] args) {
        greet();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader((System.in)));

        while (!exited) {
            try {
                parse_input(reader);
            } catch (IOException e) {
                System.out.println("IOException occurred.");
            }
        }
    }
}
