import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * This class implements the chatbot Scribbles.
 *
 * @author danielle
 */
public class Scribbles {

    private static ArrayList<Task> taskList = new ArrayList<>();

    private Scribbles() {
    }

    /**
     * Prints the greeting message of the chatbot.
     *
     * @return Greeting message
     */
    public static String greet() {
        String greetingMessage = "Hello! I'm Scribbles :) What can I do for you?\n";
        return greetingMessage;
    }

    /**
     * Prints the exiting message of the chatbot.
     *
     * @return Exit message
     */
    public static String exit() {
        String exitingMessage = "Bye! Hope to see you again soon :)";
        return exitingMessage;
    }

    /**
     * Prints the current list of tasks.
     *
     * @return List of tasks
     */
    public static String listTasks() {
        int numOfTasks = taskList.size(); // number of task in list currently
        int index = 1; // index of the task in list
        String listOfTasks = "";

        while (numOfTasks != 0) {
            listOfTasks = listOfTasks + index + ". " + taskList.get(index - 1).toString() + "\n";
            numOfTasks--;
            index++;
        }

        return listOfTasks;
    }

    /**
     * Marks task in list as completed.
     *
     * @param index Index of task to mark
     */
    public static void markCompleted(int index) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            taskList.get(index - 1).markComplete();
        }
    }

    /**
     * Prints message after marking task instruction is called.
     *
     * @param index Index of task to mark
     * @return Message to confirm marking of a task
     */
    public static String markCompleteMessage(int index) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            return "Nice! I've marked task number " + index + " as done: \n" +
                    taskList.get(index - 1).toString() + "\n";
        } else { // message if task index does not exist
            return "Uh oh! looks like that task does not exist in your list.\n" +
                    "You currently only have " + numOfTasks + " task(s) in your list.\n";
        }
    }

    /**
     * Unmarks task in list.
     *
     * @param index Index of task to unmark
     */
    public static void markIncomplete(int index) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            taskList.get(index - 1).markIncomplete();
        }
    }

    /**
     * Prints message after unmarking task instruction is called.
     *
     * @param index Index of task to unmark
     * @return Message to confirm unmarking of a task
     */
    public static String markIncompleteMessage(int index) {
        int numOfTasks = taskList.size();
        if (index <= numOfTasks) {
            return "Okay! I've marked task number " + index + " as incomplete: \n" +
                    taskList.get(index - 1).toString() + "\n";
        } else { // message if task index does not exist
            return "Uh oh! looks like that task does not exist in your list.\n" +
                    "You currently only have " + numOfTasks + " task(s) in your list.\n";
        }
    }

    /**
     * Adds a to-do task to the task list.
     * Prints error message if description of task is empty.
     *
     * @param description Description of task
     */
    public static void addTodo(String description) {
        if (description.isEmpty()) {
            System.out.println("Uh oh! There's missing information in your instructions!");
            System.out.println("You can try the command \"todo [task description]\" instead.\n");
        } else {
            taskList.add(new Todo(description, false));
            System.out.println("I've added this to-do to your list: ");
            System.out.println(taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");
        }
    }

    /**
     * Adds a deadline task to the task list.
     * Prints error message if there are missing information in the description.
     *
     * @param description Description of task
     */
    public static void addDeadline(String description) {
        if (description.isEmpty() || !description.contains(" /by ")) {
            System.out.println("Uh oh! there's missing information in your instructions!");
            System.out.println("You can try the command \"deadline [task description] /by [date/time]\" " +
                    "instead.\n");
        } else {
            try {
                String taskDescription = description.split(" /by")[0];
                String taskDeadline = description.split(" /by ")[1];
                taskList.add(new Deadline(taskDescription, false, taskDeadline));
                System.out.println("I've added this deadline to your list:");
                System.out.println(taskList.get(taskList.size() - 1).toString());
                System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Uh oh! there's missing information in your instructions!");
                System.out.println("You can try the command \"deadline [task description] /by [date/time]\" " +
                        "instead.\n");
            }

        }
    }

    /**
     * Adds an event task to the task list.
     * Prints errpr message if there are any missing information in the description.
     *
     * @param description Description of task
     */
    public static void addEvent(String description) {
        if (description.isEmpty() || !description.contains(" /from ") || !description.contains(" /to ")) {
            System.out.println("Uh oh! there's missing information in your instructions!");
            System.out.println("You can try the command \"event [task description] /from [date/time] " +
                    "/to [date/time]\" instead.\n");
        } else {
            try {
                String taskDescription = description.split(" /from ")[0];
                String taskStart = description.split(" /from ")[1].split("/to ")[0];
                String taskEnd = description.split(" /to ")[1];
                if (!taskDescription.isEmpty() && !taskStart.isEmpty() && !taskEnd.isEmpty()) {
                    taskList.add(new Event(taskDescription, false, taskStart, taskEnd));
                    System.out.println("I've added this deadline to your list:");
                    System.out.println(taskList.get(taskList.size() - 1).toString());
                    System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");
                } else {
                    System.out.println("Uh oh! there's missing information in your instructions!");
                    System.out.println("You can try the command \"event [task description] /from [date/time] " +
                            "/to [date/time]\" instead.\n");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Uh oh! there's missing information in your instructions!");
                System.out.println("You can try the command \"event [task description] /from [date/time] " +
                        "/to [date/time]\" instead.\n");
            }
        }
    }

    public static void deleteTask(int index) {
        int numOfTasks = taskList.size(); // finds the number of tasks in list currently
        if (index <= numOfTasks) {
            String taskRemoved = taskList.get(index - 1).toString();
            taskList.remove(index - 1);
            System.out.println("I've removed this task from your list:");
            System.out.println(taskRemoved);
            System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");
        } else {
            System.out.println("Uh oh! looks like that task does not exist in your list.");
            System.out.println("You currently only have " + numOfTasks + " task(s) in your list.\n");
        }
    }

    /**
     * Loads data from the file to Scribbles.
     *
     * @param filePath File path where tasks are stored
     * @throws FileNotFoundException If data file does not exist
     */
    public static void loadFileData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            String delimiter = "\\s*\\|\\s*";

            while (line != null) {
                String[] tokens = line.split(delimiter);
                String typeOfTask = tokens[0].trim();
                int completed = Integer.parseInt(tokens[1].trim());
                boolean isCompleted = (completed == 1 ? true : false);
                String description = tokens[2].trim();

                switch(typeOfTask){
                    case "T":
                        taskList.add(new Todo(description, isCompleted));
                        break;
                    case "D":
                        String deadline = tokens[3].trim();
                        taskList.add(new Deadline(description, isCompleted, deadline));
                        break;
                    case "E":
                        String start = tokens[3].trim();
                        String end = tokens[4].trim();
                        taskList.add(new Event(description, isCompleted, start, end));
                        break;
                    default:
                        System.out.println("Invalid task type \"" + typeOfTask + "\" was found in file.");
                }

                line = reader.readLine(); // read the next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits data in the file.
     *
     * @param filePath File path where tasks are stored
     * @throws FileNotFoundException If data file does not exist
     */
    public static void saveFileData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner data = new Scanner(f);
    }

    /**
     * Prints the error message for invalid inputs into chatbot.
     *
     * @return Error message
     */
    public static String inputErrorMessage() {
       String errorMessage = "Sorry, Scribbles was unable to understand your instructions :(\n" +
               "Try formatting your instructions as follows: \n" +
               "- type \"list\" to view your current list of tasks\n" +
               "- type \"mark [index]\" to mark task at index as completed\n" +
               "- type \"unmark [index]\" to mark task at index as incomplete\n" +
               "- type \"todo [task]\" to insert to-do task into your list\n" +
               "- type \"deadline [task] /by [date]\" to insert task into your list with deadline as date\n" +
               "- type \"event [task] /from [start] to [end]\" " +
               "to insert task into your list with a start and end duration\n" +
               "- type \"delete [index]\" to remove a task at index from your list\n" +
               "Please try again :)\n";
       return errorMessage;
    }

    public static void main(String[] args) {
        // read data stored in hard disk
        try {
            loadFileData("src/main/java/taskData.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }

        Scanner sc = new Scanner(System.in); // scanner for user input

        System.out.println((greet()));

        String input = sc.nextLine(); // takes in input from user

        while (!(input.equals("bye"))) {

            if (input.equals("list")) { // user inputs "list"
                System.out.println("Here are the tasks in your list:");
                System.out.println(listTasks());
                input = sc.nextLine();

            } else if (input.startsWith("mark ")) { // user inputs "mark..." to mark task in list
                int index = Integer.parseInt(input.substring(5)); // get the index to mark
                markCompleted(index);
                System.out.println(markCompleteMessage(index));
                input = sc.nextLine();

            } else if (input.startsWith("unmark ")) { // user inputs "unmark..." to unmark task in list
                int index = Integer.parseInt(input.substring(7)); // get the task number to unmark
                markIncomplete(index);
                System.out.println(markIncompleteMessage(index));
                input = sc.nextLine();

            } else if (input.startsWith("todo ")) { // adds a to-do task to the list
                String description = input.substring(5);
                addTodo(description);
                input = sc.nextLine();

            } else if (input.startsWith("deadline ")) {
                String description = input.substring(9);
                addDeadline(description);
                input = sc.nextLine();

            } else if (input.startsWith("event ")) {
                String description = input.substring(6);
                addEvent(description);
                input = sc.nextLine();

            } else if (input.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(input.substring(7)); // get the task number to remove
                deleteTask(taskNumber);
                input = sc.nextLine();

            } else { // invalid inputs
                System.out.println(inputErrorMessage());
                input = sc.nextLine();
            }
        }

        System.out.println(exit()); // exits when user inputs bye
    }
}
