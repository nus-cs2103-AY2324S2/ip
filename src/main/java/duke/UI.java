package duke;

import java.util.Scanner;

import duke.exceptions.HistoryIndexException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

public class UI {

    private Storage manager;
    private Parser parser;
    private TaskList history;
    private Scanner sc;

    enum Command {
        BYE,
        LIST,
        EVENT,
        TODO,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE
    }

    public UI(Storage manager, Parser parser, TaskList history) {
        this.history = history;
        this.manager = manager;
        this.parser = parser;
        this.sc = new Scanner(System.in);
        sayHi();
    }

    /**
     * Lets shuheng say hi to everyone! :)
     */
    public void sayHi() {
        String name = "shu heng";
        String nameDisplay = "_________________________\n"
            + "Hello! I'm " + name + "\n"
            + "What can I do for you?\n"
            + "_________________________\n";
        System.out.println(nameDisplay);
    }
    /**
     * Lets shuheng say bye to everyone! :)
     */
    public void sayBye() {
        String finalPrint = "_________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "_________________________\n";
        System.out.println(finalPrint);
        this.sc.close();
    }
    /**
     * Runs the UI that continuously takes in user input and output.
     */
    public void run() {
        mainloop: while (true) {
            String currentInput = this.sc.nextLine();
            String[] currentInputSplit = currentInput.split(" ");
            Command currentCommand = null;
            try {
                currentCommand = parser.getCommand(currentInputSplit);
            } catch (InvalidTaskException e) {
                System.out.println("That's not a valid input :(");
                System.out.println(e.getMessage());
                continue;
            }
            switch (currentCommand) {
                case LIST:
                    history.showTaskList();
                    break;
                case BYE:
                    this.sayBye();
                    return;
                case DELETE:
                    int focusIndex = -1;
                    try {
                        focusIndex = parser.checkIndexGiven(currentInputSplit[1],
                        history.getLength());
                    } catch (HistoryIndexException e) {
                        System.out.println("Invalid index selected!");
                        continue;
                    }
                    history.deleteTask(focusIndex);
                    break;
                case MARK:
                    focusIndex = -1;
                    try {
                        focusIndex = parser.checkIndexGiven(currentInputSplit[1],
                        history.getLength());
                    } catch (HistoryIndexException e) {
                        System.out.println("Invalid index selected!");
                        continue;
                    }
                    history.markTask(focusIndex);
                    break;
                case UNMARK:
                    focusIndex = -1;
                    try {
                        focusIndex = parser.checkIndexGiven(currentInputSplit[1],
                        history.getLength());
                    } catch (HistoryIndexException e) {
                        System.out.println("Invalid index selected!");
                        continue;
                    }
                    history.unmarkTask(focusIndex);
                    break;
                case EVENT:
                    Task event = null;
                    String[] data;
                    try {
                        data = parser.extractDescriptionData(currentInputSplit);
                    } catch (InvalidInputException e) {
                        System.out.println("That's not a valid input :(");
                        System.out.println(e.getMessage());
                        continue mainloop;
                    }
                    try {
                        event = new Events(data[0], parser.parseDate(data[1]), parser.parseDate(data[2]));
                        history.addTask(event);
                    } catch (InvalidInputException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case TODO:
                    event = null;
                    try {
                        data = parser.extractDescriptionData(currentInputSplit);
                    } catch (InvalidInputException e) {
                        System.out.println("That's not a valid input :(");
                        System.out.println(e.getMessage());
                        continue mainloop;
                    }
                    event = new ToDos(data[0]);
                    history.addTask(event);
                    break;
                case DEADLINE:
                    try {
                        data = parser.extractDescriptionData(currentInputSplit);
                    } catch (InvalidInputException e) {
                        System.out.println("That's not a valid input :(");
                        System.out.println(e.getMessage());
                        continue;
                    }
                    try {
                        event = new Deadlines(data[0], parser.parseDate(data[1]));
                        history.addTask(event);
                    } catch (InvalidInputException e) {
                        System.out.println("Invalid Input: " + e.getMessage());
                    }
                    break;
                default:
                    continue mainloop;
            }
        }
    }
}
