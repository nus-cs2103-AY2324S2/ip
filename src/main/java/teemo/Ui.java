package teemo;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Ui class for menu display.
 */
public class Ui {
    /**
     * Constructor for Ui
     */
    public Ui() {
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
     */
    public String nextCommand(Options choice, TaskList taskList, List<String> trail, Storage storage)
            throws DukeException, IOException {
        LocalDate now = LocalDate.now(); // Current date
        Task add;
        String output;
        trail.remove(0);

        switch (choice) {
        case bye: // TODO make this exit the chatbot
            return exit();
        case list:
            output = taskList.list();
            if (!Objects.equals(output, "")) {
                return output;
            } else {
                return "You have no tasks for now! Good job!";
            }
        case delete:
            int no = Integer.parseInt(trail.get(0));
            output = taskList.delete(no);
            return output;
        case mark:
            int markNo = Integer.parseInt(trail.get(0));
            output = taskList.mark(markNo);
            return output;
        case unmark:
            int unmarkNo = Integer.parseInt(trail.get(0));
            output = taskList.unmark(unmarkNo);
            return output;
        case todo:
            if (trail.get(0).equals("")) {
                throw new DukeException("Description for todo cannot be empty!");
            }
            add = new ToDo(trail.get(0));
            output = taskList.add(add);
            return output;
        case deadline:
            if (trail.get(0).equals("")) {
                throw new DukeException("Description for deadline cannot be empty!");
            }
            try {
                LocalDate d1 = LocalDate.parse(trail.get(1));
                if (d1.isBefore(now)) {
                    throw new DukeException("Deadline must be after today!");
                }
                add = new Deadline(trail.get(0), d1);
                output = taskList.add(add);
                return output;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please include /by tag for deadline!");
            }
        case event:
            if (trail.get(0).equals("")) {
                throw new DukeException("Description for event cannot be empty!");
            }
            try {
                LocalDate d2 = LocalDate.parse(trail.get(1));
                LocalDate d3 = LocalDate.parse(trail.get(2));
                if (d3.isBefore(d2)) {
                    throw new DukeException("To must be after From!");
                }
                add = new Event(trail.get(0), d2, d3);
                output = taskList.add(add);
                return output;
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please include /from and /to tags!");
            }
        case save:
            output = storage.save(taskList.getTaskList());
            return output;
        case find:
            String search = trail.get(0);
            for (int i = 1; i < trail.size(); i++) {
                search += " " + trail.get(i);
            }
            output = taskList.find(search);
            return output;
        case help:
            output = "Commands available:\n";
            for (Options option : Options.values()) {
                output += "\t" + option.name() + ": " + option.getDescription() + "\n";
            }
            return output;
        case update: // format: update taskid (field) (update value)
            String[] parts = trail.get(0).split("\\s+", 3);
            Task toUpdate = taskList.getTaskList().get(Integer.parseInt(parts[0]) - 1);
            try {
                String updateField = parts[1];
                String updateValue = parts[2];
                LocalDate updateDate;

                if (updateField.equals("name")) {
                    toUpdate.updateDescription(updateValue);
                } else if (updateField.equals("by") && toUpdate instanceof Deadline) {
                    updateDate = LocalDate.parse(updateValue);
                    if (updateDate.isBefore(now)) {
                        throw new DukeException("Deadline must be after today!");
                    }
                    Deadline deadline = (Deadline) toUpdate;
                    deadline.updateBy(updateDate);
                } else if (updateField.equals("from")) {
                    Event event = (Event) toUpdate;
                    updateDate = LocalDate.parse(updateValue);
                    if (updateDate.isAfter(event.to)) {
                        throw new DukeException("From must be before to!");
                    }
                    event.updateFrom(updateDate);
                } else if (updateField.equals("to")) {
                    Event event = (Event) toUpdate;
                    updateDate = LocalDate.parse(updateValue);
                    if (updateDate.isBefore(event.from)) {
                        throw new DukeException("To must be after From!");
                    }
                    event.updateTo(updateDate);
                } else {
                    throw new DukeException("Invalid field to update! Available fields are (name/by/to/from)");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter valid field and value to update!");
            }
            return "Task updated!";
        case error:
            throw new DukeException("This isn't a valid command! Try [help] instead?");
        default:
            return "";
        }
    }
    /**
     * End the chatbot.
     * @return String of output to be used if needed.
     */
    public String exit() {
        return "\tBye. Hope to see you again soon!\n";
    }
}
