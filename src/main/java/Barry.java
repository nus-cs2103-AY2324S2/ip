import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Barry {

    // A class for elements in the taskList, with a name and an indicator of whether the task is marked
    private abstract static class Task{
        private final String taskType;
        private final String taskName;
        private boolean isDone;

        // Constructor for the Task class, initialised with the name of the task
        public Task(String name, String type) {
            this.taskType = type;
            this.taskName = name;
            this.isDone = false;
        }

        // Allows the user to modify the status of the task
        public void modifyStatus(boolean status) {
            this.isDone = status;
        }

        // Returns the name of the task
        public final String getName() {
            return this.taskName;
        }

        // Returns the status of the task
        public final boolean getStatus() {
            return this.isDone;
        }

        // Returns the type of the task
        public final String getTaskType() { return this.taskType; }

        // Returns details about the relevant timings of the task, to be defined in the subclasses
        abstract public String detailsAsString();
    }

    // A class that inherits from the Task class, for creating ToDos
    // (tasks without any date/time attached to them)
    private static class ToDo extends Task {
        // Constructor for ToDos, initialised with the name of the task
        public ToDo(String name) {
            super(name, "T");
        }

        // Method which returns details of the object as a String
        @Override
        public final String detailsAsString() {
            return "[T][" + (this.getStatus() ? "X" : " ") + "] " + this.getName();
        }
    }

    // A class that inherits from the Task class, for creating Deadlines
    // (tasks that need to be done before a specific date/time)
    private static class Deadline extends Task {
        private final String deadlineDateTime;

        // Constructor for Deadlines, initialised with the name of the task and the deadline
        public Deadline(String name, String deadline) {
            super(name, "D");
            this.deadlineDateTime = deadline;
        }

        // Method which returns details of the object as a String
        @Override
        public final String detailsAsString() {
            String basicInfo = "[D][" + (this.getStatus() ? "X" : " ") + "] " + this.getName();
            return basicInfo + " (by: " + deadlineDateTime + ")";
        }
    }

    // A class that inherits from the Task class, for creating Events
    // (tasks that start and end at a specific date/time)
    private static class Event extends Task {
        private final String eventStartDateTime;
        private final String eventEndDateTime;

        // Constructor for Events, initialised with the name of the task, as well as the start and end of the event
        public Event(String name, String start, String end) {
            super(name, "E");
            this.eventStartDateTime = start;
            this.eventEndDateTime = end;
        }

        // Method which returns details of the object as a String
        @Override
        public final String detailsAsString() {
            String basicInfo = "[E][" + (this.getStatus() ? "X" : " ") + "] " + this.getName();
            return basicInfo + " (from: " + eventStartDateTime + " to: " + eventEndDateTime + ")";
        }
    }

    // ArrayList for storing user-added tasks.
    private static final ArrayList<Task> taskList = new ArrayList<>();

    // Greets the user
    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Greetings! I'm Barry.\n" + "\t How could I assist you?");
        System.out.println("\t [Type help for a list of available commands]");
        System.out.println("\t____________________________________________________________\n");
    }

    // Exits the program with a message
    private static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye, see you next time! :)");
        System.out.println("\t____________________________________________________________\n");
    }

    // List all available commands
    private static void help() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here's a list of available commands:\n");
        System.out.println("\t   help                                           List all available commands");
        System.out.println("\t   bye                                            Quit");
        System.out.println("\t   list                                           List all tasks in your list");
        System.out.println("\t   mark [x]                                       Mark task number x in your list");
        System.out.println("\t   unmark [x]                                     Unmark task number x in your list");
        System.out.println("\t   todo [task name]                               Add a ToDo to your list");
        System.out.println("\t   deadline [task name] /by [deadline]            Add a Deadline to your list");
        System.out.println("\t   event [task name] /from [start] /to [end]      Add an Event to your list");
        System.out.println("\t____________________________________________________________\n");
    }

    // Task list mode, allows user to enter tasks into a list, and print all tasks in the list
    // currently with "list", exits with "bye"
    private static void taskListMode() {
        Scanner scanner = new Scanner(System.in);
        final Pattern markPattern = Pattern.compile("^mark\\s(\\d+)$");
        final Pattern unmarkPattern = Pattern.compile("^unmark\\s(\\d+)$");
        final Pattern todoPattern = Pattern.compile("^todo\\s(.+)$");
        final Pattern deadlinePattern = Pattern.compile("^deadline\\s(.+)\\s/by\\s(.+)$");
        final Pattern eventPattern = Pattern.compile("^event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)$");
        // Execution loop
        while (true) {
            // Command line input, stripped of whitespaces
            String inputText = scanner.nextLine().strip();
            if (inputText.equals("bye")) {
                // Exit task list mode
                break;
            } else if (inputText.equals("help")) {
                // List all available commands
                help();
            } else if (inputText.equals("list")) {
                // List all tasks in taskList currently, or return a message indicating that there are none
                int taskListSize = taskList.size();
                System.out.println("\t____________________________________________________________");
                if (taskListSize == 0) {
                    // List is empty
                    System.out.println("\t Looks like there aren't any tasks in your list yet... let's add some!");
                } else {
                    // List has items
                    System.out.println("\t Let's take a look at the tasks in your list:");
                    // Print list of tasks
                    for (int i = 0; i < taskListSize; i++) {
                        Task task = taskList.get(i);
                        System.out.println("\t " + (i + 1) + "." + task.detailsAsString());
                    }
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (inputText.startsWith("mark")) {
                // Mark indicated task
                Matcher markMatcher = markPattern.matcher(inputText);
                System.out.println("\t____________________________________________________________");
                if (markMatcher.matches()){
                    // Valid format
                    int taskIndex = Integer.parseInt(markMatcher.group(1)) - 1;
                    try {
                        Task task = taskList.get(taskIndex);
                        if (!task.getStatus()) {
                            task.modifyStatus(true);
                            System.out.println("\t Nice, I have marked this task as done:");
                            System.out.println("\t   [" + task.getTaskType() + "][X] " + task.getName());
                            System.out.println("\t Great job! :)");
                        } else {
                            System.out.println("\t Looks like this task was already marked...");
                            System.out.println("\t [In order to unmark a task, use the 'unmark' command instead]");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        // Task number is out of range
                        System.out.println("\t Oops, I can't find a task in your list with that number! :(");
                        System.out.println("\t [Make sure the task number is correct and try again]");
                    }
                } else {
                    // Invalid format
                    System.out.println("\t I see you're trying to mark a task, but it's in the wrong format...");
                    System.out.println("\t [Type 'help' if you're unsure of the correct format]");
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (inputText.startsWith("unmark")){
                // Unmark indicated task
                Matcher unmarkMatcher = unmarkPattern.matcher(inputText);
                System.out.println("\t____________________________________________________________");
                if (unmarkMatcher.matches()) {
                    // Valid format
                    int taskIndex = Integer.parseInt(unmarkMatcher.group(1)) - 1;
                    try {
                        Task task = taskList.get(taskIndex);
                        if (task.getStatus()) {
                            task.modifyStatus(false);
                            System.out.println("\t Alright, i have marked this task as not done yet:");
                            System.out.println("\t   [" + task.getTaskType() + "][ ] " + task.getName());
                        } else {
                            System.out.println("\t Looks like this task was already unmarked...");
                            System.out.println("\t [In order to mark a task, use the 'mark' command instead]");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        // Task number is out of range
                        System.out.println("\t Oops, I can't find a task in your list with that number! :(");
                        System.out.println("\t [Make sure the task number is correct and try again]");
                    }
                } else {
                    // Invalid format
                    System.out.println("\t I see you're trying to unmark a task, but it's in the wrong format...");
                    System.out.println("\t [Type 'help' if you're unsure of the correct format]");
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (inputText.startsWith("todo")) {
                // ToDos
                Matcher todoMatcher = todoPattern.matcher(inputText);
                System.out.println("\t____________________________________________________________");
                if (todoMatcher.matches()) {
                    // Valid format
                    String todoName = todoMatcher.group(1);
                    taskList.add(new ToDo(todoName));
                    int listSize = taskList.size();
                    String plural = listSize > 1 ? "s" : "";
                    System.out.println("\t Gotcha, i've added the ToDo:");
                    System.out.println("\t   [T][ ] " + todoName);
                    System.out.println("\t You've now got " + listSize + " task" + plural + " in your list!");
                } else {
                    // Invalid format
                    System.out.println("\t I see you're trying to add a ToDo, but it's in the wrong format...");
                    System.out.println("\t [Type 'help' if you're unsure of the correct format]");
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (inputText.startsWith("deadline")) {
                // Deadlines
                Matcher deadlineMatcher = deadlinePattern.matcher(inputText);
                System.out.println("\t____________________________________________________________");
                if (deadlineMatcher.matches()) {
                    // Valid format
                    String deadlineName = deadlineMatcher.group(1);
                    String deadlineDateTime = deadlineMatcher.group(2);
                    taskList.add(new Deadline(deadlineName, deadlineDateTime));
                    int listSize = taskList.size();
                    String plural = listSize > 1 ? "s" : "";
                    System.out.println("\t Gotcha, i've added the Deadline:");
                    System.out.println("\t   [D][ ] " + deadlineName);
                    System.out.println("\t You've now got " + listSize + " task" + plural + " in your list!");
                } else {
                    // Invalid format
                    System.out.println("\t I see you're trying to add a Deadline, but it's in the wrong format...");
                    System.out.println("\t [Type 'help' if you're unsure of the correct format]");
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (inputText.startsWith("event")) {
                // Events
                Matcher eventMatcher = eventPattern.matcher(inputText);
                System.out.println("\t____________________________________________________________");
                if (eventMatcher.matches()) {
                    // Valid format
                    String eventName = eventMatcher.group(1);
                    String eventStartDateTime = eventMatcher.group(2);
                    String eventEndDateTime = eventMatcher.group(3);
                    taskList.add(new Event(eventName, eventStartDateTime, eventEndDateTime));
                    int listSize = taskList.size();
                    String plural = listSize > 1 ? "s" : "";
                    System.out.println("\t Gotcha, i've added the Event:");
                    System.out.println("\t   [E][ ] " + eventName);
                    System.out.println("\t You've now got " + listSize + " task" + plural + " in your list!");
                } else {
                    // Invalid format
                    System.out.println("\t I see you're trying to add an Event, but it's in the wrong format...");
                    System.out.println("\t [Type 'help' if you're unsure of the correct format]");
                }
                System.out.println("\t____________________________________________________________\n");
            } else {
                // No such command
                System.out.println("\t____________________________________________________________");
                System.out.println("\t I'm sorry, I don't understand what that means... >_<");
                System.out.println("\t [Type 'help' for a list of available commands]");
                System.out.println("\t____________________________________________________________\n");
            }
        }
    }

    public static void main(String[] args) {
        greet();
        taskListMode();
        exit();
    }
}