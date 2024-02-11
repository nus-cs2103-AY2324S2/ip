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

    /**
     * Constructor for Duke class.
     * @param filePath Path to save file for tasks.
     */
    public Duke(String filePath) {
        load(filePath);
    }

    /**
     * Default constructor for Duke class.
     * Used for GUI.
     */
    public Duke() {
        String filePath = "./tasks.txt";
        load(filePath);
    }
    /**
     * Method for loading required variables
     */
    public void load(String filePath) {
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
        String errorMessage;

        while (!isExit) {
            try {
                // Take user input
                input = scanner.nextLine();
                stringList = parser.parse(input);

                Options choice = optionType(stringList.get(0));

                ui.nextCommand(choice, tasks, stringList, storage);

                if (choice.equals(optionType("bye"))) {
                    isExit = true;
                }

            } catch (DukeException de) {
                errorMessage = ui.padding("\t" + de + "\n");
                System.out.println(errorMessage);
            } catch (NumberFormatException nfe) {
                errorMessage = ui.padding("\tInvalid number, please enter a valid number.\n");
                System.out.println(errorMessage);
            } catch (IndexOutOfBoundsException oobe) {
                if (tasks.size() == 0) {
                    errorMessage = ui.padding("\tNo tasks found. Please add a task first!\n");
                } else {
                    errorMessage = ui.padding("\tInvalid number, please enter a valid number between 1 and "
                            + tasks.size() + ".\n");
                }
                System.out.println(errorMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DateTimeParseException dtpe) {
                errorMessage = ui.padding("\tPlease enter a datetime format of yyyy-mm-dd.\n");
                System.out.println(errorMessage);
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
            Options choice = optionType(stringList.get(0));
            return ui.nextCommand(choice, tasks, stringList, storage).replace("\t", "");
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
    public Options optionType(String option) {
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
        case "help":
            return Options.help;
        default:
            return Options.error;
        }
    }
}

enum Options {
    help, bye, list, mark, unmark, todo, deadline, event, delete, find, save, error
}
