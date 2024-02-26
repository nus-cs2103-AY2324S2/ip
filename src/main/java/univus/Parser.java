package univus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import univus.task.Deadline;
import univus.task.Event;
import univus.task.Task;
import univus.task.TaskList;
import univus.task.ToDo;


/**
 * Parses user input and performs corresponding actions for the Univus application.
 */
public class Parser {
    /**
     * Parses a user input message to mark a task as done in the given TaskList.
     *
     * @param taskList    The TaskList containing the tasks.
     * @param message     The user input message containing the index of the task to be marked.
     * @param responses   The StringBuilder to store the success or error messages.
     */
    public static void parseMark(TaskList taskList, String message, StringBuilder responses) {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);

            if (index > taskList.size() || index < 1) {
                throw new UnivusException("OOPS!!! Wrong Index!");
            }

            Task task = taskList.getTask(index);
            if (task.isDone()) {
                throw new UnivusException("Already Marked!");
            } else {
                task.mark();
                responses.append("Nice! I've marked this task as done:\n");
                responses.append("\t" + task.toString() + "\n");
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses a user input message to unmark a task as not done in the given TaskList.
     *
     * @param taskList    The TaskList containing the tasks.
     * @param message     The user input message containing the index of the task to be marked.
     * @param responses   The StringBuilder to store the success or error messages.
     */
    public static void parseUnmark(TaskList taskList, String message, StringBuilder responses) {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);

            if (index > taskList.size() || index < 1) {
                throw new UnivusException("OOPS!!! Wrong Index!");
            }

            Task task = taskList.getTask(index);
            if (!task.isDone()) {
                throw new UnivusException("Already Unmarked!");
            } else {
                task.unMark();
                responses.append("OK, I've marked this task as not done yet:\n");
                responses.append("\t" + task.toString() + "\n");
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses and adds a new ToDo task to the provided TaskList based on the user's input message.
     *
     * @param taskList    The TaskList to which the ToDo task will be added.
     * @param message     The user's input message containing the description of the ToDo task.
     * @param responses   A StringBuilder to store the responses or error messages.
     */
    public static void parseTodo(TaskList taskList, String message, StringBuilder responses) {
        try {
            if (message.equals("todo")) {
                throw new UnivusException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                ToDo todo = new ToDo(message);
                taskList.add(todo);
                responses.append("Got it. I've added this task:\n");
                responses.append("\t" + todo.toString() + "\n");
                responses.append("Now you have " + taskList.size() + " tasks in the list.\n");
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses and adds a new Deadline task to the provided TaskList based on the user's input message.
     *
     * @param taskList    The TaskList to which the Deadline task will be added.
     * @param message     The user's input message containing the description and deadline of the task.
     * @param responses   A StringBuilder to store the responses or error messages.
     */
    public static void parseDeadline(TaskList taskList, String message, StringBuilder responses) {
        try {
            if (message.equals("deadline")) {
                throw new UnivusException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                int startIndex = message.indexOf("/");
                String dueDate = message.substring(startIndex + 1);
                String description = message.split("/")[0];
                String pattern1 = "^by \\d{4}-\\d{2}-\\d{2}$";
                Pattern regex1 = Pattern.compile(pattern1);
                Matcher matcher1 = regex1.matcher(dueDate);
                if (matcher1.matches()) {
                    Deadline deadline = new Deadline(description, dueDate);
                    taskList.add(deadline);
                    responses.append("Got it. I've added this task:\n");
                    responses.append("\t" + deadline.toString() + "\n");
                    responses.append("Now you have " + taskList.size() + " tasks in the list.\n");
                } else {
                    throw new UnivusException("OOPS!!! Invalid date format. Please use yyyy-MM-dd.");
                }
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses and adds a new Event task to the provided TaskList based on the user's input message.
     *
     * @param taskList    The TaskList to which the Event task will be added.
     * @param message     The user's input message containing the description, start time, and end time of the event.
     * @param responses   A StringBuilder to store the responses or error messages.
     */
    public static void parseEvent(TaskList taskList, String message, StringBuilder responses) {
        try {
            if (message.equals("event")) {
                throw new UnivusException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String description = message.split("/")[0];
                String start = message.split("/")[1];
                String end = message.split("/")[2];
                Event event = new Event(description, start, end);
                taskList.add(event);
                responses.append("Got it. I've added this task:\n");
                responses.append("\t" + event.toString() + "\n");
                responses.append("Now you have " + taskList.size() + " tasks in the list.\n");
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses and removes a task from the provided TaskList based on the index provided in the message.
     *
     * @param taskList    The TaskList containing the tasks.
     * @param message     The user's input message containing the index of the task to be deleted.
     * @param responses   A StringBuilder to store the responses or error messages.
     */
    public static void parseDelete(TaskList taskList, String message, StringBuilder responses) {
        try {
            int index = Integer.parseInt(message.split(" ")[1]);
            if (index > taskList.size() || index < 1) {
                throw new UnivusException("OOPS!!! Wrong Index!");
            } else {
                Task task = taskList.remove(index);
                responses.append(" Noted. I've removed this task:\n");
                responses.append("\t" + task.toString() + "\n");
                responses.append("Now you have " + taskList.size() + " tasks in the list.\n");
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses and searches for tasks containing a specific keyword in the provided TaskList.
     *
     * @param taskList    The TaskList to search for matching tasks.
     * @param message     The user's input message containing the keyword for searching.
     * @param responses   A StringBuilder to store the responses or error messages.
     */
    public static void parseFind(TaskList taskList, String message, StringBuilder responses) {
        try {
            if (message.equals("find")) {
                throw new UnivusException("OOPS!!! Please indicate the keyword.");
            } else {
                String keyword = message.split(" ")[1];
                TaskList searchingResult = taskList.find(keyword);
                responses.append(" Here are the matching tasks in your list:\n");
                responses.append(searchingResult.list());
            }
        } catch (UnivusException error) {
            responses.append(error.getMessage() + "\n");
        }
    }
    /**
     * Parses the user input message and performs actions based on the message content.
     *
     * @param taskList The TaskList to which tasks are added, marked, or removed.
     * @param message  The user input message to be parsed.
     * @throws UnivusException If an error occurs during the parsing or execution of the command.
     */
    public static String parse(TaskList taskList, String message) throws UnivusException {
        StringBuilder responses = new StringBuilder();
        if (message.equals("list")) {
            responses.append(taskList.list());
        } else if (message.matches("mark\\s\\d+")) {
            parseMark(taskList, message, responses);
        } else if (message.matches("unmark\\s\\d+")) {
            parseUnmark(taskList, message, responses);
        } else if (message.startsWith("todo")) {
            parseTodo(taskList, message, responses);
        } else if (message.startsWith("deadline")) {
            parseDeadline(taskList, message, responses);
        } else if (message.startsWith("event")) {
            parseEvent(taskList, message, responses);
        } else if (message.matches("delete\\s\\d+")) {
            parseDelete(taskList, message, responses);
        } else if (message.startsWith("find")) {
            parseFind(taskList, message, responses);
        } else {
            try {
                throw new UnivusException("OOPS!!! I'm sorry, but I don't know what that means...");
            } catch (UnivusException error) {
                responses.append(error.getMessage() + "\n");
            }
        }
        return responses.toString();
    }
}
