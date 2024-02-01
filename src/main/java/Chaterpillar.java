import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

/**
 * Main class for Chaterpillar chatbot.
 */
public class Chaterpillar {
    public static boolean exited = false;
    public static ArrayList<Task> listOfTasks = new ArrayList<Task>();
    public static final String CHATBOT_PATH_NAME = "./data";
    public static final String CHATBOT_FILE_NAME = "history.txt";

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
        exited = true;
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
     * @param reader used to read from System.in
     * @throws IOException if there are any input/output errors
     * @see IOException
     */
    public static Task parseInput(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        String[] inputSplit = input.split(" ");
        printHorizontalLine();

        int num;
        Task currTask = null;
        String name;
        String[] temp;
        switch(inputSplit[0]) {
        case "list":
            // ToDo: Fix Null Pointer Exception
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
        return currTask;
    }

    /**
     * Adds the specified task (in the argument) to the
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
     * Deletes the task at the specified index,
     * then shifts all the tasks in the index behind it up by 1.
     * @param index integer specifying the number of the task to be deleted
     */
    public static void deleteTask(int index) {
        Task task = listOfTasks.remove(index);
        echo("Got it. I've removed this task:");
        echo(task.toString());
        echo("Now you have " + listOfTasks.size() + " tasks in the list.");

    }

    /**
     * Gets the path of the file that stores the list of tasks.
     * Creates the directory if it is not found.
     * Creates the file if it does not exist.
     * @return <code>Path</code> of the file
     * @throws IOException if there are any input/output errors
     */
    public static Path getHistoryFilePath() throws IOException {
        Path chatbotDataFilePath = Paths.get(CHATBOT_PATH_NAME);
        if (!Files.exists(chatbotDataFilePath)) {
            Files.createDirectory(chatbotDataFilePath);
        }

        Path chatbotFilePath = chatbotDataFilePath.resolve(CHATBOT_FILE_NAME);
        if (!Files.exists(chatbotFilePath)) {
            Files.createFile(chatbotFilePath);
        }
        return chatbotFilePath;
    }

    /**
     * Loads the contents of the file and inputs it into the listOfTasks.
     * @return An <code>ArrayList</code> of <code>Task</code> objects.
     * @throws IOException if there are any input/output errors.
     */
    public static ArrayList<Task> loadFromFile() throws IOException {
        Path path = getHistoryFilePath();
        ArrayList<Task> newList = new ArrayList<Task>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String str;
            while ((str = reader.readLine()) != null) {
                try {
                    String[] eachLine = str.split("\\|");
                    String taskType = eachLine[0];
                    boolean isMarked = Boolean.parseBoolean(eachLine[1]);
                    String taskName = eachLine[2];
                    switch (taskType) {
                    case "T": {
                        Task task = new TodoTask(taskName, isMarked);
                        newList.add(task);
                        break;
                    }
                    case "D": {
                        String dueDate = eachLine[3];
                        Task task = new DeadlineTask(taskName, isMarked, dueDate);
                        newList.add(task);
                        break;
                    }
                    case "E": {
                        String startDate = eachLine[3];
                        String endDate = eachLine[4];
                        Task task = new EventTask(taskName, isMarked, startDate, endDate);
                        newList.add(task);
                        break;
                    }
                    default:
                        System.out.println("Error in type of task of this line: ");
                        System.out.println(str);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error in formatting of this line: ");
                    System.out.println(str);
                }
            }
        }
        return newList;
    }

    /**
     * Saves the entire list of Tasks into the file, by first clearing its contents.
     * @throws IOException if there are any input/output errors.
     */
    public static void saveAllToFile() throws IOException {
        Path path = getHistoryFilePath();
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())) {
            writer.write("");   // clears the file
            StringBuilder strBdr = new StringBuilder();
            for (Task task : listOfTasks) {
                String str = task.stringForSaving();
                strBdr.append(str).append("\n");
            }
            writer.write(strBdr.toString());
        } catch (IOException e) {
            // ToDo: Handle IOException
        }
    }
    public static void main(String[] args) throws IOException {
        greet();
        listOfTasks = loadFromFile();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader((System.in)));

        while (!exited) {
            try {
                Task task = parseInput(reader);
                if (task != null) {
                    saveAllToFile();
                }
            } catch (IOException e) {
                System.out.println("IOException occurred.");
            }
        }
    }
}
