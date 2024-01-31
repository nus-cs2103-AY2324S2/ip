import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Jimmy {

    static final String FOLDER_PATH = "./data";
    static final String FILE_PATH = FOLDER_PATH + "/jimmy";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Greets the user.
     */
    private static void greetUser() {
        System.out.println("Hello! I'm Jimmy\nWhat can I do for you?\n");
    }

    /**
     * Writes the contents of the task list into the file.
     *
     * @throws JimmyException If the file cannot be written to.
     */
    private static void writeToFile() throws JimmyException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("Writing to file...");
            for (Task task : taskList) {
                bufferedWriter.write(task.toFileString() + System.lineSeparator());
            }
            bufferedWriter.close();
            System.out.println("Done with writing!");
        } catch (IOException e) {
            throw new JimmyException("IOException: Cannot write to file.");
        }
    }

    /**
     * Loads the contents of the file into memory.
     *
     * @throws JimmyException If the file cannot be loaded.
     */
    private static void loadFileContents() throws JimmyException {
        try {
            System.out.println("Loading file contents...");
            File file = new File(FILE_PATH);

            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            if (isFolderMade || isFileMade) {
                System.out.println("File not found, Jimmy is creating one for you now...");
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                parseFileString(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new JimmyException("IOException: Cannot load file.");
        }
    }

    /**
     * Parses the file string and appends task from memory into task list.
     *
     * @param fileString The string to be parsed.
     */
    private static void parseFileString(String fileString) {
        String[] attributes = fileString.split(" \\| "); // split by delimiter to obtain the task name, status and timings (if any)
        String taskType = attributes[0];
        boolean isTaskCompleted = Objects.equals(attributes[1], "1");

        switch (taskType) {
        case "T":
            String taskDesc = attributes[2];
            taskList.add(new Todo(taskDesc, isTaskCompleted));
            break;
        case "D":
            String deadlineDesc = attributes[2];
            String deadline = attributes[3];
            taskList.add(new Deadline(deadlineDesc, deadline, isTaskCompleted));
            break;
        case "E":
            String eventDesc = attributes[2];
            String start = attributes[3];
            String end = attributes[4];
            taskList.add(new Event(eventDesc, start, end, isTaskCompleted));
            break;
        }
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param instruction The type of task to be created.
     * @param details     The details of the task to be created.
     * @throws JimmyException If the task type is invalid or the details are invalid.
     */
    private static void createNewTask(String instruction, String details) throws JimmyException {
        Task newTask = null;
        try {
            switch (instruction) {
            case "todo":
                newTask = createNewTodo(details);
                break;
            case "deadline":
                newTask = createNewDeadline(details);
                break;
            case "event":
                newTask = createNewEvent(details);
                break;
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new JimmyException("Please enter a valid task type.");
        }

        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println(generateListCounter() + "\n");
    }

    /**
     * Creates a new todo task.
     *
     * @param details The details of the todo task.
     * @return The new todo task.
     */
    private static Todo createNewTodo(String details) {
        return new Todo(details, false);
    }

    /**
     * Creates a new deadline task.
     *
     * @param details The details of the deadline task.
     * @return The new deadline task.
     * @throws JimmyException If the deadline task details are invalid.
     */
    private static Deadline createNewDeadline(String details) throws JimmyException {
        if (!details.contains(" /by ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new deadline.");
        }
        String[] deadlineDetails = details.split(" /by ", 2);
        String deadlineName = deadlineDetails[0], deadline = deadlineDetails[1];
        if (deadlineName.length() == 0 || deadline.length() == 0) {
            throw new JimmyException("Please check that you have entered a deadline name and a deadline.");
        }
        return new Deadline(deadlineName, deadline, false);
    }

    /**
     * Creates a new event task.
     *
     * @param details The details of the event task.
     * @return The new event task.
     * @throws JimmyException If the event task details are invalid.
     */
    private static Event createNewEvent(String details) throws JimmyException {
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new JimmyException("Sorry! " +
                    "Please use the correct format when creating a new event.");
        }
        String[] eventDetails = details.split(" /from | /to ");
        String eventName = eventDetails[0], start = eventDetails[1], end = eventDetails[2];
        if (eventName.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new JimmyException("Please check that you have entered a event name, a start time and an end time.");
        }
        return new Event(eventName, start, end, false);
    }

    /**
     * Generates the counter for the number of tasks in the task list.
     *
     * @return The counter for the task list.
     */
    private static String generateListCounter() {
        if (taskList.size() == 0) {
            return "You have no tasks, create some now!";
        } else if (taskList.size() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws JimmyException If the task index is invalid.
     */
    private static void deleteTask(String taskIndex) throws JimmyException {
        int deleteTask;
        try {
            deleteTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (deleteTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (deleteTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task deletedTask = taskList.remove(deleteTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(generateListCounter() + "\n");
    }

    /**
     * Displays the tasks in the task list.
     */
    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                System.out.println((i + 1) + "." + taskList.get(i).toString() + "\n");
            } else {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param taskIndex The index of the task to be marked as complete.
     * @throws JimmyException If the task index is invalid.
     */
    private static void markTaskComplete(String taskIndex) throws JimmyException {
        int completeTask;
        try {
            completeTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (completeTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (completeTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = taskList.get(completeTask);
        curr.markAsComplete();
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskIndex The index of the task to be marked as incomplete.
     * @throws JimmyException If the task index is invalid.
     */
    private static void markTaskIncomplete(String taskIndex) throws JimmyException {
        int incompleteTask;
        try {
            incompleteTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (incompleteTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (incompleteTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = taskList.get(incompleteTask);
        curr.markAsIncomplete();
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    private static int getListSize() {
        return taskList.size();
    }

    /**
     * Exits the bot.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Runs the bot.
     *
     * @param args The arguments passed in.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greetUser();
        boolean isBotRunning = true; // flag to check if bot is running

        try {
            loadFileContents();
        } catch (JimmyException e) {
            System.out.println(e.getMessage());
        }

        try {
            try {
                while (isBotRunning) {
                    String userInput = sc.nextLine();
                    String[] inputArray = userInput.split(" ", 2);
                    String instruction;
                    String details = "";
                    if (inputArray.length > 1) {
                        instruction = inputArray[0];
                        details = inputArray[1];
                    } else {
                        instruction = inputArray[0];
                    }
                    switch (instruction) {
                    case "bye":
                        exit();
                        isBotRunning = false;
                        break;
                    case "list":
                        displayTasks();
                        break;
                    case "mark":
                        markTaskComplete(details);
                        break;
                    case "unmark":
                        markTaskIncomplete(details);
                        break;
                    case "delete":
                        deleteTask(details);
                        break;
                    default:
                        createNewTask(instruction, details);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Please only use the specified commands in the user guide.");
            } finally {
                writeToFile();
            }
        } catch (JimmyException e) {
            System.out.println(e.getMessage());
        }
    }
}
