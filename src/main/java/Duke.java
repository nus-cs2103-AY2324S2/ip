import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected int index;

        public Task(String description, int index) {
            this.description = description;
            this.index = index;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }

        public String commandMark() {
            String response = this.isDone == true
                    ? "Task " + (this.index + 1) + " is already done! Yay!\n"
                    : "Nice! I've marked this task as done:\n";
            this.isDone = true;
            return response + "  " + toString();
        }

        public String commandUnmark() {
            String response = this.isDone == false
                    ? "Task " + (this.index + 1) + " is not done yet!\n"
                    : "OK, I've marked this task as undone:\n";
            this.isDone = false;
            return response + "  " + toString();
        }

        public void editIndex(int x) {
            this.index = x;
        }

        public void editDescription(String input) {
            this.description = input;
        }

    }

    public static class ToDo extends Task {
        public ToDo(String description, int index) {
            super(description, index);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected String by;
        public Deadline(String description, String by, int index) {
            super(description, index);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String start;
        protected String end;

        public Event(String description, String start, String end, int index) {
            super(description, index);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(from: " + start +  " to: " + end + ")";
        }
    }

    private static String logo = " _______  __                       __ \n"
            + "|     __||__|.-----..-----..---.-.|  |\n"
            + "|__     ||  ||  _  ||     ||  _  ||  |\n"
            + "|_______||__||___  ||__|__||___._||__|\n"
            + "             |_____|                  \n";
    private static String div = "\n" + "~~**~~";
    private static Scanner scanner = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int index = 0;



    /**
     * Checks with the user if the input is a typo of a command.
     *
     * @param input Input collected from the user.
     * @param command Command to check.
     * @return True if input is a typo of the command.
     */
    public static boolean checkCommandTypo(String input, String command) {
        if(!input.equals(command)) {
            signalSays("Did you mean '"+ command + "'? (y/n)");
            String isCommandCheck = scanner.nextLine();
            if(isCommandCheck.equals("y")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check if the input is a permutation of the original.
     *
     * @param input Input collected from the user.
     * @param original String to compare the input to.
     * @return True if input is a permutation of original.
     */
    public static boolean isPermutationMatch(String input, String original) {
        // Check if user input is a permutation match
        char[] userInputArray = input.toCharArray();
        char[] originalArray = original.toCharArray();

        // Sort the arrays to compare
        java.util.Arrays.sort(userInputArray);
        java.util.Arrays.sort(originalArray);

        return java.util.Arrays.equals(userInputArray, originalArray);
    }

    /**
     * Adds input to the list.
     *
     * @param input Input collected from the user.
     */
    public static void taskAdded(String input) {
        taskList[index] = new Task(input, index);
        index += 1;
        signalSays("Added: " + input);
    }

    public static void commandAddTask(String type, String input) throws DukeException {
        if (input == "") {
            throw new DukeException("Looks like you haven't entered a task description!");
        }
        if (type.equals("todo")) {
            taskList[index] = new ToDo(input, index);
        } else {
            String command[] = input.split("/");
            if (type.equals("deadline")) {
                if (command.length < 2) {
                    throw new DukeException("Looks like you haven't added a deadline!");
                }
                String deadline = command[1] != null && command[1].length() > 3 ? command[1].substring(3) : command[1];
                taskList[index] = new Deadline(command[0], deadline, index);
            } else if (type.equals("event")){
                if (command.length < 3) {
                    throw new DukeException("Looks like you haven't added a start or end time!");
                }
                String start = command[1] != null && command[1].length() > 5 ? command[1].substring(5): command[1];
                String end = command[2] != null && command[2].length() > 3 ? command[2].substring(3) : command[2];
                taskList[index] = new Event(command[0], start, end, index);
            } else {
                taskList[index] = new Task(input, index);
            }
        }
        taskAdded((taskList[index]));
    }

    public static void taskAdded(Task t) {
        index += 1;
        signalSays("Got it! I've added this task to your list: \n"
                + "  " + t.toString() + "\n"
                + "Now you have " + (t.index + 1) + (t.index == 0 ? " task" : " tasks") + " in the list.");
    }


    /**
     * Prints the list of inputs collected from the user.
     *
     */
    public static void commandList() {
        System.out.println(div);
        System.out.println("Here is your tasklist!");
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }
        System.out.println(div);
    }

    /**
     * Deletes the task from the list and shifts the subsequent tasks forward.
     *
     * @param t The task to be deleted.
     * @throws DukeException
     */
    public static void commandDelete(Task t) throws DukeException {
        if (index == 0) {
            throw new DukeException("Looks like there's nothing here to remove. Better get on those tasks!");
        }
        if (t.index >= 0 && t.index <= index) {
            // Shift the remaining elements up
            for (int i = t.index; i <= index - 1; i++) {
                // Adjust the index of each element
                taskList[i] = taskList[i + 1];
                taskList[i].index = i;
            }
            index -= 1;
            signalSays("Noted, I've deleted this task from your list: \n"
                    + "  " + t.toString() + "\n"
                    + "Now you have " + (index) + (index == 0 ? " task" : " tasks") + " in the list.");
        } else {
            throw new DukeException("I'd say shoot for the stars but in this case there are only "
                    + (index - 1) + ((index - 1) == 1 ? " item" : " items") + " in this list");
        }
    }

    /**
     * Handles negative and out-of-bounds inputs for deleting tasks.
     *
     * @param x
     * @throws DukeException
     */
    public static void commandDeleteInvalid(int x) throws DukeException {
        if (x < 0) {
            throw new DukeException("Negative numbers might exist in maths but not in this list!");
        }
        if (x > 100) {
            throw new DukeException("Oops! You only have " + (index) + (index == 0 ? " task" : " tasks") + ". Better get on it then!");
        }
    }

    public static void commandBlah() throws DukeException {
        throw new DukeException("All words are made up, but this one seems more nonsensical than usual. Try something else!");
    }

    public static void commandSomethingelse() throws DukeException {
        throw new DukeException("Haha, very funny. Nice try my guy!");
    }

    public static void commandHelp() {
        signalSays("Note: Entering any text besides the following creates a new generic task. \n" +
                "The round brackets indicate you can enter any text, square brackets indicate you should enter a number, without the brackets. \n" +
                "\nCREATING TASKS: \n" +
                "* todo () -- creates a To Do task, which has no deadline. \n" +
                "* deadline () \\by () -- creates a Deadline task, indicate its deadline after '\\by'. \n" +
                "* event () \\from () \\to () -- creates an Event task, indicate its start and end after '\\from' and '\\to'. \n" +
                "\nCOMMANDS: \n" +
                "* list -- prints a numbered list of the tasks created, in input order.\n" +
                "* mark [] -- marks the task at index [] as completed. \n" +
                "* unmark [] -- marks the task at index [] as uncompleted. \n" +
                "* delete [] -- removes the task at index [] from the list.\n" +
                "* bye -- exits the program.");
    }

    /**
     * Prints Signal's response enclosed in the dividers.
     *
     * @param response The message that is printed.
     */
    public static void signalSays(String response) {
        System.out.println(div);
        System.out.println(response);
        System.out.println(div);
    }


    public static void main(String[] args) {
        System.out.println("Hello! My name is\n" + logo);
        System.out.println("How can I help?");
        System.out.println("Enter 'help' to see the list of commands available :D");
        System.out.println(div);


        while(true) {
            String userInput = scanner.nextLine();
            String[] inputArray = userInput.split(" ");

            if (userInput.equals("bye")) {
                // Exit program
                System.out.println(div);
                break;
            } else if (userInput.equals("")) {
                // input is blank
                signalSays("Brevity is the soul of wit, but you have to tell me something still!");
            } else if (inputArray.length == 2 && inputArray[0].equals("mark")) {
                // Mark item at index as done
                int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                signalSays(taskList[itemIndex].commandMark());
            } else if (inputArray.length == 2 && inputArray[0].equals("unmark")) {
                // Mark item at index as done
                int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                signalSays(taskList[itemIndex].commandUnmark());
            } else if (inputArray.length == 2 && (isPermutationMatch(inputArray[0], "mark") || isPermutationMatch(inputArray[0], "unmark"))) {
                // check typo of "mark" or "unmark"
                if (checkCommandTypo(inputArray[0], "mark")) { // command mark typo
                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                    signalSays(taskList[itemIndex].commandMark());
                } else if (checkCommandTypo(inputArray[0], "unmark")) { // command unmark typo
                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                    signalSays(taskList[itemIndex].commandUnmark());
                } else {
                    signalSays("Do you want to add '" + userInput + "'? (y/n)");
                    String addCommandCheck = scanner.nextLine();
                    if (addCommandCheck.equals("n")) {
                        signalSays("What else can I help you with?");
                    } else if (addCommandCheck.equals("y")) {
                        taskAdded(userInput);
                    }
                }
            } else if (userInput.equals("list")) {
                // command list
                if (taskList[0] == null) {
                    signalSays("Oops, looks like you haven't added any tasks!");
                } else {
                    commandList();
                }
            } else if (isPermutationMatch(userInput, "list")) {
                // check typo of command list
                if (checkCommandTypo(userInput, "list")) {
                    commandList();
                } else {
                    signalSays("Do you want to add " + userInput + "? (y/n)");
                    String addCommandCheck = scanner.nextLine();
                    if(addCommandCheck.equals("n")) {
                        signalSays("What else can I help you with?");
                    } else if(addCommandCheck.equals("y")) {
                        taskAdded(userInput);
                    }
                }
            } else if (inputArray[0].equals("todo") || inputArray[0].equals("deadline") || inputArray[0].equals("event")) {
                String task = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                try {
                    commandAddTask(inputArray[0], task);
                } catch (DukeException e) {
                    signalSays("WHOOPSIE! " + e.getMessage());
                }
            } else if (userInput.equals("blah")) {
                try {
                    commandBlah();
                } catch (DukeException e) {
                    signalSays(e.getMessage());
                }
            } else if (userInput.equals("something else")) {
                try {
                    commandSomethingelse();
                } catch (DukeException e) {
                    signalSays(e.getMessage());
                }
            } else if (inputArray.length == 2 && inputArray[0].equals("delete")) {
                try {
                    int itemIndex = Integer.parseInt(inputArray[1]) - 1;
                    if (itemIndex > index - 1) {
                        commandDeleteInvalid(101);
                    }
                    if (itemIndex < 0) {
                        commandDeleteInvalid(-1);
                    }
                    commandDelete(taskList[itemIndex]);
                } catch (DukeException e) {
                    signalSays(e.getMessage());
                }
            } else if (userInput.equals("help")) {
                commandHelp();
            } else {
                taskAdded(userInput);
            }
        }

        System.out.println("Bye! Hope you come back soon :D");
        System.out.println(div);

    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}