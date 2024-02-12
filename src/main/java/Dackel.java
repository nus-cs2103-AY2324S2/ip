import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Dackel {
    /** Command aliases */
    private static final String QUIT = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";

    /** Command flags */
    private static final String COMMAND = "command";
    private static final String BODY = "body";
    private static final String BY = "by";
    private static final String FROM = "from";
    private static final String UNTIL = "to";

    /** Other String constants */
    private static final String NAME = "DACKEL";
    private static final String LOGO = " ____    __    ___  _  _  ____  __   \n"
        + "(  _ \\  /__\\  / __)( )/ )( ___)(  )  \n"
        + " )(_) )/(__)\\( (__  )  (  )__)  )(__ \n"
        + "(____/(__)(__)\\___)(_)\\_)(____)(____) ";
    private static final String LINE = "____________________________________________________________";
    private static final String VERSION = "Version 0.1";

    /** Scanner for receiving user input*/
    private static final Scanner SCANNER = new Scanner(System.in);

    /** Memory for storing task and instruction data */
    private static ArrayList<Task> storedTasks = new ArrayList<>();
    private static int numberOfTasks = 0;
    private static HashMap<String, String> commandArgs = new HashMap<>();

    /**
     * Simulates Dackel speaking to the user ala a chatroom interface
     * 
     * @param string Message for Dackel to send
     */
    private static void speak(String message) {
        // TODO: make this take in String[] args
        System.out.println(NAME + ": " + message);
    }

    // TODO: perhaps collapse the three add task methods into a single method

    /**
     * Adds a Todo to storedTasks
     * 
     * @param taskName name of task to be added
     */
    private static void addTodo(String taskName) {
        Todo newTask = new Todo(taskName);
        storedTasks.add(newTask);
        numberOfTasks++;
        speak("added the following task to your list!\n " + newTask.toString());
        speak("your list now has " + String.valueOf(numberOfTasks) + " tasks.");
    }

    /**
     * Adds a Deadline to storedTasks
     * 
     * @param taskName name of task to be added
     * @param dueTime due date/time of task as a String
     */
    private static void addDeadline(String taskName, String dueTime) {
        Deadline newTask = new Deadline(taskName, dueTime);
        storedTasks.add(newTask);
        numberOfTasks++;
        speak("added the following task to your list!\n " + newTask.toString());
        speak("your list now has " + String.valueOf(numberOfTasks) + " tasks.");
    }

    /**
     * Adds an Event to storedTasks
     * 
     * @param taskName name of task to be added
     * @param startTime starting date/time of task as a String
     * @param endTime ending date/time of task as a String
     */
    private static void addEvent(String taskName, String startTime, String endTime) {
        Event newTask = new Event(taskName, startTime, endTime);
        storedTasks.add(newTask);
        numberOfTasks++;
        speak("added the following task to your list!\n " + newTask.toString());
        speak("your list now has " + String.valueOf(numberOfTasks) + " tasks.");
    }

    /**
     * Removes task with specified index from storedTasks
     * 
     * @param index index of the element to be removed
     */
    private static void deleteTask(int index) {
        Task taskToBeRemoved = storedTasks.get(index);
        String s = "the following task will be deleted from your list:\n ";
        s += taskToBeRemoved.toString();
        speak(s);
        speak("are you sure you want to do this? [Y/n]: ");
        String confirmation = receiveInput();
        if (confirmation.equals("Y")) {
            storedTasks.remove(index);
            numberOfTasks--;
            speak("task " + taskToBeRemoved.toString() + " successfully deleted.");
            speak("you now have " + String.valueOf(numberOfTasks) + " tasks on your list.");
        }
        else {
            speak("task was not deleted.");
        }
    }

    /**
     * Lists all tasks in storedTasks. Displays a message if storedTasks is empty
     */
    private static void listTasks() {
        if (numberOfTasks == 0) {
            speak("you have no tasks on your list!");
            return;
        }
        String s = "";
        for (int i = 0; i < numberOfTasks; i++) {
            s += "\n" + String.format(" %2d " + storedTasks.get(i).toString(), i + 1);
        }
        speak(s);
    }

    /**
     * Marks the specified task as done
     * 
     * @param index index of task in storedTasks
     */
    private static void markTask(int index) {
        storedTasks.get(index).mark();
        speak("i've marked the following task as done!\n " + storedTasks.get(index).toString());
    }

    /**
     * Marks the specified task as not done
     * 
     * @param index index of task in storedTasks
     */
    private static void unmarkTask(int index) {
        storedTasks.get(index).unmark();
        speak("i've unmarked the following task. do it soon, please!\n " + storedTasks.get(index).toString());
    }

    /**
     * Prompts user for input and returns it
     * 
     * @return User-inputted string
     */
    private static String receiveInput() {
        System.out.print("> ");
        String input = SCANNER.nextLine();
        return input;
    }

    /**
     * Parses the arguments of the user input and separates them into elements of a String array
     * 
     * @param input user input as a single String
     * @return void, but sets commandArgs based on the input. 
     *     commandArgs["command"] is the command, 
     *     commandArgs["body"] is the unflagged part of the user input,
     *     subsequent flagged elements are stored as commandArgs[flag name].
     */
    private static void parseInput(String input) {
        commandArgs.clear();
        String[] splittedInput = input.split(" ");
        commandArgs.put(COMMAND, splittedInput[0]);
        String currentFlag = BODY;
        for (int i = 1; i < splittedInput.length; i++) {
            String currentString = splittedInput[i];
            if (currentString.charAt(0) == '/') {
                currentFlag = currentString.substring(1);
                commandArgs.put(currentFlag, null);
                continue;
            }
            if (commandArgs.get(currentFlag) == null) {
                commandArgs.put(currentFlag, currentString);
            }
            else {
                commandArgs.put(currentFlag, commandArgs.get(currentFlag) + " " + currentString);
            }
        }
    }

    /**
     * Executes a command based on the input String. Also does most error handling
     * 
     * @param input user input String
     * @return boolean representing if Dackel is to terminate after executeInput returns
     */
    // TODO: make error handling more specific: say which flags are there when they shouldn't, e.g.
    private static boolean executeInput(String input) throws DackelException {
        parseInput(input);
        String command = commandArgs.get(COMMAND);
        String body = commandArgs.get(BODY);
        switch (command) {
        case QUIT:
            if (commandArgs.size() > 1) {
                throw new DackelException("too many arguments!");
            }
            return false;
        case LIST:
            if (commandArgs.size() > 1) {
                throw new DackelException("too many arguments!");
            }
            listTasks();
            break;
        case MARK:
            if (body == null) {
                throw new DackelException("list index cannot be left blank!");
            }
            if (body.length() == 0) {
                throw new DackelException("list index cannot be left blank!");
            }
            if (commandArgs.size() > 2) {
                throw new DackelException("too many arguments!");
            }
            try {
                int index = Integer.valueOf(body) - 1;
                if (index >= numberOfTasks) {
                    throw new DackelException("there is no task with that index in the list.");
                }
                if (index < 0) {
                    throw new DackelException("list indices must be greater than 0!");
                }
                markTask(index);
            }
            catch (NumberFormatException e) {
                throw new DackelException("\"" + body + "\" isn't a number!");
            }
            break;
        case UNMARK:
            if (body == null) {
                throw new DackelException("list index cannot be left blank!");
            }
            if (body.length() == 0) {
                throw new DackelException("list index cannot be left blank!");
            }
            if (commandArgs.size() > 2) {
                throw new DackelException("too many arguments!");
            }
            try {
                int index = Integer.valueOf(body) - 1;
                if (index >= numberOfTasks) {
                    throw new DackelException("there is no task with that index in the list.");
                }
                if (index < 0) {
                    throw new DackelException("list indices must be greater than 0!");
                }
                unmarkTask(index);
            }
            catch (NumberFormatException e) {
                throw new DackelException("\"" + body + "\" isn't a number!");
            }
            break;
        case TODO:
            if (body == null) {
                throw new DackelException("task description can't be left empty!");
            }
            if (body.length() == 0) {
                throw new DackelException("task description can't be left empty!");
            }
            if (commandArgs.size() > 2) {
                throw new DackelException("too many arguments!");
            }
            addTodo(body);
            break;
        case DEADLINE:
            String by = commandArgs.get(BY);
            if (body == null) {
                throw new DackelException("task description can't be left empty!");
            }
            if (body.length() == 0) {
                throw new DackelException("task description can't be left empty!");
            }
            if (by == null) {
                throw new DackelException("due date/time must be specified! use the /" + BY + " flag.");
            }
            if (by.length() == 0) {
                throw new DackelException("task due date/time can't be left empty!");
            }
            if (commandArgs.size() > 3) {
                throw new DackelException("too many arguments!");
            }
            addDeadline(body, by);
            break;
        case EVENT:
            String from = commandArgs.get(FROM);
            String until = commandArgs.get(UNTIL);
            if (body == null) {
                throw new DackelException("task description can't be left empty!");
            }
            if (body.length() == 0) {
                throw new DackelException("task description can't be left empty!");
            }
            if (from == null) {
                throw new DackelException("starting date/time must be specified! use the /" + FROM + " flag.");
            }
            if (from.length() == 0) {
                throw new DackelException("task starting date/time can't be left empty!");
            }
            if (until == null) {
                throw new DackelException("ending date/time must be specified! use the /" + UNTIL + " flag.");
            }
            if (until.length() == 0) {
                throw new DackelException("task ending date/time can't be left empty!");
            }
            if (commandArgs.size() > 4) {
                throw new DackelException("too many arguments!");
            }
            addEvent(commandArgs.get(BODY), commandArgs.get(FROM), commandArgs.get(UNTIL));
            break;
        case DELETE:
            if (body == null) {
                throw new DackelException("list index cannot be left blank!");
            }
            if (body.length() == 0) {
                throw new DackelException("list index cannot be left blank!");
            }
            if (commandArgs.size() > 2) {
                throw new DackelException("too many arguments!");
            }
            try {
                int index = Integer.valueOf(body) - 1;
                if (index >= numberOfTasks) {
                    throw new DackelException("there is no task with that index in the list.");
                }
                if (index < 0) {
                    throw new DackelException("list indices must be greater than 0!");
                }
                deleteTask(index);
            }
            catch (NumberFormatException e) {
                throw new DackelException("\"" + body + "\" isn't a number!");
            }
            break;
        default:
            if (command.length() == 0) {
                throw new DackelException("command can't be empty! type something!");
            }
            throw new DackelException("\"" + commandArgs.get(COMMAND) + "\" is not a recognized command.");
        }
        return true;
    }

    public static void main(String[] args) {
        // title cards, etc.
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println(VERSION);
        System.out.println(LINE);
        System.out.println();

        // greeting message
        speak("hihi!");
        speak("what can i do for you today?");
        
        // main command entry loop
        // TODO: make dackel read its lines from a file
        boolean isNotQuit = true;
        while (isNotQuit) {
            try {
                String input = receiveInput();
                isNotQuit = executeInput(input);
            }
            catch (DackelException e) {
                speak(e.getMessage());
            }
        }

        // goodbye message
        speak("hope to see you again soon!");
    }
}
