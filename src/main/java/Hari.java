import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a task management chatbot named Hari.
 */
class HandlerBot {

    private static final String FOLDER_PATH = "." + File.separator + "data";
    private static final String FILE_PATH = FOLDER_PATH + File.separator + "hari.txt";

    /**
     * Main class for the Hari chatbot.
     */
    public Task[] arrTasks; // To store tasks created by the user for easy retrieval and listing
    public int counterTasks; // Counter for assumption that there are no more than 100 tasks

    // Class object
    public HandlerBot() {
        arrTasks = new Task[100]; // Assumption that there are no more than 100 tasks
        counterTasks = 0; // Counter for the number of tasks
    }

    // Class for task (called Task)
    public class Task {
        private String summary; // Description of tasks
        private boolean completion; // To check if a task is or is not completed
        private String taskType; // To identify the type of task
        private LocalDate deadline; // Add LocalDate for deadline
        private String timerStart; // Start time for Event tasks
        private String timerEnd; // End time for Event tasks
        private String deadlineStat; // Deadline for Deadline tasks

        // Constructor for To Do tasks
        public Task(String summary) {
            this.summary = summary;
            this.completion = false;
            this.taskType = "T";
            this.deadline = null;
            this.timerStart = null;
            this.timerEnd = null;
        }

        // Constructor for Deadline tasks
        public Task(String summary, String deadlineStat) {
            this.summary = summary;
            this.completion = false;
            this.taskType = "D";
            this.deadline = parseDeadline(deadlineStat);
            this.timerStart = null;
            this.timerEnd = null;
        }

        // Constructor for Event tasks
        public Task(String summary, String startTime, String endTime) {
            this.summary = summary;
            this.completion = false;
            this.taskType = "E";
            this.deadline = null;
            this.timerStart = startTime;
            this.timerEnd = endTime;
        }

        // Additional method to parse deadline string into LocalDate
        private LocalDate parseDeadline(String deadlineStat) {
            try {
                // Parse the date string into a LocalDate object
                return LocalDate.parse(deadlineStat, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                // Handle parsing exception (invalid date format)
                System.out.println("Error when parsing deadline. Please use the format yyyy-MM-dd.");
                return null;
            }
        }

        // To mark as completed
        public void completionMark() {
            this.completion = true;
        }

        // To unmark completed tasks
        public void incompletionMark() {
            this.completion = false;
        }

        // For displaying the X or [ ] depending on completion status
        public String completionStatus() {
            return (completion ? "X" : " "); // To display the X or [ ]
        }

        // For displaying task description
        public String summaryStatus() {
            return summary;
        }

        // For displaying the task type
        public String taskStatus() {
            return taskType;
        }

        // For displaying start time of event tasks
        public String timerStartStatus() {
            return timerStart;
        }

        // For displaying end time of event tasks
        public String timerEndStatus() {
            return timerEnd;
        }

        // For displaying deadline of deadline tasks
        public String deadlineStatus() {
            return deadlineStat;
        }

        // Additional method to check if task description is empty
        public boolean summaryEmpty() {
            return summary.trim().isEmpty();
        }

        // Additional method to display deadline in a different format
        public String formattedDeadline() {
            return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    // Task finder
    public void findTask(String keyword) {
        boolean found = false;
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");

        for (int i = 0; i < counterTasks; i++) {
            if (arrTasks[i].summaryStatus().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(" " + (i + 1) + "." + arrTasks[i].summaryStatus() + "  " +
                        (arrTasks[i].taskStatus().equals("E") ?
                                " (from: " + arrTasks[i].timerStartStatus() + " to: " + arrTasks[i].timerEndStatus() + ")" :
                                (arrTasks[i].taskStatus().equals("D") ? " (by: " + arrTasks[i].deadlineStatus() + ")" : "")));
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No matching tasks found.");
        }

        System.out.println("____________________________________________________________");
    }

    // Function to save tasks to a file
    private void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < counterTasks; i++) {
                Task task = arrTasks[i];
                writer.write(task.taskStatus() + " | " + (task.completion ? "1" : "0") + " | " +
                        task.summaryStatus() + " | " +
                        (task.taskStatus().equals("D") ? task.deadlineStatus() : "") + " | " +
                        (task.taskStatus().equals("E") ? task.timerStartStatus() + " to " + task.timerEndStatus() : "") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to load data from the file
    public void loadFromFile() {
        File folder = new File(FOLDER_PATH);
        File file = new File(FILE_PATH);

        try {
            // Create the folder if it doesn't exist
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    System.out.println("Data folder created.");
                } else {
                    System.out.println("Failed to create data folder.");
                }
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Data file created.");
                } else {
                    System.out.println("Failed to create data file.");
                }
            }

            // Load tasks from the file
            loadTasksFromFile();

        } catch (IOException e) {
            System.out.println("Error handling file operations: " + e.getMessage());
        } catch (CorruptedDataException e) {
            System.out.println("Error loading data from file. The file may be corrupted.");

            // Handle the corrupted file situation (prompt the user, take corrective actions, etc.)
            // For example, you may choose to delete the corrupted file and create a new one.
            if (file.exists() && file.delete()) {
                System.out.println("Corrupted file deleted. A new file will be created.");
                loadFromFile(); // Retry loading from file
            } else {
                System.out.println("Failed to delete corrupted file. Please create a new file manually.");
            }
        }
    }

    // Function to load tasks from the file
    private void loadTasksFromFile() throws CorruptedDataException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean completion = parts[1].equals("1");
                String summary = parts[2];
                Task task;
                if (type.equals("T")) {
                    task = new Task(summary);
                } else if (type.equals("D")) {
                    task = new Task(summary, parts[3]);
                } else if (type.equals("E")) {
                    task = new Task(summary, parts[3], parts[4]);
                } else {
                    // Handle unrecognized task type
                    throw new CorruptedDataException("Unrecognized task type in the file.");
                }
                if (completion) {
                    task.completionMark();
                }
                arrTasks[counterTasks++] = task;
            }
        } catch (IOException e) {
            // Handle file not found or other IO exceptions
            e.printStackTrace();
        }
    }

    /**
     * Custom exception class for handling corrupted data during file operations.
     */
    class CorruptedDataException extends Exception {
        public CorruptedDataException(String message) {
            super(message);
        }
    }

    // Function that handles the greeting message
    public void messageGreeting() {
        try {
            // Load tasks from file when the chatbot starts up
            loadTasksFromFile();
            System.out.println("____________________________________________________________");
            System.out.println(" Hey! I'm Hari!");
            System.out.println(" How may I be of service today?");
            System.out.println("____________________________________________________________");
        } catch (CorruptedDataException e) {
            System.out.println("Error loading data from file. File may be corrupted.");

            // Handle the corrupted file situation
            File file = new File(FILE_PATH);
            if (file.exists() && file.delete()) {
                System.out.println("Corrupted file deleted. A new file will be created.");
                messageGreeting(); // Retry loading from file
            } else {
                System.out.println("Failed to delete corrupted file. Please create new file manually.");
            }
        }
    }

    // Function that handles the exit message
    public void messageFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Au revoir! Till we meet again!");
        System.out.println("____________________________________________________________");
    }

    // Function that handles and echoes user input (this is maintained as not all inputs are tasks)
    public void userEchoedInput(String readerInput) {
        if (readerInput.equalsIgnoreCase("list")) // To list out tasks
        {
            System.out.println("____________________________________________________________");
            taskingsDisplay();
            System.out.println("____________________________________________________________");
        } else if (readerInput.equalsIgnoreCase("bye")) // To exit the chatbot program
        {
            messageFarewell();
        } else // Anything else, is assumed to be a new task to add
        {
            additionTaskings(readerInput);
        }
    }

    // Function to add tasks
    // No modification done to userEchoedInput function as not all inputs are tasks
    public void additionTaskings(String taskings) {
        // Display message based on the task type
        if (taskings.startsWith("todo")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            arrTasks[counterTasks] = new Task(taskings.substring(5).trim()); // 5 because of the word to do
        } else if (taskings.startsWith("deadline")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            String[] parts = taskings.substring(8).trim().split("/by"); // 8 because of the word deadline
            arrTasks[counterTasks] = new Task(parts[0].trim(), parts[1].trim());
            System.out.println("   " + "[" + arrTasks[counterTasks].taskStatus() + "]" +
                    "[" + arrTasks[counterTasks].completionStatus() + "]" + arrTasks[counterTasks].summaryStatus() +
                    " (by: " + arrTasks[counterTasks].formattedDeadline() + ")");
        } else if (taskings.startsWith("event")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            String[] parts = taskings.substring(5).trim().split("/from|/to"); // 5 because of the word event
            arrTasks[counterTasks] = new Task(parts[0].trim(), parts[1].trim(), parts[2].trim());
            System.out.println("   " + "[" + arrTasks[counterTasks].taskStatus() + "]" +
                    "[" + arrTasks[counterTasks].completionStatus() + "]" +
                    arrTasks[counterTasks].summaryStatus() +
                    " (from: " + arrTasks[counterTasks].timerStartStatus() + " to: " + arrTasks[counterTasks].timerEndStatus() + ")");
        } else {
            // Error handlings for missing task types
            System.out.println("____________________________________________________________");
            System.out.println(" SAD! Please start your task with 'todo', 'deadline', or 'event'.");
            System.out.println("____________________________________________________________");
            return;
        }

        System.out.println(" Now you have " + counterTasks + " task(s) in the list");
        System.out.println("____________________________________________________________");

        // Save tasks to file whenever the task list changes
        saveTasksToFile();
    }

    // Function to display tasks
    // No modification done to userEchoedInput function as not all inputs are tasks
    public void taskingsDisplay() {
        if (counterTasks == 0) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your task list is empty. Add tasks by simply typing them in."); // If there are no tasks, a message to guide the user
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are your tasks:"); // Display all tasks
            for (int i = 0; i < counterTasks; i++) {
                System.out.println(" " + (i + 1) + ". " + "[" + arrTasks[i].taskStatus() + "]" + "[" + arrTasks[i].completionStatus() + "]" + arrTasks[i].summaryStatus() +
                        (arrTasks[i].taskStatus().equals("E") ?
                                " (from: " + arrTasks[i].timerStartStatus() + " to: " + arrTasks[i].timerEndStatus() + ")" :
                                (arrTasks[i].taskStatus().equals("D") ? " (by: " + arrTasks[i].deadlineStatus() + ")" : "")));
            }
            System.out.println("____________________________________________________________");
        }
    }

    // Function to mark task as completed
    public void completionMark(int taskRecorder) {
        if (taskRecorder > 0 && taskRecorder <= counterTasks) // If there are tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println(" Another one in the bag! Well done!");
            arrTasks[taskRecorder - 1].completionMark(); // Mark as complete
            System.out.println("   " + "[" + arrTasks[taskRecorder - 1].taskStatus() + "]" + "[" + arrTasks[taskRecorder - 1].completionStatus() + "]" + arrTasks[taskRecorder - 1].summaryStatus() + "  " +
                    (arrTasks[taskRecorder - 1].taskStatus().equals("E") ?
                            " (from: " + arrTasks[taskRecorder - 1].timerStartStatus() + " to: " + arrTasks[taskRecorder - 1].timerEndStatus() + ")" :
                            (arrTasks[taskRecorder - 1].taskStatus().equals("D") ? " (by: " + arrTasks[taskRecorder - 1].deadlineStatus() + ")" : "")));
            System.out.println("____________________________________________________________");
        } else // Error handling: There are no tasks or invalid task number
        {
            System.out.println("____________________________________________________________");
            System.out.println(" Hmmm...I don't seem to have a task under this number");
            System.out.println("____________________________________________________________");
        }

        // Save tasks to file whenever task list changes
        saveTasksToFile();
    }

    // Function to unmark previously marked as completed task
    public void incompletionMark(int taskRecorder) {
        if (taskRecorder > 0 && taskRecorder <= counterTasks) // If there are tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println(" Oh dear, better get on it!");
            arrTasks[taskRecorder - 1].incompletionMark(); // Mark as incomplete
            System.out.println("   " + "[" + arrTasks[taskRecorder - 1].taskStatus() + "]" + "[" + arrTasks[taskRecorder - 1].completionStatus() + "]" + arrTasks[taskRecorder - 1].summaryStatus() + "  " +
                    (arrTasks[taskRecorder - 1].taskStatus().equals("E") ?
                            " (from: " + arrTasks[taskRecorder - 1].timerStartStatus() + " to: " + arrTasks[taskRecorder - 1].timerEndStatus() + ")" :
                            (arrTasks[taskRecorder - 1].taskStatus().equals("D") ? " (by: " + arrTasks[taskRecorder - 1].deadlineStatus() + ")" : "")));
            System.out.println("____________________________________________________________");
        } else // Error handling: There are no tasks or invalid task number
        {
            System.out.println("____________________________________________________________");
            System.out.println(" Hmmm...I don't seem to have a task under this number");
            System.out.println("____________________________________________________________");
        }

        // Save tasks to file whenever the task list changes
        saveTasksToFile();
    }

    // Function to delete a task
    public void taskDeleter(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= counterTasks) {
            System.out.println("____________________________________________________________");
            System.out.println(" Okay, I've removed this task:");
            System.out.println("   " + "[" + arrTasks[taskNumber - 1].taskStatus() + "]" + "[" + arrTasks[taskNumber - 1].completionStatus() + "]" + arrTasks[taskNumber - 1].summaryStatus() + "  " +
                    (arrTasks[taskNumber - 1].taskStatus().equals("E") ?
                            " (from: " + arrTasks[taskNumber - 1].timerStartStatus() + " to: " + arrTasks[taskNumber - 1].timerEndStatus() + ")" :
                            (arrTasks[taskNumber - 1].taskStatus().equals("D") ? " (by: " + arrTasks[taskNumber - 1].deadlineStatus() + ")" : "")));
            // Shift tasks in the array to fill the gap
            for (int i = taskNumber - 1; i < counterTasks - 1; i++) {
                arrTasks[i] = arrTasks[i + 1];
            }
            arrTasks[counterTasks - 1] = null; // Set the last element to null
            counterTasks--;
            System.out.println(" Now you have " + counterTasks + " task(s) in the list");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Hmmm...I don't seem to have a task under this number");
            System.out.println("____________________________________________________________");
        }

        // Save tasks to file whenever the task list changes
        saveTasksToFile();
    }
}

/**
 * Main method to run the Hari chatbot program.
 *
 * @param args Command-line arguments (not used in this program).
 */
public class Hari {
    public static void main(String[] args) {
        Scanner inputRead = new Scanner(System.in); // Scanner object to read and process user input
        HandlerBot hari = new HandlerBot(); // Create a new "Hari" chatbot (HandlerBot object)
        hari.messageGreeting(); // Call the messageGreeting function to greet the user

        String readerInput; // To store user input

        while (true) // Modified do-while to a while as I have now streamlined all the code in the main body and reduced the number of function calls
        {
            readerInput = inputRead.nextLine(); // Read and store user input inside readerInput variable

            if (readerInput.equalsIgnoreCase("bye")) { // If "bye" is written as an input, the chatbot exits with the farewell message
                break;
            } else if (readerInput.equalsIgnoreCase("list")) { // To list out tasks
                hari.taskingsDisplay();
            } else if (readerInput.startsWith("todo") || readerInput.startsWith("event")) {
                // Check if there is anything following "todo" or "event"
                if (readerInput.length() <= 5) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task description after 'todo', 'deadline', or 'event'.");
                    System.out.println("____________________________________________________________");
                } else {
                    hari.userEchoedInput(readerInput); // Else, it proceeds to call the user input processing function
                }
            } else if (readerInput.startsWith("deadline")) {
                // Check if there is anything following "deadline"
                if (readerInput.length() <= 9) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task description after 'todo', 'deadline', or 'event'.");
                    System.out.println("____________________________________________________________");
                } else {
                    hari.userEchoedInput(readerInput); // Else, it proceeds to call the user input processing function
                }
            } else if (readerInput.startsWith("unmark") || readerInput.startsWith("delete")) {
                // Check if there is anything following "unmark", "mark", or "delete"
                if (readerInput.length() <= 6) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task number after 'unmark', 'mark', or 'delete'.");
                    System.out.println("____________________________________________________________");
                } else {
                    try {
                        int taskIndexer = Integer.parseInt(readerInput.substring(6).trim());
                        if (taskIndexer > 0 && taskIndexer <= hari.counterTasks) {
                            if (readerInput.startsWith("unmark")) {
                                hari.incompletionMark(taskIndexer);
                            } else if (readerInput.startsWith("delete")) {
                                hari.taskDeleter(taskIndexer);
                            }
                        } else {
                            // Error handling: Invalid task number
                            System.out.println("____________________________________________________________");
                            System.out.println(" Hmmm...I don't seem to have a task under this number");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        // Error handling: Invalid input after "unmark" or "delete"
                        System.out.println("____________________________________________________________");
                        System.out.println(" SAD! Please provide a valid task number after 'unmark' or 'delete'.");
                        System.out.println("____________________________________________________________");
                    }
                }
            } else if (readerInput.startsWith("done")) {
                // Check if there is anything following "done"
                if (readerInput.length() <= 4) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task number after 'done'.");
                    System.out.println("____________________________________________________________");
                } else {
                    try {
                        int taskIndexer = Integer.parseInt(readerInput.substring(4).trim());
                        if (taskIndexer > 0 && taskIndexer <= hari.counterTasks) {
                            hari.completionMark(taskIndexer);
                        } else {
                            // Error handling: Invalid task number
                            System.out.println("____________________________________________________________");
                            System.out.println(" Hmmm...I don't seem to have a task under this number");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        // Error handling: Invalid input after "done"
                        System.out.println("____________________________________________________________");
                        System.out.println(" SAD! Please provide a valid task number after 'done'.");
                        System.out.println("____________________________________________________________");
                    }
                }
            } else if (readerInput.startsWith("find")) {
                String keyword = readerInput.substring(4).trim();
                hari.findTask(keyword);
            } else {
                // If none of the above conditions are met, it is assumed to be an unstructured input and echoes it back
                hari.userEchoedInput(readerInput);
            }
        }
        inputRead.close(); // Close the Scanner object to avoid resource leak
    }
}