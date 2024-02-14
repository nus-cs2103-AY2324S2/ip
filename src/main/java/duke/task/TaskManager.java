package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.parser.DateHandler;

/**
 * Manager class to manage and keep track of all the actions being performed on the task.
 */
public class TaskManager {
    public static final LocalTime TIME_DEFAULT = LocalTime.of(0, 0);
    private static final String listingResponse = "Here are the tasks in your list:";
    //Strings for marking and unmarking
    private static final String RESPONSE_MARK = "Nice! I've marked this task as done:";
    private static final String RESPONSE_UMARK = "OK, I've marked this task as not done yet:";
    private static final String RESPONSE_REMOVE = "Noted. I've removed this task";
    //String and variables for task
    private static final String RESPONSE_ADD = "Got it. I've added this task:";
    private static final String RESPONSE_FIND = "Here are the matching tasks in your list";
    private final ArrayList<Task> items;
    private boolean hasChanged = false;


    /**
     * Creates a manager.
     */
    public TaskManager() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds task to the current manager list.
     *
     * @param item A task to add.
     */
    public void addItem(Task item) {
        items.add(item);
    }

    /**
     * Adds a Task based on the possible Actions and instruction to the current manage
     *
     * @param options     A valid Action to perform.
     * @param instruction A string value of the commands.
     * @return An ArrayList of string of the values to output to the Ui.
     * @throws DukeException Invalid processing of the items.
     */
    public String[] addTask(Actions options, String instruction) throws DukeException {

        Task item = getTask(options, instruction);
        hasChanged = true;
        items.add(item);

        String[] ret = new String[3];
        ret[0] = RESPONSE_ADD;
        ret[1] = item.toString();
        ret[2] = numOfTask();

        return ret;

    }

    private static Task getTask(Actions options, String instruction) throws DukeException {
        Task item;
        String description;
        String by;
        String from;
        Pattern deadlineFormat = Pattern.compile("(?<description>.+)\\s?((?i)/by)(?<by>.+)");
        Pattern eventFormat = Pattern.compile("(?<description>.+)\\s?((?i)/from)(?<from>.+)((?i)/to)(?<by>.+)");
        switch (options) {
        case TODO:
            if (instruction.isBlank()) {
                throw new DukeException("description");
            }
            item = new Todo(instruction);
            break;
        case DEADLINE:
            Matcher deadlineMatch = deadlineFormat.matcher(instruction);
            if (!deadlineMatch.find()) {
                throw new DukeException("datelineError");
            }
            description = deadlineMatch.group("description");
            by = deadlineMatch.group("by").trim();
            checkDeadlineValid(description, by);
            item = constructDeadline(by, description);
            break;
        case EVENT:
            Matcher eventMatch = eventFormat.matcher(instruction);
            if (!eventMatch.find()) {
                throw new DukeException("eventError");
            }
            description = eventMatch.group("description");
            by = eventMatch.group("by").trim();
            from = eventMatch.group("from").trim();
            checkEventValid(from, by, description);
            item = constructEvent(by, from, description);
            break;
        default:
            throw new DukeException("invalidError");
        }
        return item;
    }

    /**
     * Returns the number of task in the TaskManager.
     *
     * @return Nicely formatted String of the number task in the TaskManager.
     */
    public String numOfTask() {
        return "Now you have " + items.size() + " tasks in the list.";
    }

    private static void checkDeadlineValid(String description, String by) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("descriptionError");
        } else if (by.isBlank()) {
            throw new DukeException("byEmptyError");
        }
    }

    private static Task constructDeadline(String by, String description) throws DukeException {
        Optional<LocalDate> testDate = DateHandler.checkDate(by);
        return testDate.map(localDate -> new Deadline(description, LocalDateTime.of(localDate, getTimeFromString(by))))
                       .orElseGet(() -> new Deadline(description, by));
    }

    private static void checkEventValid(String from, String by, String description) throws DukeException {
        if (from.isBlank()) {
            throw new DukeException("fromEmptyError");
        } else if (by.isBlank()) {
            throw new DukeException("byEmptyError");
        } else if (description.isBlank()) {
            throw new DukeException("descriptionError");
        }
    }

    private static Task constructEvent(String by, String from, String description) throws DukeException {

        Optional<LocalDate> testByDate = DateHandler.checkDate(by);
        Optional<LocalDate> testFromDate = DateHandler.checkDate(from);
        Optional<LocalDateTime> combineByDate =
                testByDate.flatMap(byDate -> Optional.of(LocalDateTime.of(byDate, getTimeFromString(by))));
        Optional<LocalDateTime> combineFromDate =
                testFromDate.flatMap(fromDate -> Optional.of(LocalDateTime.of(fromDate, getTimeFromString(from))));
        return combineByDate.flatMap(byDate -> combineFromDate.flatMap(fromDate -> Optional.of(new Event(description,
                                                                                                         fromDate,
                                                                                                         byDate))))
                            .orElseGet(() -> new Event(description, from, by));
    }

    private static LocalTime getTimeFromString(String time) {
        return DateHandler.checkTime(time).orElse(TIME_DEFAULT);
    }

    /**
     * Manages current task in TaskManager.
     *
     * @param act         A valid action.
     * @param instruction A command to indicate what to do based on the action.
     * @return An ArrayList of String of the outputs to be return to the Ui.
     * @throws DukeException Invalid processing of the items.
     */
    public String[] manageTask(Manage act, String instruction) throws DukeException {
        int id = getId(instruction);
        Task item = items.get(id);
        String response = decideManageAction(act, item, id);
        hasChanged = true;
        return buildManageResponse(act, response, item);
    }

    private int getId(String instruction) throws DukeException {
        if (items.isEmpty()) {
            throw new DukeException("empty");
        }
        if (instruction.isBlank()) {
            throw new DukeException("number");
        }
        int id = Integer.parseInt(instruction) - 1; //Index 0 based
        if (id < 0 || id >= items.size()) {
            throw new DukeException("outOfRange");
        }
        return id;
    }

    private String decideManageAction(Manage act, Task item, int id) throws DukeException {
        String response = "";
        switch (act) {
        case UNMARK:
            item.unmark();
            response = RESPONSE_UMARK;
            break;
        case MARK:
            response = RESPONSE_MARK;
            item.markAsDone();
            break;
        case DELETE:
            response = RESPONSE_REMOVE;
            items.remove(id);
            break;
        default:
            throw new DukeException("manageError");
        }
        return response;
    }

    private String[] buildManageResponse(Manage act, String response, Task item) throws DukeException {
        String[] ret;
        switch (act) {
        case MARK:
            //fallthrough
        case UNMARK:
            ret = new String[2];
            ret[0] = response;
            ret[1] = item.toString();
            break;
        case DELETE:
            ret = new String[3];
            ret[0] = response;
            ret[1] = item.toString();
            ret[2] = numOfTask();
            break;
        default:
            throw new DukeException("invalidError");
        }
        return ret;
    }

    /**
     * Gets the current items in the TaskManager and produce them into a save format
     *
     * @return The tasks in a save format of String.
     */
    public String getTasksSave() {
        StringBuilder returnBuilder = new StringBuilder();
        for (Task item : items) {
            returnBuilder.append(item.saveFile()); //build the save here
            returnBuilder.append("\n");
        }
        return returnBuilder.toString();
    }

    /**
     * Gets all the current items in TaskManager.
     *
     * @return An String array of the all the items.
     */
    public String[] listItems() {
        if (items.isEmpty()) {
            return new String[]{"Your list is empty!!!!Add something! "};
        }
        String[] ret = new String[items.size()];
        ret[0] = listingResponse;
        for (int i = 0; i < items.size(); i++) {
            ret[i] = i + 1 + "." + items.get(i);
        }
        return ret;
    }

    public boolean getUpdate() {
        return hasChanged;
    }

    public void setUpdate(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    /**
     * Find the task in the current list.
     *
     * @param search The keywords to search.
     * @return A list of items containing the search results.
     */
    public String[] findTask(String search) {
        ArrayList<String> foundTask = new ArrayList<>();
        int count = 1;
        for (Task item : items) {
            if (item.toString().contains(search)) {
                if (count == 1) {
                    foundTask.add(RESPONSE_FIND);
                }
                foundTask.add(count + ". " + item);
                count += 1;
            }
        }
        if (foundTask.isEmpty()) {
            return new String[]{"Sorry I couldn't find anything that fits that search :("};
        }
        return foundTask.toArray(new String[0]);
    }


}
