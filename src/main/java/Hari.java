import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Class for the chatbot itself
class handlerbot {

    //Class Attributes
    public taskings[] arrtaskings; // To store tasks created by the user for easy retrieval and listing
    public int countertaskings; // Counter for assumption that there are no more than 100 tasks

    // Class object
    public handlerbot() {
        arrtaskings = new taskings[100]; // Assumption that there are no more than 100 tasks
        countertaskings = 0; // Counter for the number of tasks
    }

    // Class for tasks (called taskings)
    public class taskings {
        private String summary; // Description of tasks
        private boolean completion; // To check if a task is or is not completed
        private String taskertype; // To identify the type of task
        private LocalDate deadline; // Add LocalDate for deadline
        private String timerstart; // Start time for Event tasks
        private String timerend; // End time for Event tasks
        private String deadlinestat; // Deadline for Deadline tasks

        // Constructor for To Do tasks
        public taskings(String summary) {
            this.summary = summary;
            this.completion = false;
            this.taskertype = "T";
            this.deadline = null;
            this.timerstart = null;
            this.timerend = null;
        }

        // Constructor for Deadline tasks
        public taskings(String summary, String deadlineStat) {
            this.summary = summary;
            this.completion = false;
            this.taskertype = "D";
            this.deadline = parseDeadline(deadlineStat);
            this.timerstart = null;
            this.timerend = null;
        }

        // Constructor for Event tasks
        public taskings(String summary, String startTime, String endTime) {
            this.summary = summary;
            this.completion = false;
            this.taskertype = "E";
            this.deadline = null;
            this.timerstart = startTime;
            this.timerend = endTime;
        }

        // Additional method to parse deadline string into LocalDate
        private LocalDate parseDeadline(String deadlinestat) {
            try {
                // Parse the date string into a LocalDate object
                return LocalDate.parse(deadlinestat, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                // Handle parsing exception (invalid date format)
                System.out.println("Error when parsing deadline. Please use the format yyyy-MM-dd.");
                return null;
            }
        }

        // To mark as completed
        public void completionmark() {
            this.completion = true;
        }

        // To unmark completed tasks
        public void incompletionmark() {
            this.completion = false;
        }

        // For displaying the X or [ ] depending on completion status
        public String completionstatus() {
            return (completion ? "X" : " "); // To display the X or [ ]
        }

        // For displaying task description
        public String summarystatus() {
            return summary;
        }

        // For displaying the task type
        public String taskstatus() {
            return taskertype;
        }

        // For displaying start time of event tasks
        public String timerstartstatus() {
            return timerstart;
        }

        // For displaying end time of event tasks
        public String timerendstatus() {
            return timerend;
        }

        // For displaying deadline of deadline tasks
        public String deadlinestatus() {
            return deadlinestat;
        }

        // Additional method to check if task description is empty
        public boolean summaryempty() {
            return summary.trim().isEmpty();
        }

        // Additional method to display deadline in a different format
        public String formattedDeadline() {
            return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

    }

    // File name and path
    private static final String FOLDER_PATH = "." + File.separator + "data";
    private static final String FILE_PATH = FOLDER_PATH + File.separator + "hari.txt";

    // Function to save tasks to a file
    private void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (int i = 0; i < countertaskings; i++) {
                taskings task = arrtaskings[i];
                writer.write(task.taskstatus() + " | " + (task.completion ? "1" : "0") + " | " +
                        task.summarystatus() + " | " +
                        (task.taskstatus().equals("D") ? task.deadlinestatus() : "") + " | " +
                        (task.taskstatus().equals("E") ? task.timerstartstatus() + " to " + task.timerendstatus() : "") + "\n");
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
                taskings task;
                if (type.equals("T")) {
                    task = new taskings(summary);
                } else if (type.equals("D")) {
                    task = new taskings(summary, parts[3]);
                } else if (type.equals("E")) {
                    task = new taskings(summary, parts[3], parts[4]);
                } else {
                    // Handle unrecognized task type
                    throw new CorruptedDataException("Unrecognized task type in the file.");
                }
                if (completion) {
                    task.completionmark();
                }
                arrtaskings[countertaskings++] = task;
            }
        } catch (IOException e) {
            // Handle file not found or other IO exceptions
            e.printStackTrace();
        }
    }

    // Custom exception class for handling corrupted data
    class CorruptedDataException extends Exception {
        public CorruptedDataException(String message) {
            super(message);
        }
    }

    // Function that handles the greeting message
    public void messagegreeting() {
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
                messagegreeting(); // Retry loading from file
            } else {
                System.out.println("Failed to delete corrupted file. Please create new file manually.");
            }
        }
    }

    // Function that handles the exit message
    public void messagefarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Au revoir! Till we meet again!");
        System.out.println("____________________________________________________________");
    }

    // Function that handles and echoes user input (this is maintained as not all inputs are tasks)
    public void userechoedinput(String readerinput) {
        if (readerinput.equalsIgnoreCase("list")) // To list out tasks
        {
            System.out.println("____________________________________________________________");
            taskingsdisplay();
            System.out.println("____________________________________________________________");
        } else if (readerinput.equalsIgnoreCase("bye")) // To exit the chatbot program
        {
            messagefarewell();
        } else // Anything else, is assumed to be a new task to add
        {
            additiontaskings(readerinput);
        }
    }

    // Function to add tasks
// No modification done to userechoedinput function as not all inputs are tasks
    public void additiontaskings(String taskings) {
        // Display message based on the task type
        if (taskings.startsWith("todo")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            arrtaskings[countertaskings] = new taskings(taskings.substring(5).trim()); // 5 because of the word to do
        } else if (taskings.startsWith("deadline")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            String[] parts = taskings.substring(8).trim().split("/by"); // 8 because of the word deadline
            arrtaskings[countertaskings] = new taskings(parts[0].trim(), parts[1].trim());
            System.out.println("   " + "[" + arrtaskings[countertaskings].taskstatus() + "]" +
                    "[" + arrtaskings[countertaskings].completionstatus() + "]" +
                    arrtaskings[countertaskings].summarystatus() +
                    " (by: " + arrtaskings[countertaskings].formattedDeadline() + ")");
        } else if (taskings.startsWith("event")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            String[] parts = taskings.substring(5).trim().split("/from|/to"); // 5 because of the word event
            arrtaskings[countertaskings] = new taskings(parts[0].trim(), parts[1].trim(), parts[2].trim());
            System.out.println("   " + "[" + arrtaskings[countertaskings].taskstatus() + "]" +
                    "[" + arrtaskings[countertaskings].completionstatus() + "]" +
                    arrtaskings[countertaskings].summarystatus() +
                    " (from: " + arrtaskings[countertaskings].timerstartstatus() + " to: " + arrtaskings[countertaskings].timerendstatus() + ")");
        } else {
            // Error handling for missing task types
            System.out.println("____________________________________________________________");
            System.out.println(" SAD! Please start your task with 'todo', 'deadline', or 'event'.");
            System.out.println("____________________________________________________________");
            return;
        }

        System.out.println(" Now you have " + countertaskings + " task(s) in the list");
        System.out.println("____________________________________________________________");

        // Save tasks to file whenever the task list changes
        saveTasksToFile();
    }

    // Function to display tasks
    // No modification done to userechoedinput function as not all inputs are tasks
    public void taskingsdisplay() {
        if (countertaskings == 0) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your task list is empty. Add tasks by simply typing them in."); // If there are no tasks, a message to guide the user
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Here are your tasks:"); // Display all tasks
            for (int i = 0; i < countertaskings; i++) {
                System.out.println(" " + (i + 1) + ". " + "[" + arrtaskings[i].taskstatus() + "]" + "[" + arrtaskings[i].completionstatus() + "]" + arrtaskings[i].summarystatus() +
                        (arrtaskings[i].taskstatus().equals("E") ?
                                " (from: " + arrtaskings[i].timerstartstatus() + " to: " + arrtaskings[i].timerendstatus() + ")" :
                                (arrtaskings[i].taskstatus().equals("D") ? " (by: " + arrtaskings[i].deadlinestatus() + ")" : "")));
            }
            System.out.println("____________________________________________________________");
        }
    }

    // Function to mark task completed
    public void completionmark(int taskrecorder) {
        if (taskrecorder > 0 && taskrecorder <= countertaskings) // If there are tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println(" Another one in the bag! Well done!");
            arrtaskings[taskrecorder - 1].completionmark(); // Mark as complete
            System.out.println("   " + "[" + arrtaskings[taskrecorder - 1].taskstatus() + "]" + "[" + arrtaskings[taskrecorder - 1].completionstatus() + "]" + arrtaskings[taskrecorder - 1].summarystatus() + "  " +
                    (arrtaskings[taskrecorder - 1].taskstatus().equals("E") ?
                            " (from: " + arrtaskings[taskrecorder - 1].timerstartstatus() + " to: " + arrtaskings[taskrecorder - 1].timerendstatus() + ")" :
                            (arrtaskings[taskrecorder - 1].taskstatus().equals("D") ? " (by: " + arrtaskings[taskrecorder - 1].deadlinestatus() + ")" : "")));
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

    // Function to unmark previously marked as completed task
    public void incompletionmark(int taskrecorder) {
        if (taskrecorder > 0 && taskrecorder <= countertaskings) // If there are tasks
        {
            System.out.println("____________________________________________________________");
            System.out.println(" Oh dear, better get on it!");
            arrtaskings[taskrecorder - 1].incompletionmark(); // Mark as incomplete
            System.out.println("   " + "[" + arrtaskings[taskrecorder - 1].taskstatus() + "]" + "[" + arrtaskings[taskrecorder - 1].completionstatus() + "]" + arrtaskings[taskrecorder - 1].summarystatus() + "  " +
                    (arrtaskings[taskrecorder - 1].taskstatus().equals("E") ?
                            " (from: " + arrtaskings[taskrecorder - 1].timerstartstatus() + " to: " + arrtaskings[taskrecorder - 1].timerendstatus() + ")" :
                            (arrtaskings[taskrecorder - 1].taskstatus().equals("D") ? " (by: " + arrtaskings[taskrecorder - 1].deadlinestatus() + ")" : "")));
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
    public void taskdeleter(int tasknumber) {
        if (tasknumber > 0 && tasknumber <= countertaskings) {
            System.out.println("____________________________________________________________");
            System.out.println(" Okay, I've removed this task:");
            System.out.println("   " + "[" + arrtaskings[tasknumber - 1].taskstatus() + "]" + "[" + arrtaskings[tasknumber - 1].completionstatus() + "]" + arrtaskings[tasknumber - 1].summarystatus() + "  " +
                    (arrtaskings[tasknumber - 1].taskstatus().equals("E") ?
                            " (from: " + arrtaskings[tasknumber - 1].timerstartstatus() + " to: " + arrtaskings[tasknumber- 1].timerendstatus() + ")" :
                            (arrtaskings[tasknumber - 1].taskstatus().equals("D") ? " (by: " + arrtaskings[tasknumber - 1].deadlinestatus() + ")" : "")));
            // Shift tasks in the array to fill the gap
            for (int i = tasknumber - 1; i < countertaskings - 1; i++) {
                arrtaskings[i] = arrtaskings[i + 1];
            }
            arrtaskings[countertaskings - 1] = null; // Set the last element to null
            countertaskings--;
            System.out.println(" Now you have " + countertaskings + " task(s) in the list");
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

// Main
public class Hari {
    public static void main(String[] args) {
        Scanner inputread = new Scanner(System.in); // Scanner object to read and process user input
        handlerbot hari = new handlerbot(); // Create a new "Hari" chatbot (handlerbot object)
        hari.messagegreeting(); // Call the messagegreeting function to greet the user

        String readerinput; // To store user input

        while (true) // Modified do-while to a while as I have now streamlined all the code in the main body and reduced the number of function calls
        {
            readerinput = inputread.nextLine(); // Read and store user input inside readerinput variable

            if (readerinput.equalsIgnoreCase("bye")) { // If "bye" is written as an input, the chatbot exits with the farewell message
                break;
            } else if (readerinput.equalsIgnoreCase("list")) { // To list out tasks
                hari.taskingsdisplay();
            } else if (readerinput.startsWith("todo") || readerinput.startsWith("event")) {
                // Check if there is anything following "todo" or "event"
                if (readerinput.length() <= 5) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task description after 'todo', 'deadline', or 'event'.");
                    System.out.println("____________________________________________________________");
                } else {
                    hari.userechoedinput(readerinput); // Else, it proceeds to call the user input processing function
                }
            } else if (readerinput.startsWith("deadline")) {
                // Check if there is anything following "deadline"
                if (readerinput.length() <= 9) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task description after 'todo', 'deadline', or 'event'.");
                    System.out.println("____________________________________________________________");
                } else {
                    hari.userechoedinput(readerinput); // Else, it proceeds to call the user input processing function
                }
            } else if (readerinput.startsWith("unmark") || readerinput.startsWith("delete")) {
                // Check if there is anything following "unmark", "mark", or "delete"
                if (readerinput.length() <= 6) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task number after 'unmark', 'mark', or 'delete'.");
                    System.out.println("____________________________________________________________");
                } else {
                    try {
                        int taskindexer = Integer.parseInt(readerinput.substring(6).trim());
                        if (taskindexer > 0 && taskindexer <= hari.countertaskings) {
                            if (readerinput.startsWith("unmark")) {
                                hari.incompletionmark(taskindexer);
                            } else if (readerinput.startsWith("mark")) {
                                hari.completionmark(taskindexer);
                            } else if (readerinput.startsWith("delete")) {
                                hari.taskdeleter(taskindexer);
                            }
                        } else {
                            // Error handling: Invalid task number
                            System.out.println("____________________________________________________________");
                            System.out.println(" SAD! Invalid task number.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        // Error handling: Invalid task number format
                        System.out.println("____________________________________________________________");
                        System.out.println(" SAD! Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                }
            } else if (readerinput.startsWith("mark")) {
                // Check if there is anything following "unmark", "mark", or "delete"
                if (readerinput.length() < 5) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Missing task number after 'unmark', 'mark', or 'delete'.");
                    System.out.println("____________________________________________________________");
                } else {
                    try {
                        int taskindexer = Integer.parseInt(readerinput.substring(5).trim());
                        if (taskindexer > 0 && taskindexer <= hari.countertaskings) {
                            if (readerinput.startsWith("unmark")) {
                                hari.incompletionmark(taskindexer);
                            } else if (readerinput.startsWith("mark")) {
                                hari.completionmark(taskindexer);
                            } else if (readerinput.startsWith("delete")) {
                                hari.taskdeleter(taskindexer);
                            }
                        } else {
                            // Error handling: Invalid task number
                            System.out.println("____________________________________________________________");
                            System.out.println(" SAD! Invalid task number.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        // Error handling: Invalid task number format
                        System.out.println("____________________________________________________________");
                        System.out.println(" SAD! Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" SAD! I'm not sure what you mean. Please enter a valid command.");
                System.out.println("____________________________________________________________");
            }
        }
        hari.messagefarewell(); // Call the messagefarewell function to display farewell message and exit the program
    }
}