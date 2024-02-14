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
    private static final String LINE = "____________________________________________________________";

    /**
     * Parses the user input message and performs actions based on the message content.
     *
     * @param taskList The TaskList to which tasks are added, marked, or removed.
     * @param message  The user input message to be parsed.
     * @throws UnivusException If an error occurs during the parsing or execution of the command.
     */
    public static void parse(TaskList taskList, String message) throws UnivusException {
        if (message.equals("list")) {
            System.out.println(LINE);
            taskList.list();
            System.out.println(LINE);
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
                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("\t" + task.toString());
                    System.out.println(LINE);
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
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
                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("\t" + task.toString());
                    System.out.println(LINE);
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
            }
        } else if (message.startsWith("todo")) {
            try {
                if (message.equals("todo")) {
                    throw new UnivusException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    ToDo todo = new ToDo(message);
                    taskList.add(todo);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + todo.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
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
                        System.out.println(LINE);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("\t" + deadline.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println(LINE);
                    } else {
                        throw new UnivusException("OOPS!!! Invalid date format. Please use yyyy-MM-dd.");
                    }
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
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
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + event.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
            }
        } else if (message.matches("delete\\s\\d+")) {
            try {
                int index = Integer.parseInt(message.split(" ")[1]);
                if (index > taskList.size() || index < 1) {
                    throw new UnivusException("OOPS!!! Wrong Index!");
                } else {
                    Task task = taskList.remove(index);
                    System.out.println(LINE);
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("\t" + task.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
            }
        } else if (message.startsWith("find")) {
            try {
                if (message.equals("find")) {
                    throw new UnivusException("OOPS!!! Please indicate the keyword.");
                } else {
                    String keyword = message.split(" ")[1];
                    TaskList searchingResult = taskList.find(keyword);
                    System.out.println(LINE);
                    System.out.println(" Here are the matching tasks in your list:");
                    searchingResult.list();
                    System.out.println(LINE);
                }
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
            }
        } else {
            try {
                throw new UnivusException("OOPS!!! I'm sorry, but I don't know what that means...");
            } catch (UnivusException error) {
                System.out.println(LINE);
                System.out.println(error.getMessage());
                System.out.println(LINE);
            }
        }
    }
}
