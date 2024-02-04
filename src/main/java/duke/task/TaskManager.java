package duke.task;

import duke.parser.DateHandler;
import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskManager {
    private ArrayList<Task> items;
    private boolean hasChanged = false;
    private static final String listingResponse = "Here are the tasks in your list:";

    //Strings for marking and unmarking

    private static final String RESPONSE_MARK = "Nice! I've marked this task as done:";
    private static final String RESPONSE_UMARK = "OK, I've marked this task as not done yet:";
    private static final String RESPONSE_REMOVE = "Noted. I've removed this task";


    //String and variables for task
    private static final String RESPONSE_ADD = "Got it. I've added this task:";

    private static final String RESPONSE_FIND = "Here are the matching tasks in your list";


    public TaskManager() {
        this.items = new ArrayList<>();
    }

    public void addItem(Task item) {
        items.add(item);
    }

    public ArrayList<String> addTask(Actions options, String instruction) throws DukeException {
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
            //add the deadline task
            Matcher deadlineMatch = deadlineFormat.matcher(instruction);
            if (!deadlineMatch.find()) {
                throw new DukeException("GIGABOOOM");
            }
            description = deadlineMatch.group("description");
            by = deadlineMatch.group("by").trim();
            if (description.isBlank()) {
                throw new DukeException("description");
            } else if (by.isBlank()) {
                throw new DukeException("by");
            }
            Optional<LocalDate> testDate = DateHandler.checkDate(by);
            item = testDate.map(localDate -> new Deadline(description,
                            LocalDateTime.of(localDate, DateHandler.checkTime(by)
                                    .orElse(LocalTime.of(0, 0)))))
                    .orElseGet(() -> new Deadline(description, by));
            break;
        case EVENT:
            Matcher eventMatch = eventFormat.matcher(instruction);
            if (!eventMatch.find()) {
                System.out.println("reached here");
                throw new DukeException("GIGABOOOM");
            }
            description = eventMatch.group("description");
            by = eventMatch.group("by").trim();
            from = eventMatch.group("from").trim();
            if (from.isBlank()) {
                throw new DukeException("from");
            } else if (by.isBlank()) {
                throw new DukeException("by");
            } else if (description.isBlank()) {
                throw new DukeException("description");
            }
            Optional<LocalDate> testByDate = DateHandler.checkDate(by);
            Optional<LocalDate> testFromDate = DateHandler.checkDate(from);
            //This will only check a valid time after checking a valid date if not it will skip and use legacy stuff
            Optional<LocalDateTime> combineByDate = testByDate.flatMap(byDate -> {
                LocalTime time = DateHandler.checkTime(by).orElse(LocalTime.of(0, 0));
                return Optional.of(LocalDateTime.of(byDate, time));
            });
            Optional<LocalDateTime> combineFromDate = testFromDate.flatMap(fromDate -> {
                LocalTime time = DateHandler.checkTime(from).orElse(LocalTime.of(0, 0));
                return Optional.of(LocalDateTime.of(fromDate, time));
            });
            item = combineByDate.flatMap(byDate ->
                            combineFromDate.flatMap(fromDate ->
                                    Optional.of(new Event(description, fromDate, byDate))))
                    .orElseGet(() -> new Event(description, from, by));
            break;
        default:
            throw new DukeException("Invalid");
        }
        hasChanged = true;
        items.add(item);
        ArrayList<String> returnString = new ArrayList<>();
        returnString.add(RESPONSE_ADD);
        returnString.add(item.toString());
        returnString.add(numOfTask());
        return returnString;

    }

    public ArrayList<String> manageTask(Manage act, String instruction) throws DukeException {
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
        Task item = items.get(id);
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
            //This does nothing
            break;
        }
        hasChanged = true;
        ArrayList<String> returnString = new ArrayList<>();
        if (act.equals(Manage.MARK) || act.equals(Manage.UNMARK)) {
            returnString.add(response);
            returnString.add(item.toString());
            return returnString;
        } else {
            returnString.add(response);
            returnString.add(item.toString());
            returnString.add(numOfTask());
            return returnString;
        }
    }

    public String numOfTask() {
        return "Now you have " + items.size() + " tasks in the list.";
    }

    public String getTasksSave() {
        StringBuilder returnBuilder = new StringBuilder();
        for (Task item : items) {
            returnBuilder.append(item.saveFile()); //build the save here
            returnBuilder.append("\n");
        }
        return returnBuilder.toString();
    }

    public ArrayList<String> ListItems() {

        int i = 1;
        ArrayList<String> ret = new ArrayList<>();
        if (items.isEmpty()) {
            ret.add("Your list is empty!!!!Add something! ");
            return ret;
        }
        ret.add(listingResponse);
        for (Task item : items) {
            ret.add(" " + i + "." + item);
            i++;
        }
        return ret;
    }

    public void setUpdate(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public boolean getUpdate() {
        return hasChanged;
    }

    public ArrayList<String> findTask(String search) {
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
            foundTask.add("Sorry I couldn't find anything that fits that search :(");
        }
        return foundTask;
    }


}
