import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {
    public static boolean hasExited = false;
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Prints the greeting message by the Chaterpillar chatbot.
     * It also prints the horizontal lines as dividers before and after the message.
     */
    public static void greet() {
        printHorizontalLine();
        System.out.println("Hello! I'm Chaterpillar");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Exits the program.
     * It sets the exited flag to true, and prints the exit message.
     */
    public static void exit() {
        hasExited = true;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the message given in the String argument.
     * @param s the message to be printed
     */
    public static void echo(String s) {
        System.out.println(s);
    }

    /**
     * Prints out a horizontal line, typically used to segment
     * the start and end of a message by the chatbot.
     */
    public static void printHorizontalLine() {
        String line = "-".repeat(50);
        System.out.println(line);
    }

    /**
     * Parses the input from the user, then calls the respective
     * methods to deal with the various actions of the chatbot.
     *
     * @param reader <code>BufferedReader</code> used to read from <code>System.in</code>
     * @return <code>Task</code> object
     * @throws IOException if there are any input/output errors
     * @see IOException
     */
    public static Boolean parseInput(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        String[] inputSplit = input.split(" ");
        printHorizontalLine();

        int num;
        Task currTask = null;
        String name;
        String[] temp;
        boolean edited = false;

        switch(inputSplit[0]) {
        case "list":
            // ToDo: Fix Null Pointer Exception
            echo("Here are the tasks in your list: ");
            int i = 1;
            for (Task eachTask : tasks) {
                echo(i++ + ". " + eachTask);
            }
            break;
        case "mark":
            echo("Nice! I've marked this task as done:");
            num = Integer.parseInt(inputSplit[1]);
            currTask = tasks.get(num-1);
            currTask.mark();
            echo(currTask.toString());
            edited = true;
            break;
        case "unmark":
            echo("Ok, I've marked this task as not done yet:");
            num = Integer.parseInt(inputSplit[1]);
            currTask = tasks.get(num-1);
            currTask.unmark();
            echo(currTask.toString());
            edited = true;
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
            edited = true;
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
            } catch (DateTimeParseException e) {
                echo("Unable to add task, wrong date/time format!");
                echo("Suggested format: DD/MM/YYY HH:MM");
            }
            edited = true;
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
            } catch (DateTimeParseException e) {
                echo("Unable to add task, wrong date/time format!");
                echo("Suggested format: DD/MM/YYY HH:MM");
            }
            edited = true;
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
            edited = true;
            break;
        case "today":
            echo("Here are the tasks for today:");
            DateTime today = new DateTime(LocalDate.now());
            ArrayList<Task> tasksToDisplayList = new ArrayList<Task>();
            for (Task task : tasks) {
                if (task.hasDate) {
                    if(task.isWithinDate(today)) {
                        tasksToDisplayList.add(task);
                    }
                }
            }
            listTasks(tasksToDisplayList);
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
        return edited;
    }

    /**
     * Adds the specified task (in the argument) to the
     * static <code>ArrayList</code> to be tracked by the chatbot.
     * @param task <code>Task</code> object containing the specified task
     */
    public static void addTask(Task task) {
        tasks.add(task);
        echo("Got it. I've added this task:");
        echo(task.toString());
        echo("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes the task at the specified index,
     * then shifts all the tasks in the index behind it up by 1.
     * @param index <code>Integer</code> specifying the number of the task to be deleted
     */
    public static void deleteTask(int index) {
        Task task = tasks.remove(index);
        echo("Got it. I've removed this task:");
        echo(task.toString());
        echo("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void listTasks(List<Task> list) {
        int i = 1;
        for (Task each_task : list) {
            echo(i++ + ". " + each_task);
        }
    }

    public static void main(String[] args) throws IOException {
        greet();
        Storage storage = new Storage();
        tasks = storage.loadFromFile();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader((System.in)));

        while (!hasExited) {
            try {
                if(parseInput(reader)) {
                    storage.saveAllToFile(tasks);
                }
            } catch (IOException e) {
                System.out.println("IOException occurred.");
            }
        }
    }
}
