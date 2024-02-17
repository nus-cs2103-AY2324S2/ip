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
     * Parses the user input message and performs actions based on the message content.
     *
     * @param taskList The TaskList to which tasks are added, marked, or removed.
     * @param message  The user input message to be parsed.
     * @throws UnivusException If an error occurs during the parsing or execution of the command.
     */
    public static String parse(TaskList taskList, String message) throws UnivusException {
        StringBuilder response = new StringBuilder();
        if (message.equals("list")) {
            response.append(taskList.list());
        } else if (message.matches("mark\\s\\d+")) {
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
                    response.append("Nice! I've marked this task as done:\n");
                    response.append("\t" + task.toString() + "\n");
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else if (message.matches("unmark\\s\\d+")) {
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
                    response.append("OK, I've marked this task as not done yet:\n");
                    response.append("\t" + task.toString() + "\n");
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else if (message.startsWith("todo")) {
            try {
                if (message.equals("todo")) {
                    throw new UnivusException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    ToDo todo = new ToDo(message);
                    taskList.add(todo);
                    response.append("Got it. I've added this task:\n");
                    response.append("\t" + todo.toString() + "\n");
                    response.append("Now you have " + taskList.size() + " tasks in the list.\n");
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else if (message.startsWith("deadline")) {
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
                        response.append("Got it. I've added this task:\n");
                        response.append("\t" + deadline.toString() + "\n");
                        response.append("Now you have " + taskList.size() + " tasks in the list.\n");
                    } else {
                        throw new UnivusException("OOPS!!! Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else if (message.startsWith("event")) {
            try {
                if (message.equals("event")) {
                    throw new UnivusException("OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String description = message.split("/")[0];
                    String start = message.split("/")[1];
                    String end = message.split("/")[2];
                    Event event = new Event(description, start, end);
                    taskList.add(event);
                    response.append("Got it. I've added this task:\n");
                    response.append("\t" + event.toString() + "\n");
                    response.append("Now you have " + taskList.size() + " tasks in the list.\n");
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else if (message.matches("delete\\s\\d+")) {
            try {
                int index = Integer.parseInt(message.split(" ")[1]);
                if (index > taskList.size() || index < 1) {
                    throw new UnivusException("OOPS!!! Wrong Index!");
                } else {
                    Task task = taskList.remove(index);
                    response.append(" Noted. I've removed this task:\n");
                    response.append("\t" + task.toString() + "\n");
                    response.append("Now you have " + taskList.size() + " tasks in the list.\n");
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else if (message.startsWith("find")) {
            try {
                if (message.equals("find")) {
                    throw new UnivusException("OOPS!!! Please indicate the keyword.");
                } else {
                    String keyword = message.split(" ")[1];
                    TaskList searchingResult = taskList.find(keyword);
                    response.append(" Here are the matching tasks in your list:\n");
                    response.append(searchingResult.list());
                }
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        } else {
            try {
                throw new UnivusException("OOPS!!! I'm sorry, but I don't know what that means...");
            } catch (UnivusException error) {
                response.append(error.getMessage() + "\n");
            }
        }
        return response.toString();
    }
}
