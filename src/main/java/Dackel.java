import java.util.Scanner;
import java.util.HashMap;

public class Dackel {
    /** Command aliases */
    private static final String QUIT = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    /** Command flags */
    private static final String COMMAND = "command";
    private static final String BODY = "body";
    private static final String BY = "by";
    private static final String FROM = "from";
    private static final String UNTIL = "until";

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
    private static Task[] storedTasks = new Task[100];
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
        storedTasks[numberOfTasks] = newTask;
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
        storedTasks[numberOfTasks] = newTask;
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
        storedTasks[numberOfTasks] = newTask;
        numberOfTasks++;
        speak("added the following task to your list!\n " + newTask.toString());
        speak("your list now has " + String.valueOf(numberOfTasks) + " tasks.");
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
            s += "\n" + String.format(" %2d " + storedTasks[i].toString(), i + 1);
        }
        speak(s);
    }

    /**
     * Marks the specified task as done
     * 
     * @param index index of task in storedTasks
     */
    private static void markTask(int index) {
        storedTasks[index].mark();
        speak("i've marked the following task as done!\n " + storedTasks[index].toString());
    }

    /**
     * Marks the specified task as not done
     * 
     * @param index index of task in storedTasks
     */
    private static void unmarkTask(int index) {
        storedTasks[index].unmark();
        speak("i've unmarked the following task. do it soon, please!\n " + storedTasks[index].toString());
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
     * @return a String to String map parsedInput[]. 
     *     parsedInput["command"] is the command, 
     *     parsedInput["body"] is the unflagged part of the user input,
     *     subsequent flagged elements are stored as parsedInput[flag name].
     */
    private static HashMap<String, String> parseInput(String input) {
        HashMap<String, String> parsedInput = new HashMap<>();
        String[] splittedInput = input.split(" ");
        parsedInput.put(COMMAND, splittedInput[0]);
        String currentFlag = BODY;
        for (int i = 1; i < splittedInput.length; i++) {
            String currentString = splittedInput[i];
            if (currentString.charAt(0) == '/') {
                currentFlag = currentString.substring(1);
                continue;
            }
            if (parsedInput.containsKey(currentFlag)) {
                parsedInput.put(currentFlag, parsedInput.get(currentFlag) + " " + currentString);
            }
            else {
                parsedInput.put(currentFlag, currentString);
            }
        }
        return parsedInput;
    }

    /**
     * Executes a command based on the input String
     * 
     * @param input user input String
     * @return boolean representing if Dackel is to terminate after executeInput returns
     */
    private static boolean executeInput(String input) {
        commandArgs = parseInput(input);
        switch (commandArgs.get(COMMAND)) {
        case QUIT:
            return false;
        case LIST:
            listTasks();
            break;
        case MARK:
            int index = Integer.valueOf(commandArgs.get(BODY)) - 1;
            markTask(index);
            break;
        case UNMARK:
            int index = Integer.valueOf(commandArgs.get(BODY)) - 1;
            unmarkTask(index);
            break;
        case TODO:
            addTodo(commandArgs.get(BODY));
            break;
        case DEADLINE:
            addDeadline(commandArgs.get(BODY), commandArgs.get(BY));
            break;
        case EVENT:
            addEvent(commandArgs.get(BODY), commandArgs.get(FROM), commandArgs.get(UNTIL));
            break;
        default:
            break;
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
            String input = receiveInput();
            isNotQuit = executeInput(input);
        }

        // goodbye message
        speak("hope to see you again soon!");
    }
}
