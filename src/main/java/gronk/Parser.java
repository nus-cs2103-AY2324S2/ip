package gronk;

import exception.gronk.UnknownCommandException;
import exception.gronk.WrongDateFormatException;

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

    // Patterns used
    private static final String DATE_REGEX = "(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/2[0-9]{3}";
    private static final Pattern TODO = Pattern.compile("(?i)todo .+");
    private static final Pattern DEADLINE = Pattern.compile("(?i)deadline .+ /[bB] " + DATE_REGEX);
    private static final Pattern WRONG_DEADLINE = Pattern.compile("(?i)deadline .+ /[bB] .+");
    private static final Pattern EVENT = Pattern.compile("(?i)event .+ /[fF] " + DATE_REGEX + " /[tT] " + DATE_REGEX);
    private static final Pattern WRONG_EVENT = Pattern.compile("(?i)event .+ /[fF].+/[tT].+");
    private static final Pattern LIST = Pattern.compile("(?i)list");
    private static final Pattern MARK = Pattern.compile("(?i)mark [1-9][0-9]*");
    private static final Pattern DELETE = Pattern.compile("(?i)delete [1-9][0-9]*");
    private static final Pattern FIND = Pattern.compile("(?i)find .+");

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
            Parser.userInterface.printMessage(e.toString());
            return "";
        }
    }

    /**
     * Generates a message indicating that a Todo has been created. All messages entering it
     * are guaranteed to follow the Todo message format.
     * @param message
     * @return Message indicating that a Todo has been created.
     */
    private static String parseTodo(String message) {
        taskList.addTask(new Todo(message.substring(5), 0));
        return "\tTask added: " + message.substring(5);
    }

    /**
     * Generates a message indicating that a Deadline has been created. All messages entering it
     * are guaranteed to follow the Deadline message format.
     * @param message
     * @return Message indicating that an Event has been created.
     */
    private static String parseDeadline(String message) {
        String[] words = message.substring(9).split(" /[bB] ");
        String deadlineDescription = words[0];
        String time = words[1]; // Follows dd/MM/yyyy format
        Deadline newTask = Deadline.createDeadline(deadlineDescription, 0, time);
        Parser.taskList.addTask(newTask);
        return "\tDeadline added: " + words[0];
    }

    /**
     * Generates a message indicating that an Event has been created. All messages entering it
     * are guaranteed to follow the Event message format.
     * @param message
     * @return Message indicating that an Event has been created.
     */
    private static String parseEvent(String message) {
        String[] words = message.substring(6).split(" /[fF] ");
        String eventDescription = words[0];
        String startTime = words[1].substring(0, 10); // Follows dd/MM/yyyy format
        String endTime = words[1].substring(14, 24); // Follows dd/MM/yyyy format
        Event newTask = Event.createEvent(eventDescription, 0, startTime, endTime);
        Parser.taskList.addTask(newTask);
        return "\tEvent added: " + words[0];
    }

    /**
     * Generates a list of all tasks.
     * @param message
     * @return Lists all tasks, their completion status, and dates (where applicable).
     */
    private static String parseList(String message) {
        return Parser.userInterface.returnAllTasks(Parser.taskList);
    }

    /**
     * Generates a message indicating a task has been marked or unmarked
     * @param message
     * @return Marks or unmarks a task by their index
     */
    private static String parseMark(String message) {
        String markedIndex = message.substring(5);
        int index = Integer.parseInt(markedIndex) - 1;
        String returnMessage = Parser.taskList.getTask(index).statusMessage();
        Parser.taskList.getTask(index).flip();
        return returnMessage;
    }

    /**
     * Generates a message indicating that a task has been deleted.
     * @param message
     * @return Message indicating a task has been deleted.
     */
    private static String parseDelete(String message) {
        try {
            String deletedIndex = message.substring(7);
            int index = Integer.parseInt(deletedIndex) - 1;
            int size = Parser.taskList.getSize();
            if (size == 0) {
                throw new EmptyListException();
            } else if (index >= size || index < 0) {
                throw new NoSuchElementException();
            } else {
                String returnMessage = "\tItem: " + Parser.taskList.getTask(index).getDesc()
                        + " removed from list.";
                Parser.taskList.deleteTask(index);
                return returnMessage;
            }
        } catch (EmptyListException | NoSuchElementException e) {
            Parser.userInterface.printMessage(e.toString());
            return "";
        }
    }

    /**
     * Generates a list of tasks with a matching description.
     * @param message
     * @return Lists all tasks with a matching description.
     */
    private static String parseFind(String message) {
        String searchString = message.substring(5);
        TaskList matchedTasks = new TaskList();
        for (Task task: Parser.taskList.getAllTasks()) {
            if (task.getDesc().indexOf(searchString) != -1) {
                matchedTasks.addTask(task);
            }
        }
        return Parser.userInterface.returnAllTasks(matchedTasks);
    }
}
