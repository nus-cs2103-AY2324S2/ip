import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.nio.file.*;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Contains the logic for the ChatBot named 'Campus'
 */
public class Campus {
    public static String DATAFILEPATH = "src/main/java/data.txt";
    static List<Task> tasks = new ArrayList<>();

    /**
     * Main Driver Logic of the Campus Class which handles user inputs and sorts them into cases
     */
    public static void main(String[] args) {
        Campus.greet();

        try {
            String filepath = Campus.DATAFILEPATH;
            List<String> lines = Campus.readFromDBCreateIfNotExists(filepath);
            updateListFromFile(lines);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (CampusException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        String userInput;
        int response = 0;

        do {
            try {
                userInput = scanner.nextLine();
                response = processCommand(userInput);
            } catch (CampusException e) {
                String message = "------------------------------\n"
                        + String.format("%s\n", e.getMessage())
                        + "------------------------------\n";
                System.out.println(message);
            }
        } while (response != 1);

        scanner.close();
        Campus.exit();
    }

    /**
     * Updates the Campus.tasks field using whatever data is in the data.text file
     * @param listOfStrings A list of each line in the data.text file converted into a List<String>
     * @throws CampusException Throws an error, likely that the data.text file is corrupted
     */
    public static void updateListFromFile(List<String> listOfStrings) throws CampusException {
        if (listOfStrings == null) {
            Campus.tasks = new ArrayList<>();
            return;
        }

        List<Task> task = new ArrayList<>();

        for (String string : listOfStrings) {
            String[] parts = string.split("\\|");
            String typeOfTask = parts[0].trim();
            switch (typeOfTask) {
                case "T":
                    if (parts.length != 3) {
                        throw new CampusException("File is Corrupted, Check Formatting for 'T'");
                    } else {
                        String todoName = parts[2].trim();
                        Boolean completed = parts[1].trim().equals("1");
                        task.add(new ToDos(todoName, completed));
                    }
                    break;
                case "D":
                    if (parts.length != 4) {
                        throw new CampusException("File is Corrupted, Check Formatting for 'D'");
                    } else {
                        String deadlineName = parts[2].trim();
                        Boolean completed = parts[1].trim().equals("1");
                        String deadlineEndTime = parts[3].trim();
                        task.add(new Deadline(deadlineName, completed, deadlineEndTime));
                    }
                    break;
                case "E":
                    if (parts.length != 5) {
                        throw new CampusException("File is Corrupted, Check Formatting for 'E'");
                    } else {
                        String eventName = parts[2].trim();
                        Boolean completed = parts[1].trim().equals("1");
                        String eventStartTime = parts[3].trim();
                        String eventEndTime = parts[4].trim();
                        task.add(new Event(eventName, completed, eventStartTime, eventEndTime));
                    }
            }
        }
        Campus.tasks = task;
    }

    /**
     * Updates the data.txt file using what is currently in the Campus.tasks field
     * @param listOfTasks Usually from the main Campus.tasks field
     */
    public static void updateFileFromList(List<Task> listOfTasks) {
        String filePath = Campus.DATAFILEPATH;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : listOfTasks) {
                writer.write(task.toDBFormat());
                writer.newLine();
            }
            // System.out.println("Tasks written to file successfully");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Function to read from the expected file called "data", if not, create the file and return null
     * @param filePath Takes in the relative path file that it expects the datafile to be at
     * @return Returns a list of strings, each string contains information about the DB, null if initialised for the first time
     */
    public static List<String> readFromDBCreateIfNotExists(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
            return null;
        }
        return Files.readAllLines(path);
    }

    public static Task getIthTask(String userInput) {
        String listNumber = userInput.substring(userInput.length() - 1);
        int index = Integer.parseInt(listNumber) - 1;
        return Campus.tasks.get(index);
    }

    public static void handleUpdateCommands (String command, String userInput) {
        Task task = getIthTask(userInput);
        switch (command) {
            case "mark":
                Campus.markDone(task);
                break;
            case "unmark":
                Campus.markUndone(task);
            case "delete":
                Campus.delete(task);
        }
    }

    public static void handleTodoCommand(String remaining) throws CampusException {
        if (remaining.isEmpty()) {
            throw new CampusException("Error! A todo task must have a name, please follow the following syntax: todo <task name>\n");
        } else {
            ToDos todo = new ToDos(remaining);
            Campus.add(todo);
        }
    }

    public static void handleDeadlineCommand(String remaining) throws CampusException {
        String[] temp = remaining.split("/by", 2);
        if (temp.length != 2) {
            throw new CampusException("Error! A deadline task must have the correct number of parameters, please follow the following syntax: deadline <deadline name> /by <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String deadlineName = temp[0].trim();
        String endDateTime = temp[1].trim();

        try {
            Deadline deadline = new Deadline(deadlineName, endDateTime);
            Campus.add(deadline);
        } catch (CampusException e) {
            System.out.printf("%s\n%n", e.getMessage());
        }
    }

    public static void handleEventCommand(String remaining) throws CampusException {
        String[] temp = remaining.split("/from", 2);

        if (temp.length != 2) {
            throw new CampusException("Error! An event task must have the correct number of parameters, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String eventName = temp[0].trim();
        String remaining1 = temp[1].trim();
        String[] temp2 = remaining1.split("/to", 2);

        if (temp2.length != 2) {
            throw new CampusException("Error! An event task must have parameters, please follow the following syntax: event <event name> /from <startDateTime (HHmm dd/MM/yyyy)> /to <endDateTime (HHmm dd/MM/yyyy)>\n");
        }

        String from = temp2[0].trim();
        String to = temp2[1].trim();

        try {
            Event event = new Event(eventName, from, to);
            Campus.add(event);
        } catch (CampusException e) {
            System.out.printf("%s\n%n", e.getMessage());
        }
    }

    /**
     * Main Logic to keep the Chat Bot running until a user decides to quit talking by sending 'bye'
     * @param userInput A String i/o input
     * @return Returns int value 0 except for the "bye" case in which the value is 1
     * @throws CampusException Throws a CampusException, may arise due to improper syntax or unknown commands
     */
    public static int processCommand(String userInput) throws CampusException {
        String[] arr = userInput.split(" ", 2);
        String firstWord;
        String remaining;

        if (arr.length > 1) {
            firstWord = arr[0].trim();
            remaining = arr[1].trim();
        } else {
            firstWord = arr[0].trim();
            remaining = "";
        }

        switch(firstWord) {
            case "list":
                Campus.display();
                break;
            case "mark":
            case "unmark":
            case "delete":
                handleUpdateCommands(firstWord, userInput);
                break;
            case "todo":
                handleTodoCommand(remaining);
                break;
            case "deadline":
                handleDeadlineCommand(remaining);
                break;
            case "event":
                handleEventCommand(remaining);
                break;
            case "bye":
                return 1;
            case "":
                break;
            default:
                throw new CampusException("Sorry, I don't understand that command, please check for potential spelling errors");
        }

        updateFileFromList(Campus.tasks);

        return 0;
    }

    public static void delete(Task task) {
        Campus.tasks.remove(task);
        int numOfTasks = Campus.tasks.size();

        String message = "------------------------------\n"
                + "Noted. Task deleted successfully. I have removed the following task:\n"
                + String.format("%s\n", task)
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void markDone(Task task) {
        task.markComplete();

        String message = "------------------------------\n"
                + "Nice! I've completed this task successfully:\n"
                + String.format("%s\n", task)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void markUndone(Task task) {
        task.markIncomplete();

        String message = "------------------------------\n"
                + "Ok, this task is still not done yet:\n"
                + String.format("%s\n", task)
                + "------------------------------\n";

        System.out.println(message);
    }

    /**
     * Displays all the tasks currently stored in the Campus Class by iterating through the list of type Task
     * and adding it to a final string
     */
    public static void display() {
        int numOfTasks = Campus.tasks.size();
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < numOfTasks; i++) {
            int listNum = i + 1;
            Task task = Campus.tasks.get(i);
            listOfTasks.append(String.format("%s. %s\n", listNum, task.toString()));
        }

        String message = "------------------------------\n"
                + String.format("%s\n", listOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    /**
     * Adds a Task instance to the list of tasks in the Campus Class
     * @param task an instance of the Task Class
     */
    public static void add(Task task) {
        Campus.tasks.add(task);

        int numOfTasks = Campus.tasks.size();

        String message = "------------------------------\n"
                + "Got it. I've added this to our list of tasks:\n"
                + String.format("added: %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", numOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void greet() {
        String message = "------------------------------\n"
                + "Hello! I'm Campus\n"
                + "What can I do for you?\n"
                + "\n"
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void exit() {
        String message = "------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "\n"
                + "------------------------------\n";
        System.out.println(message);
    }
}
