package teemo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

import javafx.application.Application;
/**
 * Instance of Duke class
 */
public class Teemo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();
    /**
     * Default constructor for Duke class.
     * Used for GUI.
     */
    public Teemo() {
        String filePath = "./tasks.txt";
        load(filePath);
    }
    /**
     * Loads the required variables
     */
    public void load(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            tasks = new TaskList();
        }
    }
    /**
     * Gets response for GUI inputs.
     * @param input user input.
     * @return String of output.
     */
    protected String getResponse(String input) {
        try {
            List<String> stringList = parser.parse(input);
            Options choice = optionType(stringList.get(0));
            return "Swiftly! Reporting in, here is your command.\n"
                    + ui.nextCommand(choice, tasks, stringList, storage).replace("\t", "");
        } catch (DukeException de) {
            return "That's gotta sting! " + de.getMessage() + "\n";
        } catch (NumberFormatException nfe) {
            return "Oh nyo! This isn't a number, please enter a valid one!\n";
        } catch (IndexOutOfBoundsException oobe) {
            if (tasks.size() == 0) {
                return "You have no tasks currently!\n";
            } else {
                return "Hmm, this number seems sus. Perhaps try a number between 1 and " + tasks.size() + "?\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DateTimeParseException dtpe) {
            return "Please use a datetime format of yyyy-mm-dd! Teemo can't understand other formats :(\n";
        }
    }
    public static void main(String[] args) {
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
        case "update":
            return Options.update;
        default:
            return Options.error;
        }
    }
}

enum Options {
    help("Display all commands and its usage."),
    bye("Exit the application."),
    list("List all tasks."),
    mark("Mark a task as completed. \nSyntax: mark (TaskId)"),
    unmark("Unmark a task. \nSyntax: unmark (TaskId)"),
    todo("Add a todo task. \nSyntax: todo (TaskName)"),
    deadline("Add a deadline task. \nSyntax: deadline (TaskName) /by (deadline)"),
    event("Add an event task. \nSyntax: event (TaskName) /from (start date) /to (end date)"),
    delete("Delete a task. \nSyntax: delete (TaskId)"),
    find("Find tasks by keyword. \nSyntax: find (TaskName)"),
    save("Save tasks to file."),
    error("Error option"),
    update("Update a task. \nSyntax: update (TaskId) (name/by/from/to) (update value)");

    private final String description;

    Options(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
