import javax.xml.stream.events.StartDocument;
import java.util.LinkedList;
import java.util.Scanner;

// name of the chat bot
public class Liv {
    // to adhere to the singleton pattern
    private enum State {
        ACTIVE_TALKING,
        /*
            things to be handled in ACTIVE_TALKING state:
            - print words to say
         */
        ACTIVE_LISTENING,
        /*
            things to be handled in ACTIVE_LISTENING state:
            - take in user input
            - process the input
         */
        INACTIVE
    }
    private static Liv instance = null;
    private static HorizontalLine horizontalLine = null;
    private State currentState = null;
    private static Scanner scanner = null;
    private static LinkedList<Task> tasks = null;
    private static int numberOfTasks;

    private Liv() {
        // initial setup
        horizontalLine = HorizontalLine.getInstance();
        currentState = State.INACTIVE;
        scanner = new Scanner(System.in);
        tasks = new LinkedList<>();
        numberOfTasks = 0;
    }

    public static Liv getInstance() {
        if (instance == null) {
            instance = new Liv();
        }

        return instance;
    }
    public static void main(String[] args) {
        getInstance().Start();
    }

    private void Start() {
        instance.ToggleActiveState();
        instance.Greet();
        while (IsActive()) {
            // should start the cycle talking
            String userInput = StartListening();
            ProcessInput(userInput);
        }
    }

    private void Greet() {
        System.out.println("Hello there, Liv here.");
        System.out.println("How may I help you?");
    }

    private void EndSession() {
        // should be called from ACTIVE_LISTENING STATE, exception handling?
        ToggleConversationState();
        System.out.println("Hope you find my service helpful.");
        System.out.println("Till next time!");
        ToggleActiveState();
    }
    private void ToggleConversationState() {

        horizontalLine.printLine();

        if (currentState == State.ACTIVE_TALKING) {
            currentState = State.ACTIVE_LISTENING;
            return;
        }

        if (currentState == State.ACTIVE_LISTENING) {
            currentState = State.ACTIVE_TALKING;
            return;
        }
    }

    private void ToggleActiveState() {

        horizontalLine.printLine();

        if (currentState != State.INACTIVE) {
            currentState = State.INACTIVE;
            return;
        }

        if (currentState == State.INACTIVE) {
            currentState = State.ACTIVE_TALKING;
            return;
        }
    }
    private String StartListening() {
            // should be called from ACTIVE_TALKING STATE
            if (currentState == State.ACTIVE_TALKING) ToggleConversationState();
            return scanner.nextLine();
    }

    private void EndListening() {
        if (currentState == State.ACTIVE_TALKING) ToggleConversationState();
    }

    private boolean IsActive() {
        return currentState != State.INACTIVE;
    }

    private void ProcessInput(String input) {
        if (input.equals("bye")) {
            EndSession();
            return;
        }

        if (input.equals("list")) {
            ListTasks();
            return;
        }

        if (input != null) {
            Task newTask = new Task(input);
            addTask(newTask);
            Speak("added: " + input);
            return;
        }
    }

    private void Speak(String output) {
        ToggleConversationState();
        System.out.println(output);
        ToggleConversationState();
    }

    private void ListTasks() {
        ToggleConversationState();
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + ". " + tasks.get(i - 1).getDescription());
        }
        ToggleConversationState();
    }

    private void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }
}
