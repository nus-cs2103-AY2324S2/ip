package duke;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Instance of Duke class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();
    private List<String> stringList;
    private String command;
    private String trail;

    /**
     * Constructor for Duke class.
     * @param filePath Path to save file for tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Default constructor for Duke class.
     * Used for GUI.
     */
    public Duke() {
        String filePath = "./tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Method for running the chatbot
     */
    public void run() {
        ui.greet();

        String input;
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                // Take user input
                input = scanner.nextLine();
                stringList = parser.parse(input);
                command = stringList.get(0);
                trail = stringList.get(1);

                Options choice = optionType(command);

                ui.nextCommand(choice, tasks, trail, storage);

                if (choice.equals(optionType("bye"))) {
                    isExit = true;
                }

            } catch (DukeException de) {
                String text = "\t____________________________________________________________\n"
                        + "\t" + de + "\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            } catch (NumberFormatException nfe) {
                String text = "\t____________________________________________________________\n"
                        + "\tInvalid number, please enter a valid number.\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            } catch (IndexOutOfBoundsException oobe) {
                String text;
                if (tasks.size() == 0) {
                    text = "\t____________________________________________________________\n"
                            + "\tNo tasks found. Please add a task first!\n"
                            + "\t____________________________________________________________\n";
                } else {
                    text = "\t____________________________________________________________\n"
                            + "\tInvalid number, please enter a valid number between 1 and " + tasks.size() + ".\n"
                            + "\t____________________________________________________________\n";
                }
                System.out.println(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DateTimeParseException dtpe) {
                String text;
                text = "\t____________________________________________________________\n"
                        + "\tPlease enter a datetime format of yyyy-mm-dd.\n"
                        + "\t____________________________________________________________\n";
                System.out.println(text);
            }
        }
    }

    /**
     * Gets response for GUI inputs.
     * @param input user input.
     * @return String of output.
     */
    protected String getResponse(String input) {
        try {
            stringList = parser.parse(input);
            command = stringList.get(0);
            trail = stringList.get(1);
            Options choice = optionType(command);

            return ui.nextCommand(choice, tasks, trail, storage).replace("\t", "");
        } catch (DukeException de) {
            return de + "\n";
        } catch (NumberFormatException nfe) {
            return "Invalid number, please enter a valid number.\n";
        } catch (IndexOutOfBoundsException oobe) {
            if (tasks.size() == 0) {
                return "No tasks found. Please add a task first!\n";
            } else {
                return "Invalid number, please enter a valid number between 1 and " + tasks.size() + ".\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DateTimeParseException dtpe) {
            return "Please enter a datetime format of yyyy-mm-dd.\n";
        }
        // return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run(); // for running in cli
        //Application.launch(Main.class, args); // for running in gui
    }
    /**
     * Returns the Option input by user.
     * @param option first word of input from user.
     * @return enum of given option.
     */
    public static Options optionType(String option) {
        switch (option) {
        case "bye":
            return Options.bye;
        case "list": case "ls":
            return Options.list;
        case "delete":
            return Options.delete;
        case "mark":
            return Options.mark;
        case "unmark":
            return Options.unmark;
        case "todo":
            return Options.todo;
        case "deadline":
            return Options.deadline;
        case "event":
            return Options.event;
        case "save":
            return Options.save;
        case "find":
            return Options.find;
        default:
            return Options.error;
        }
    }
}

enum Options {
    bye, list, delete, mark, unmark, todo, deadline, event, error, save, find
}
