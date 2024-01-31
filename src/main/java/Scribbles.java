import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

            try {
                saveFileData("src/main/java/taskData.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
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

            try {
                saveFileData("src/main/java/taskData.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
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

            try {
                saveFileData("src/main/java/taskData.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }

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
                String taskDeadlineString = description.split(" /by ")[1].trim();

                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime taskDeadline = LocalDateTime.parse(taskDeadlineString, dateTimeFormat);

                taskList.add(new Deadline(taskDescription, false, taskDeadline));

                try {
                    saveFileData("src/main/java/taskData.txt");
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }

                System.out.println("I've added this deadline to your list:");
                System.out.println(taskList.get(taskList.size() - 1).toString());
                System.out.println("Now you have " + taskList.size() + " task(s) in the list.\n");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Uh oh! there's missing information in your instructions!");
                System.out.println("You can try the command \"deadline [task description] /by [date/time]\" " +
                        "instead.\n");

            } catch (DateTimeParseException e) {
                System.out.println("Uh oh! Looks like your date/time format is wrong!: ");
                System.out.println("Try formatting your date/time as dd/MM/yyyy HHmm.\n");
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
                String taskStartString = description.split(" /from ")[1].split("/to ")[0].trim();
                String taskEndString = description.split(" /to ")[1];

                if (!taskDescription.isEmpty() && !taskStartString.isEmpty() && !taskEndString.isEmpty()) {
                    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime taskStart = LocalDateTime.parse(taskStartString, dateTimeFormat);
                    LocalDateTime taskEnd = LocalDateTime.parse(taskEndString, dateTimeFormat);

                    taskList.add(new Event(taskDescription, false, taskStart, taskEnd));

                    try {
                        saveFileData("src/main/java/taskData.txt");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    }

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

            } catch (DateTimeParseException dateTimeException) {
                System.out.println("Uh oh! Looks like your date/time format is wrong!: ");
                System.out.println("Try formatting your date/time as dd/MM/yyyy HHmm.\n");
            }
        }
    }

    /**
     * Deletes task at index from taskList.
     *
     * @param index Index of task to delete
     */
    public static void deleteTask(int index) {
        int numOfTasks = taskList.size(); // finds the number of tasks in list currently
        if (index <= numOfTasks) {
            String taskRemoved = taskList.get(index - 1).toString();
            taskList.remove(index - 1);

            try {
                saveFileData("src/main/java/taskData.txt");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }

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
                        String deadlineString = tokens[3].trim();
                        try {
                            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime deadline = LocalDateTime.parse(deadlineString, dateTimeFormat);
                            taskList.add(new Deadline(description, isCompleted, deadline));
                        } catch (DateTimeParseException e) {
                            System.out.println("Your deadline, \"" + description + "\" stored in file has incorrect " +
                                    "date/time format and cannot be loaded into task list. ");
                        }
                        break;
                    case "E":
                        String startString = tokens[3].trim();
                        String endString = tokens[4].trim();
                        try {
                            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                            LocalDateTime start = LocalDateTime.parse(startString,dateTimeFormat);
                            LocalDateTime end = LocalDateTime.parse(endString, dateTimeFormat);
                            taskList.add(new Event(description, isCompleted, start, end));
                        } catch (DateTimeParseException e) {
                            System.out.println("Your event, \"" + description + "\" stored in file has incorrect " +
                                    "date/time format and cannot be loaded into task list. ");
                        }
                        break;
                    default:
                        System.out.println("Invalid task type \"" + typeOfTask + "\" was found in file.");
                }

                line = reader.readLine(); // read the next line
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Uh oh! looks like the data in your file is corrupted and cannot be read! " +
                    "Please verify data in the file to proceed. Proceeding without verification may cause " +
                    "current data to disappear. \n");
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
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task instanceof Todo) {
                    writer.write("T | " + (task.isCompleted() ? "1" : "0") + " | " + task.getDescription());
                }
                if (task instanceof Deadline) {
                    writer.write("D | " + (task.isCompleted() ? "1" : "0") + " | " + task.getDescription() +
                            " | " + ((Deadline) task).getByString());
                }
                if (task instanceof Event) {
                    writer.write("E | " + (task.isCompleted() ? "1" : "0") + " | " + task.getDescription() +
                            " | " + ((Event) task).getStartString() + " | " + ((Event) task).getEndString());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
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

        // check if directory exists
        File directory = new File("src/main/java/");
        if (!directory.exists()) {
            boolean directoryCreated = directory.mkdir();
            if (directoryCreated) {
                System.out.println("Directory for taskData.txt has been created.");
            }
            if (!directoryCreated) {
                System.out.println("Directory for taskData.txt could not be created.");
            }
        }

        // check if file exists
        File f = new File("src/main/java/taskData.txt");
        try {
            File file = new File("src/main/java/taskData.txt");
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("A new file, taskData.txt has been created.");
                }
                if (!fileCreated) {
                    System.out.println("A new file, taskData.txt could not be created.");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

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
