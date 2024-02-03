package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
    public String nextCommand(Options choice, TaskList taskList, List<String> trail, Storage storage)
            throws DukeException, IOException {
        LocalDate now = LocalDate.now(); // Current date
        Task add;
        String taskName;
        String output;
        trail.remove(0);

        switch (choice) {
        case bye: // TODO make this exit the chatbot
            return exit();
        case list:
            output = taskList.list();
            System.out.println(padding(output));
            return output;
        case delete:
            int no = Integer.parseInt(trail.get(0));
            output = taskList.delete(no);
            System.out.println(padding(output));
            return output;
        case mark:
            int markNo = Integer.parseInt(trail.get(0));
            output = taskList.mark(markNo);
            System.out.println(padding(output));
            return output;
        case unmark:
            int unmarkNo = Integer.parseInt(trail.get(0));
            output = taskList.unmark(unmarkNo);
            System.out.println(padding(output));
            return output;
        case todo:
            if (trail.get(0).equals("")) {
                throw new DukeException("Description for todo cannot be empty!");
            }
            add = new ToDo(trail.get(0));
            output = taskList.add(add);
            System.out.println(padding(output));
            return output;
        case deadline:
            if (trail.get(0).equals("")) {
                throw new DukeException("Description for todo cannot be empty!");
            }
            try {
                LocalDate d1 = LocalDate.parse(trail.get(1));
                if (d1.isBefore(now)) {
                    throw new DukeException("Deadline must be after today!");
                }
                add = new Deadline(trail.get(0), d1);
                output = taskList.add(add);
                System.out.println(padding(output));
                return output;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please include /by tag for deadline!");
            }
        case event:
            if (trail.get(0).equals("")) {
                throw new DukeException("Description for todo cannot be empty!");
            }
            try {
                LocalDate d2 = LocalDate.parse(trail.get(1));
                LocalDate d3 = LocalDate.parse(trail.get(2));
                if (d3.isBefore(d2)) {
                    throw new DukeException("To must be after From!");
                }
                if (d2.isBefore(now)) {
                    throw new DukeException("From must be after today!");
                }
                add = new Event(trail.get(0), d2, d3);
                output = taskList.add(add);
                System.out.println(padding(output));
                return output;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please include /from and /to tags!");
            }
        case save:
            output = storage.save(taskList.getTaskList());
            System.out.println(padding(output));
            return output;
        case find:
            String search = trail.get(0);
            for (int i = 1; i < trail.size(); i++) {
                search += " " + trail.get(i);
            }
            output = taskList.find(search);
            System.out.println(padding(output));
            return output;
        case help:
            String text = "Commands available:\n";
            for (Options option : Options.values()) {
                text += "\t" + option.name() + "\n";
            }
            System.out.println(padding(text));
            return text;
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
