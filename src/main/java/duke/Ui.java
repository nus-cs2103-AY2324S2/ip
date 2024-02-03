package duke;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Ui class for menu display.
 */
public class Ui {
    /**
     * Constructor for Ui
     */
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Display initial greeting message.
     */
    public void greet() {
        String text = "\t____________________________________________________________\n"
                + "\tHello! I'm Teemo!\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    /**
     * Decide what to do next based on user input.
     *
     * @param choice User's command.
     * @param taskList TaskList from main program.
     * @param trail Remainder of user's input excluding command.
     * @param storage Storage from main program.
     * @return String of output to be used if needed.
     * @throws DukeException Custom exception for Duke related errors.
     * @throws IOException Exception for any file related problems.
     *
     */
    public String nextCommand(Options choice, TaskList taskList, String trail, Storage storage)
            throws DukeException, IOException {
        LocalDate now = LocalDate.now(); // Current date
        Task add;
        String taskName;
        String output;

        switch (choice) { // TODO add a help command to list all commands
        case bye: // TODO make this exit the chatbot
            return exit();
        case list:
            output = taskList.list();
            System.out.println(padding(output));
            return output;
        case delete:
            int no = Integer.parseInt(trail);
            output = taskList.delete(no);
            System.out.println(padding(output));
            return output;
        case mark:
            int markNo = Integer.parseInt(trail);
            output = taskList.mark(markNo);
            System.out.println(padding(output));
            return output;
        case unmark:
            int unmarkNo = Integer.parseInt(trail);
            output = taskList.unmark(unmarkNo);
            System.out.println(padding(output));
            return output;
        case todo:
            if (trail.isEmpty()) {
                throw new DukeException("Description of a ToDo cannot be empty!");
            }
            add = new ToDo(trail);
            output = taskList.add(add);
            System.out.println(padding(output));
            return output;
        case deadline:
            if (!trail.contains(" /by ")) {
                throw new DukeException("Description of a Deadline must contain \" /by \"!");
            }
            taskName = trail.substring(0, trail.indexOf(" /by "));
            if (taskName.isEmpty()) {
                throw new DukeException("Description of a Deadline cannot be empty!");
            }
            String by = trail.substring(trail.indexOf(" /by ") + 5);
            // depending on whether by can be empty or not
            // if (by.isEmpty()) {throw new DukeException("Deadline cannot be empty!");}

            LocalDate d1 = LocalDate.parse(by);

            if (d1.isBefore(now)) {
                throw new DukeException("Deadline must be after today!");
            }

            add = new Deadline(taskName, d1);
            output = taskList.add(add);
            System.out.println(padding(output));
            return output;
        case event:
            if (!trail.contains(" /from ") || !trail.contains(" /to ")) {
                throw new DukeException("Description of a Event must contain \" /from \" and \" /to \"!");
            }
            taskName = trail.substring(0, trail.indexOf(" /from "));
            if (taskName.isEmpty()) {
                throw new DukeException("Description of a Event cannot be empty!");
            }
            int a = trail.indexOf(" /from ") + 7;
            int b = trail.indexOf(" /to ");
            if (a > b) {
                throw new DukeException("From cannot be empty!");
            }
            String from = trail.substring(a, b);
            // depending on whether from can be empty or not
            // if (from.isEmpty()) {throw new DukeException("From cannot be empty!");}
            String to = trail.substring(trail.indexOf(" /to ") + 5);
            // depending on whether to can be empty or not
            // if (to.isEmpty()) {throw new DukeException("To cannot be empty!");}

            LocalDate d2 = LocalDate.parse(from);
            LocalDate d3 = LocalDate.parse(to);

            if (d3.isBefore(d2)) {
                throw new DukeException("To must be after From!");
            }

            if (d2.isBefore(now)) {
                throw new DukeException("From must be after today!");
            }

            add = new Event(taskName, d2, d3);
            output = taskList.add(add);
            System.out.println(padding(output));
            return output;
        case save:
            output = storage.save(taskList.getTaskList());
            System.out.println(padding(output));
            return output;
        case find:
            output = taskList.find(trail);
            System.out.println(padding(output));
            return output;
        case error:
            throw new DukeException("Command not found! Please try again.");
        default:
            return "";
        }
    }
    /**
     * End the chatbot.
     * @return String of output to be used if needed.
     */
    public String exit() {
        String text = "\tBye. Hope to see you again soon!\n";
        System.out.println(padding(text));
        return text;
    }

    /**
     * Displays message if no save file found.
     */
    public void showLoadingError() {
        String text = "\tSave file not found, creating a new one.\n";
        System.out.println(padding(text));
    }

    /**
     * Adding  padding
     * @param text text to pad
     * @return padded text
     */
    public String padding(String text) {
        return "\t____________________________________________________________\n"
                + text
                + "\t____________________________________________________________\n";
    }
}
