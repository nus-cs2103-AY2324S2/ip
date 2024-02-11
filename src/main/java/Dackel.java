import java.util.Scanner;

public class Dackel {
    /** Command aliases */
    private static final String QUIT = "bye";
    private static final String LIST = "list";

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

    /** Memory for storing task data */
    private static String[] storedTasks = new String[100];
    private static int numberOfTasks = 0;

    /**
     * Simulates Dackel speaking to the user ala a chatroom interface
     * 
     * @param string Message for Dackel to send
     */
    private static void speak(String message) {
        // TODO: make this take in String[] args
        System.out.println(NAME + ": " + message);
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
     * Adds a task to storedTasks
     * 
     * @param task Task to be added
     */
    private static void addTask(String task) {
        storedTasks[numberOfTasks] = task;
        numberOfTasks++;
        speak("added task \"" + task + "\"");
    }

    /**
     * Lists all tasks in storedTasks
     */
    private static void listTasks() {
        String s = "";
        for (int i = 0; i < numberOfTasks; i++) {
            s += "\n" + String.format(" %2d " + storedTasks[i], i + 1);
        }
        speak(s);
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
            switch (input) {
            case QUIT:
                isNotQuit = false;
                break;
            case LIST:
                listTasks();
                break;
            default:
                addTask(input);
            }
        }

        // goodbye message
        speak("hope to see you again soon!");
    }
}
