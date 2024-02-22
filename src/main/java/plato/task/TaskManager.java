package plato.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import plato.PlatoException;
import plato.parser.DateHandler;
import plato.storage.SaveType;

/**
 * Manager class to manage and keep track of all the actions being performed on the task.
 */
public class TaskManager {
    public static final LocalTime TIME_DEFAULT = LocalTime.of(0, 0);
    private static final String RESPONSE_LISTING = "Here are the tasks in your list:";

    private static final String RESPONSE_MARK = "Nice! I've marked this task as done:";
    private static final String RESPONSE_UMARK = "OK, I've marked this task as not done yet:";
    private static final String RESPONSE_REMOVE = "Noted. I've removed this task";
    private static final String RESPONSE_ADD = "Got it. I've added this task:";
    private static final String RESPONSE_FIND = "Here are the matching tasks in your list: ";
    private static final String RESPONSE_VIEW_DATES = "Here are the task scheduled on that date: ";

    private static final String RESPONSE_EMPTY = "You have no tasks!!!! Add something to do you peasant!! ";
    private static final String RESPONSE_EMPTY_SEARCH = "Sorry I couldn't find anything that fits that search :(";
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
     * @return A String array of the values to output to the Ui.
     * @throws PlatoException Invalid processing of the items.
     */
    public String[] addTask(Actions options, String instruction) throws PlatoException {

        Task item = getTask(options, instruction);
        hasChanged = true;
        items.add(item);

        String[] ret = new String[3];
        ret[0] = RESPONSE_ADD;
        ret[1] = item.toString();
        ret[2] = numOfTask();

        return ret;

    }

    private static Task getTask(Actions options, String instruction) throws PlatoException {
        assert options != null : "Invalid action operation";
        Task item;
        String description;
        String by;
        String from;
        Pattern deadlineFormat = Pattern.compile("(?<description>.+)\\s?((?i)/by)(?<by>.+)");
        Pattern eventFormat = Pattern.compile("(?<description>.+)\\s?((?i)/from)(?<from>.+)((?i)/to)(?<by>.+)");
        switch (options) {
        case TODO:
            if (instruction.isBlank()) {
                throw new PlatoException("description");
            }
            item = new Todo(instruction);
            break;
        case DEADLINE:
            Matcher deadlineMatch = deadlineFormat.matcher(instruction);
            if (!deadlineMatch.find()) {
                throw new PlatoException("datelineError");
            }
            description = deadlineMatch.group("description");
            by = deadlineMatch.group("by").trim();
            checkDeadlineValid(description, by);
            item = constructDeadline(by, description);
            break;
        case EVENT:
            Matcher eventMatch = eventFormat.matcher(instruction);
            if (!eventMatch.find()) {
                throw new PlatoException("eventError");
            }
            description = eventMatch.group("description");
            by = eventMatch.group("by").trim();
            from = eventMatch.group("from").trim();
            checkEventValid(from, by, description);
            item = constructEvent(by, from, description);
            break;
        default:
            throw new PlatoException("invalidError");
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

    private static void checkDeadlineValid(String description, String by) throws PlatoException {
        if (description.isBlank()) {
            throw new PlatoException("descriptionError");
        } else if (by.isBlank()) {
            throw new PlatoException("byEmptyError");
        }
    }

    private static Task constructDeadline(String by, String description) throws PlatoException {
        Optional<LocalDate> testDate = DateHandler.checkDate(by);
        LocalTime testTime = getTimeFromString(by);
        return testDate.map(localDate -> new Deadline(description, LocalDateTime.of(localDate, testTime)))
                       .orElseGet(() -> new Deadline(description, by));
    }

    private static void checkEventValid(String from, String by, String description) throws PlatoException {
        if (from.isBlank()) {
            throw new PlatoException("fromEmptyError");
        } else if (by.isBlank()) {
            throw new PlatoException("byEmptyError");
        } else if (description.isBlank()) {
            throw new PlatoException("descriptionError");
        }
    }

    private static Task constructEvent(String by, String from, String description) throws PlatoException {

        Optional<LocalDate> testByDate = DateHandler.checkDate(by);
        Optional<LocalDate> testFromDate = DateHandler.checkDate(from);
        LocalTime testTimeBy = getTimeFromString(by);
        LocalTime testTimeFrom = getTimeFromString(from);
        Optional<LocalDateTime> combineByDate =
            testByDate.flatMap(byDate -> Optional.of(LocalDateTime.of(byDate, testTimeBy)));

        Optional<LocalDateTime> combineFromDate =
            testFromDate.flatMap(fromDate -> Optional.of(LocalDateTime.of(fromDate, testTimeFrom)));

        return combineByDate.flatMap(byDate -> combineFromDate.flatMap(fromDate -> Optional.of(new Event(description,
                                                                                                         fromDate,
                                                                                                         byDate))))
                            .orElseGet(() -> new Event(description, from, by));
    }

    private static LocalTime getTimeFromString(String time) throws PlatoException {
        return DateHandler.checkTime(time).orElse(TIME_DEFAULT);
    }

    /**
     * Manages current task in TaskManager.
     *
     * @param act         A valid action.
     * @param instruction A command to indicate what to do based on the action.
     * @return A String array of the outputs to be return to the Ui.
     * @throws PlatoException Invalid processing of the items.
     */
    public String[] manageTask(Manage act, String instruction) throws PlatoException {
        assert act != null : "Invalid manage operation";
        int id = getId(instruction);
        Task item = items.get(id);
        String response = decideManageAction(act, item, id);
        hasChanged = true;
        return buildManageResponse(act, response, item);
    }

    private int getId(String instruction) throws PlatoException {
        if (items.isEmpty()) {
            throw new PlatoException("empty");
        }
        if (instruction.isBlank()) {
            throw new PlatoException("number");
        }
        int id = Integer.parseInt(instruction) - 1; //Index 0 based
        if (id < 0 || id >= items.size()) {
            throw new PlatoException("outOfRange");
        }
        return id;
    }

    private String decideManageAction(Manage act, Task item, int id) throws PlatoException {
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
            throw new PlatoException("manageError");
        }
        return response;
    }

    private String[] buildManageResponse(Manage act, String response, Task item) throws PlatoException {
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
            throw new PlatoException("invalidError");
        }
        return ret;
    }


    /**
     * Querys the task with the corresponding query action.
     *
     * @param act         a valid query action
     * @param instruction query parameters to find
     * @return A String array of values to return to the Ui to print.
     * @throws PlatoException Invalid query of tasks.
     */
    public String[] queryTasks(Query act, String instruction) throws PlatoException {

        String[] ret;
        switch (act) {
        case FIND:
            ret = findTask(instruction);
            break;
        case VIEW:
            ret = viewByDate(instruction);
            break;
        default:
            throw new PlatoException("queryError");
        }
        return ret;

    }

    /**
     * Finds the task in the current list.
     *
     * @param search The keywords to search.
     * @return A list of items containing the search results.
     */
    public String[] findTask(String search) {
        List<String> foundTask =
            items.stream().map(Task::toString).filter(string -> string.contains(search)).collect(Collectors.toList());

        if (!foundTask.isEmpty()) {
            List<String> print = iterateWithIndex(foundTask);
            print.add(0, RESPONSE_FIND);
            return print.toArray(String[]::new);

        } else {
            return new String[]{RESPONSE_EMPTY_SEARCH};

        }
    }

    /**
     * Returns the task scheduled on that particular date.
     *
     * @param date the date to query.
     * @return A string array of the task to print to the Ui.
     * @throws PlatoException throws invalid query error.
     */
    public String[] viewByDate(String date) throws PlatoException {
        LocalDate inputDate = DateHandler.checkDate(date).orElseThrow(() -> new PlatoException("dateError"));
        System.out.println(inputDate);
        List<String> foundDates =
            items.stream().filter(item -> isMatchDate(item.getType(), item, inputDate)).map(Task::toString)
                 .collect(Collectors.toList());

        if (!foundDates.isEmpty()) {
            List<String> print = iterateWithIndex(foundDates);
            print.add(0, RESPONSE_VIEW_DATES);
            return print.toArray(String[]::new);

        } else {
            return new String[]{RESPONSE_EMPTY_SEARCH};

        }
    }

    private static List<String> iterateWithIndex(List<String> toReturn) {
        return IntStream.range(0, toReturn.size()).mapToObj(i -> (i + 1) + ". " + toReturn.get(i))
                        .collect(Collectors.toList());
    }

    private boolean isMatchDate(SaveType type, Task first, LocalDate second) {
        if (type.equals(SaveType.DEADLINE)) {
            Deadline test = (Deadline) first;
            Optional<LocalDateTime> compareDate = test.getDateTime();
            return compareDate.map(localDateTime -> localDateTime.toLocalDate().equals(second)).orElse(false);

        } else if (type.equals(SaveType.EVENT)) {
            Event test = (Event) first;
            Optional<LocalDateTime> compareDate = test.getDateTime();
            return compareDate.map(localDateTime -> localDateTime.toLocalDate().equals(second)).orElse(false);

        } else {
            return false;
        }
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
     * @return A String array of the all the items.
     */
    public String[] listItems() {
        if (items.isEmpty()) {
            return new String[]{RESPONSE_EMPTY};

        }

        List<String> print = iterateWithIndex((items.stream().map(Task::toString).collect(Collectors.toList())));
        print.add(0, RESPONSE_LISTING);

        return print.toArray(String[]::new);
    }

    public boolean getUpdate() {
        return hasChanged;
    }

    public void setUpdate(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

}
