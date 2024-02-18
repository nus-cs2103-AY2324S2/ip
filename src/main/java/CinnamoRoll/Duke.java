package CinnamoRoll;

import java.util.Scanner;

// Firstly, overall string formatting for toString methods in methods in TaskList and child classes of Task class
// with header comments were adapted from chatgpt generated results:
// https://chat.openai.com/c/51dceff5-b364-42aa-a5b9-41f2859ec48b
// https://chat.openai.com/c/30a81394-ee96-43ba-ab98-f1a95355d6b0

// Secondly, exception handling codes were inspired by chatgpt generated codes, specifically on how to
// create subclasses for Exception class and what are the public methods for exception classes:
// https://chat.openai.com/c/7d9fd6bb-3163-49c7-8301-289a1ebaa6e1

// Lastly, date-time formatting occuring from Task classes and parsers was inspired by two sources,
// which are also explained and commented in a very detailed manner in parser class:
// 1. https://github.com/david-eom/CS2103T-IP/releases/tag/Level-8.
// 2. https://stackoverflow.com/questions/70384955/converting-one-date-time-format-into-another-in-java
/**
 * Runs Duke class
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    /**
     * Constructs Duke class that contains Ui class to handle user inputs, storage to load / store the
     * input, and tasklist to handle the tasks and perform operations
     */
    public Duke() {
        this.ui = new Ui();
        storage = new Storage();
        try {
            this.tasklist = new TaskList(this.storage.loadData());
        } catch (Exception e) {
            System.out.println("Something wrong has happened while loading the data!");
        }
    }

    /**
     * Runs the main part of the code to start the Chatbot Cinnamo
     */
    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        this.ui.greetUser();
        boolean isTerminated = false;
        while (!isTerminated) {
            String input = sc.nextLine();
            isTerminated = ui.respondUser(this.tasklist, input);
        }
        ui.exitChat();
    }

    /**
     * Returns the output from executing the commands from user inputs
     * @param input
     * @return
     */
    public String getResponse(String input) throws Exception {
        try {
            Parser parse = new Parser();
            String[] userInput = parse.parseInput(input);
            switch (User.valueOf(userInput[0])) {
            case MARK:
                return this.tasklist.markTask(userInput);

            case UNMARK:
                return this.tasklist.unmarkTask(userInput);

            case LIST:
                return this.tasklist.listTask();

            case DELETE:
                return this.tasklist.deleteTask(userInput);

            case TODO:
                return this.tasklist.executeTask(userInput);

            case DEADLINE:
                return this.tasklist.executeTask(userInput);

            case EVENT:
                return this.tasklist.executeTask(userInput);

            case FIND:
                return this.tasklist.findTask(userInput[1]);

            case DUPLICATE:
                return this.tasklist.listDuplicates();

            case UNIQUIFY:
                return this.tasklist.uniquifyTasks();

            default:
                throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            return cin.toString();
        } catch (IllegalArgumentException e) {
            CinnamoException cin = new CinnamoException();
            return cin.toString();
        }
    }


    public static void main(String[] args) throws Exception {
        new Duke().run();
    }
}
