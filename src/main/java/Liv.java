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
    private State currentState;
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

        String[] words = input.split(" ");

        // for multi-word commands
        if (words.length != 1) {

            // mark & unmark
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                if (isInteger(words[1])) {
                    int taskIndex = Integer.parseInt(words[1]);
                    speak(toggleTaskDoneWithIndex(taskIndex, words[0]));
                } else {
                    System.out.println("Action failed: task index input is not an integer");
                }
                return;
            }

            if (words[0].equals("todo")
                    || words[0].equals("deadline")
                    || words[0].equals("event")) {
                Task newTask = null;
                if (words[0].equals("todo")) {
                    newTask = new ToDo(input);
                } else if (words[0].equals("deadline")) {
                    newTask = new Deadline(input);
                } else if (words[0].equals("event")) {
                    newTask = new Event(input);
                }
                addTask(newTask);
                speak("Got it. I've added this task:"
                        + "\n"
                        + "    "
                        + newTask.toString()
                        + "\n"
                        + "Now you have " + numberOfTasks + " tasks in the list.");//input);
            }
            //...
        }


        if (input.equals("bye")) {
            EndSession();
            return;
        }

        if (input.equals("list")) {
            listTasks();
            return;
        }

        /*
        if (input != null) {
            Task newTask = new ToDo(input);//new Task(input);
            addTask(newTask);
            speak("added: " + input);
            return;
        }
         */
    }

    private void speak(String output) {
        ToggleConversationState();
        System.out.println(output);
        ToggleConversationState();
    }

    private void listTasks() {
        ToggleConversationState();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + "." + tasks.get(i - 1).toString());
        }
        ToggleConversationState();
    }

    private void addTask(Task task) {
        tasks.add(task);
        numberOfTasks++;
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // takes in the listed index of the task (1 larger than storage index)
    private String toggleTaskDoneWithIndex(int index, String isDoneUpdateString) {
        tasks.get(index - 1).toggleIsDone(isDoneUpdateString);
        return tasks.get(index - 1).updateIsDoneMessage();
    }
}
