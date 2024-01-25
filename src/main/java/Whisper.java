import java.util.Scanner;

public class Whisper {
    static String line = "-------------------------------------------------\n";
    static String name = "Whisper";
    static String welcomeMsg = "Hello! I'm " + name + " , your personal chatbot!\n" +
            "What can I do for you?\n";
    static String byeMsg = line + "Bye. Hope to see you soon!\n" + line;
    static Task[] taskList; // store tasks in array
    static int count;

    // Main method
    public static void main(String[] args) {
        System.out.println(line + welcomeMsg + line);

        Scanner sc = new Scanner(System.in);
        taskList = new Task[100];
        count = 0;

        while (true) {
            // read user input
            System.out.println("Enter your input:");
            String input = sc.nextLine();

            try {
                // break if user exists the bot
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(byeMsg);
                    break;
                    // show user's list
                } else if (input.equalsIgnoreCase("list")) {
                    getTasks();
                    // store user list and display them back
                } else if (input.toLowerCase().startsWith("mark")) {
                    // get the index of task
                    int index = getIndex(input);
                    printTaskDone(index);
                } else if (input.toLowerCase().startsWith("unmark")) {
                    int index = getIndex(input);
                    printTaskUndone(index);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else {
                    throw WhisperException.unknownCommand();
                }
            } catch (WhisperException e) {
                System.out.println(e.getMessage());
            }

        }
        sc.close();
    }

    // insert task (todo) into the task list
    public static void addTodo(String description) throws WhisperException {
        try {
            checkTaskListFull();

            if (description.trim().equalsIgnoreCase("todo")) {
                // without description
                throw WhisperException.invalidTodo();
            }

            taskList[count] = new Todo(description);
            System.out.println(line + "Got it. I've added this task:\n" + taskList[count].toString() + "\n" + printTaskCount(count) + "\n" + line);
            count++;

        } catch (NumberFormatException e) {
            //System.out.println("Sorry, list is full. Can't add anymore.");
            // handle unexpected exception
            throw new WhisperException("Unexpected error occurred.");
        }
    }

    // add new Event
    public static void addEvent(String description) throws WhisperException {
        try {
            checkTaskListFull(); // check if the list is full before adding

            String[] eventStr = description.split("/from", 2); // create two substring after "from"
            // check if input format is correct
            if (eventStr.length < 2) {
                throw WhisperException.invalidEvent();
            }

            // check description format
            String[] descr = eventStr[0].split(" ", 2);
            // Check if it split two parts and the 2nd part is not empty
            if (descr.length < 2 || descr[1].trim().isEmpty()) {
                throw WhisperException.invalidEvent();
            }

            // extract event name
            String eventName = descr[1];
            String[] time = eventStr[1].split("/to", 2); // create two substring & get from to timing
            // Check if it splits in two parts
            if (time.length < 2) {
                throw WhisperException.invalidEvent();
            }

            // extract time
            String from = time[0].trim();
            String to = time[1].trim();
            // Check if "from" and "to" are not empty
            if (from.isEmpty() || to.isEmpty()) {
                throw WhisperException.invalidEvent();
            }

            // add new event to task list
            taskList[count] = new Event(eventName, from, to);
            System.out.println(line + "Got it. I've added this task:\n" + taskList[count].toString() + "\n" + printTaskCount(count) + "\n" + line);
            count++;
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }

    // add deadline to tasklist
    public static void addDeadline(String description) {
        try {
            checkTaskListFull(); // check if list is full

            String[] deadlineStr = description.split("/by", 2);
            if (deadlineStr.length < 2) {
                throw WhisperException.invalidDeadline();
            }

            String[] descr = deadlineStr[0].split(" ", 2);
            if (descr.length < 2) {
                throw WhisperException.invalidDeadline();
            }

            String deadlineName = descr[1];
            String by = deadlineStr[1].trim();
            // check if description or time is empty
            if (deadlineName.equals("") | by.equals("")) {
                throw WhisperException.invalidDeadline();
            }

            // add new deadline to task list
            taskList[count] = new Deadline(deadlineName, by);
            System.out.println(line + "Got it. I've added this task:\n" + taskList[count].toString() + "\n" + printTaskCount(count) + "\n" + line);
            count++;
        } catch (WhisperException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    // display text when task is marked done
    public static void printTaskDone(int index) throws WhisperException {
        try {
            // check index bound
            if (index >= 0 && index < count) {
                Task t = taskList[index];
                t.markAsDone();
                System.out.println(line + "Nice! I've marked this task as done:\n" + t.toString() + "\n" + line);
            } else {
                throw WhisperException.invalidTaskID();
            }
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }

    // display text when task mark undone
    public static void printTaskUndone(int index) throws WhisperException {
        try {
            // check index bound
            if (index >= 0 && index < count) {
                Task t = taskList[index];
                t.markAsUndone();
                System.out.println(line + "Nice! I've marked this task as not done:\n" + t.toString() + "\n" + line);
            } else {
                throw WhisperException.invalidTaskID();
            }
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String printTaskCount(int count) {
        return "Now you have " + (count + 1) + " tasks in the list.";
    }

    // display task list
    public static void getTasks() {
        System.out.println(line + "Here are your task list:\n");

        for (int i = 0; i < count; i++) {
            Task currentTask = taskList[i];
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        System.out.println(line);
    }

    // get task id based on command
    public static int getIndex(String input) throws WhisperException {
        try {
            return Integer.parseInt(input.substring(input.indexOf(" ") + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            throw WhisperException.unknownCommand();
        }
    }

    // check if task list has more than 100 tasks
    public static void checkTaskListFull() throws WhisperException {
        if (count >= 100) {
            throw WhisperException.taskFull();
        }
    }
}