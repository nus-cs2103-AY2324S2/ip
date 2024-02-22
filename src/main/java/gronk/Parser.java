package gronk;

import exception.gronk.EmptyListException;
import exception.gronk.UnknownCommandException;
import exception.gronk.WrongDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class.
 * Utility class to parse the user's messages and
 * execute them.
 */
public class Parser {
    /**
     * SingleParameterRunnable interface.
     * Utility interface to convert our parsing methods into Runnable objects.
     */
    private interface SingleParameterRunnable {
        public String run(String message);
    }

    private static UserInterface userInterface;
    private static TaskList taskList;
    private static Map<Pattern, SingleParameterRunnable> methodStore = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String HELP_MESSAGE = "User Guide: https://chengjunyuan.github.io/ip/\n"
            + "(I'm so sorry, you can't click the link,\n"
            + " you can also go to bit.ly/gronk_help)";

    // Patterns used
    private static final String DATE_REGEX = "(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/2[0-9]{3}";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private static final Pattern TODO = Pattern.compile("(?i)todo .+");
    private static final Pattern DEADLINE = Pattern.compile("(?i)deadline .+ /[bB] " + DATE_REGEX);
    private static final Pattern WRONG_DEADLINE = Pattern.compile("(?i)deadline .+ /[bB] .+");
    private static final Pattern EVENT = Pattern.compile("(?i)event .+ /[fF] " + DATE_REGEX + " /[tT] " + DATE_REGEX);
    private static final Pattern WRONG_EVENT = Pattern.compile("(?i)event .+ /[fF].+/[tT].+");
    private static final Pattern LIST = Pattern.compile("(?i)list");
    private static final Pattern MARK = Pattern.compile("(?i)mark ([1-9][0-9]* )*([1-9][0-9]*)");
    private static final Pattern DELETE = Pattern.compile("(?i)delete ([1-9][0-9]* )*([1-9][0-9]*)");
    private static final Pattern FIND = Pattern.compile("(?i)find .+");
    private static final Pattern HELP = Pattern.compile("(?i)help");

    /**
     * Constructor for Parser object.
     * Initializes a UserInterface to interact with
     * the user and a TaskList object to store data in.
     *
     * @param taskList TaskList object.
     */
    public Parser(TaskList taskList) {
        Parser.userInterface = new UserInterface();
        Parser.taskList = taskList;
        Parser.updateMethodStore(); // Stores parser methods in a HashMap for use in main parse() command
    }

    /**
     * Updates methodStore.
     * Puts the various regex Pattern objects and their associated parsing methods
     * together in the methodStore map.
     */
    private static void updateMethodStore() {
        Parser.methodStore.put(TODO, Parser::parseTodo);
        Parser.methodStore.put(DEADLINE, Parser::parseDeadline);
        Parser.methodStore.put(EVENT, Parser::parseEvent);
        Parser.methodStore.put(LIST, Parser::parseList);
        Parser.methodStore.put(MARK, Parser::parseMark);
        Parser.methodStore.put(DELETE, Parser::parseDelete);
        Parser.methodStore.put(FIND, Parser::parseFind);
        Parser.methodStore.put(HELP, Parser::parseHelp);
    }

    /**
     * Parser for user commands.
     * Contains all the logic to deal with user commands,
     * including error handling and initialization of new Task objects.
     *
     * @param message String containing user input.
     * @return String containing response message to user's input.
     */
    public String parse(String message) {
        try {
            String returnMessage = "";
            for (Map.Entry<Pattern, SingleParameterRunnable> entry : Parser.methodStore.entrySet()) {
                Pattern pattern = entry.getKey();
                SingleParameterRunnable method = entry.getValue();

                Matcher matcher = pattern.matcher(message);
                if (matcher.matches() && (pattern.equals(WRONG_DEADLINE) || pattern.equals(WRONG_EVENT))) {
                    throw new WrongDateFormatException();
                }
                if (matcher.matches()) {
                    returnMessage = method.run(message);
                }
            }
            if (returnMessage.equals("")) { // This means that the command matches none of the patterns
                throw new UnknownCommandException();
            } else {
                return returnMessage;
            }
        } catch (WrongDateFormatException | UnknownCommandException e) {
            return e.toString();
        }
    }

    /**
     * Generates a message indicating that a Todo has been created. All messages entering it
     * are guaranteed to follow the Todo message format.
     * @param message
     * @return Message indicating that a Todo has been created.
     */
    private static String parseTodo(String message) {
        assert TODO.matcher(message).matches() : "message should follow TODO format";
        taskList.addTask(new Todo(message.substring(5), 0));
        return "Task added: " + message.substring(5);
    }

    /**
     * Generates a message indicating that a Deadline has been created. All messages entering it
     * are guaranteed to follow the Deadline message format.
     * @param message
     * @return Message indicating that an Event has been created.
     */
    private static String parseDeadline(String message) {
        assert DEADLINE.matcher(message).matches() : "message should follow DEADLINE format";
        String[] words = message.substring(9).split(" /[bB] ");
        String deadlineDescription = words[0];
        String time = words[1]; // Follows dd/MM/yyyy format
        Deadline newTask = Deadline.createDeadline(deadlineDescription, 0, time);
        Parser.taskList.addTask(newTask);
        return "Deadline added: " + words[0];
    }

    /**
     * Generates a message indicating that an Event has been created. All messages entering it
     * are guaranteed to follow the Event message format.
     * @param message
     * @return Message indicating that an Event has been created.
     */
    private static String parseEvent(String message) {
        assert EVENT.matcher(message).matches() : "message should follow EVENT format";
        String[] words = message.substring(6).split(" /[fF] ");
        String eventDescription = words[0];
        String startTime = words[1].substring(0, 10); // Follows dd/MM/yyyy format
        String endTime = words[1].substring(14, 24); // Follows dd/MM/yyyy format
        Event newTask = Event.createEvent(eventDescription, 0, startTime, endTime);
        Parser.taskList.addTask(newTask);
        return "Event added: " + words[0];
    }

    /**
     * Generates a list of all tasks.
     * @param message
     * @return Lists all tasks, their completion status, and dates (where applicable).
     */
    private static String parseList(String message) {
        assert LIST.matcher(message).matches() : "message should follow LIST format";
        return Parser.userInterface.returnAllTasks(Parser.taskList);
    }

    /**
     * Takes in a variable number of integers and marks the tasks with those integers as their indexes.
     * @param indexes
     * @return Generates a message stating which tasks have been marked.
     */
    private static String parseMarkTasks(Integer... indexes) {
        String returnMessage = "";
        for (int index: indexes) {
            returnMessage += Parser.taskList.getTask(index - 1).statusMessage() + "\n";
            Parser.taskList.getTask(index - 1).flip();
        }
        returnMessage = returnMessage.substring(0, returnMessage.lastIndexOf("\n"));
        return returnMessage;
    }

    /**
     * Generates a message indicating a task has been marked or unmarked. Multiple indexes are to be
     * separated by spaces.
     * @param message
     * @return Generates a message stating which tasks have been marked.
     */
    private static String parseMark(String message) {
        assert MARK.matcher(message).matches() : "message should follow MARK format";
        Integer[] indexes = Arrays.stream(message.substring(5).split(" "))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        return Parser.parseMarkTasks(indexes);
    }

    /**
     * Takes in a variable number of integers and deletes the tasks with those integers as their indexes.
     * @param indexes
     * @return Generates a message stating which tasks have been deleted.
     */
    private static String parseDeleteTasks(Integer... indexes) {
        try {
            String returnMessage = "";
            int size = Parser.taskList.getSize();
            if (size == 0) {
                throw new EmptyListException();
            }
            for (int index: indexes) {
                if (index > size || index <= 0) {
                    throw new NoSuchElementException();
                }
                returnMessage = "Item: " + Parser.taskList.getTask(index - 1).getDesc()
                        + " removed from list." + "\n" + returnMessage;
                Parser.taskList.deleteTask(index - 1);
            }
            returnMessage = returnMessage.substring(0, returnMessage.lastIndexOf("\n"));
            return returnMessage;
        } catch (EmptyListException | NoSuchElementException e) {
            Parser.userInterface.printMessage(e.toString());
            return "";
        }
    }

    /**
     * Generates a message indicating that a task has been deleted.
     * @param message
     * @return Message indicating a task has been deleted.
     */
    private static String parseDelete(String message) {
        assert DELETE.matcher(message).matches() : "message should follow DELETE format";
        Integer[] indexes = Arrays.stream(message.substring(7).split(" "))
                .map(Integer::valueOf)
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
        return Parser.parseDeleteTasks(indexes);
    }

    /**
     * Lists all Todo objects.
     * @return Lists all Todo objects.
     */
    private static String parseFindTodos() {
        TaskList matchedTasks = new TaskList();
        for (Task task: Parser.taskList.getAllTasks()) {
            if (task instanceof Todo) {
                matchedTasks.addTask(task);
            }
        }
        return Parser.userInterface.returnAllTasks(matchedTasks);
    }

    /**
     * Lists all Deadline objects.
     * @return Lists all Deadline objects.
     */
    private static String parseFindDeadlines() {
        TaskList matchedTasks = new TaskList();
        for (Task task: Parser.taskList.getAllTasks()) {
            if (task instanceof Deadline) {
                matchedTasks.addTask(task);
            }
        }
        return Parser.userInterface.returnAllTasks(matchedTasks);
    }

    /**
     * Lists all Event objects.
     * @return Lists all Event objects.
     */
    private static String parseFindEvents() {
        TaskList matchedTasks = new TaskList();
        for (Task task: Parser.taskList.getAllTasks()) {
            if (task instanceof Event) {
                matchedTasks.addTask(task);
            }
        }
        return Parser.userInterface.returnAllTasks(matchedTasks);
    }

    /**
     * Lists all Deadline and Event objects with a date or end date before the specified date.
     * @return Lists all Deadline and Event objects matching the above criteria.
     */
    private static String parseFindByDate(String date) {
        assert DATE_PATTERN.matcher(date).matches() : "message should follow DATE_REGEX format";
        LocalDate time = LocalDate.parse(date, DATE_FORMAT);
        TaskList matchedTasks = new TaskList();
        for (Task task: Parser.taskList.getAllTasks()) {
            if (task instanceof Deadline) {
                LocalDate taskTime = LocalDate.parse(((Deadline) task).getTime(), DATE_FORMAT);
                if (taskTime.isBefore(time)) {
                    matchedTasks.addTask(task);
                }
            }
            if (task instanceof Event) {
                LocalDate taskTime = LocalDate.parse(((Event) task).getEndTime(), DATE_FORMAT);
                if (taskTime.isBefore(time)) {
                    matchedTasks.addTask(task);
                }
            }
        }
        return Parser.userInterface.returnAllTasks(matchedTasks);
    }

    /**
     * Generates a list of tasks with a matching description.
     * @param message
     * @return Lists all tasks with a matching description.
     */
    private static String parseFind(String message) {
        assert FIND.matcher(message).matches() : "message should follow FIND format";
        if (message.length() >= 7) { // Checks if there is a special command
            switch (message.substring(5, 7)) {
            case "/t": // Finds tasks
                return Parser.parseFindTodos();
            case "/d": // Finds deadlines
                return Parser.parseFindDeadlines();
            case "/e": // Finds events
                return Parser.parseFindEvents();
            case "/b": // Finds deadlines and events before a certain date
                return Parser.parseFindByDate(message.substring(8));
            }
        }
        String searchString = message.substring(5);
        TaskList matchedTasks = new TaskList();
        for (Task task: Parser.taskList.getAllTasks()) {
            if (task.getDesc().indexOf(searchString) != -1) {
                matchedTasks.addTask(task);
            }
        }
        return Parser.userInterface.returnAllTasks(matchedTasks);
    }

    /**
     * Generates a help message.
     * @return Generates a simplified help message.
     */
    private static String parseHelp(String message) {
        return HELP_MESSAGE;
    }
}
