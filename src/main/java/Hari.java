import java.util.Scanner;

// Class for the chatbot itself
class handlerbot {

    //Class Attributes
    private taskings[] arrtaskings; // To store tasks created by the user for easy retrieval and listing
    private int countertaskings; // Counter for assumption that there are no more than 100 tasks

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
        private String timerstart; // Start time for Event tasks
        private String timerend; // End time for Event tasks
        private String deadlinestat; // Deadline for Deadline tasks

        // Constructor for To Do tasks
        public taskings(String summary) {
            this.summary = summary; // Description of tasks
            this.completion = false; // To check if a task is or is not completed
            this.taskertype = "T"; // Set task type to T
        }

        // Constructor for Deadline tasks
        public taskings(String summary, String deadlinestat) {
            this.summary = summary;
            this.completion = false;
            this.taskertype = "D";
            this.deadlinestat = deadlinestat;
        }

        // Constructor for Event tasks
        public taskings(String summary, String timerstart, String timerend) {
            this.summary = summary;
            this.completion = false;
            this.taskertype = "E";
            this.timerstart = timerstart;
            this.timerend = timerend;
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

    }

    // Function that handles the greeting message
    public void messagegreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hey! I'm Hari!");
        System.out.println(" How may I be of service today?");
        System.out.println("____________________________________________________________");
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
        } else if (taskings.startsWith("event")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            String[] parts = taskings.substring(5).trim().split("/from|/to"); // 5 because of the word event
            arrtaskings[countertaskings] = new taskings(parts[0].trim(), parts[1].trim(), parts[2].trim());
        } else {
            // Error handling for missing task types
            System.out.println("____________________________________________________________");
            System.out.println(" SAD! Please start your task with 'todo', 'deadline', or 'event'.");
            System.out.println("____________________________________________________________");
            return;
        }

        System.out.println("   " + "[" + arrtaskings[countertaskings].taskstatus() + "]" + "[" + arrtaskings[countertaskings].completionstatus() + "]" + arrtaskings[countertaskings].summarystatus() +
                (arrtaskings[countertaskings].taskstatus().equals("E") ?
                        " (from: " + arrtaskings[countertaskings].timerstartstatus() + " to: " + arrtaskings[countertaskings].timerendstatus() + ")" :
                        (arrtaskings[countertaskings].taskstatus().equals("D") ? " (by: " + arrtaskings[countertaskings].deadlinestatus() + ")" : "")));

        countertaskings++;

        System.out.println(" Now you have " + countertaskings + " task(s) in the list");
        System.out.println("____________________________________________________________");
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
    }
}

// Main Class
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
            } else if (readerinput.startsWith("mark")) { // To mark a task as completed
                try {
                    // Extract the task number from user input
                    int taskindexer = Integer.parseInt(readerinput.substring(5).trim()); // 5 is because of the word mark
                    hari.incompletionmark(taskindexer);
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Invalid task number to unmark.");
                    System.out.println("____________________________________________________________");
                }
            } else if (readerinput.startsWith("delete")) { // To delete a task
                try {
                    // Extract task number from user input
                    int taskindexer = Integer.parseInt(readerinput.substring(7).trim()); // 7 is because of the word "delete"
                    hari.taskdeleter(taskindexer);
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" SAD! Invalid task number to delete.");
                    System.out.println("____________________________________________________________");
                }
            } else {
                hari.userechoedinput(readerinput); // Else, it proceeds to call the user input processing function
            }
        }
        hari.messagefarewell(); // Call the messagefarewell function to display farewell message and exit the program
    }
}