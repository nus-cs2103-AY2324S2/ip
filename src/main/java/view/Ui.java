package view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import model.Task;
import model.TaskList;

/**
 * Main User Interface class for the Zero bot.
 * 
 * <p> Handles display to the user, and reading inputs from the user.
 */
public class Ui {
    private BufferedReader br;
    private PrintWriter pw;
    private String divider;

    /**
     * Initialises the input reader and output writer.
     * 
     * <p> Wraps all output messages with the divider provided.
     * 
     * @param input User input reader.
     * @param output User display output writer.
     * @param d Divider used for wrapping all output messages.
     */
    public Ui(Reader input, Writer output, String d) {
        br = new BufferedReader(input);
        pw = new PrintWriter(output) {
            @Override
            public PrintWriter printf(String format, Object... args) {
                return super.printf(divider + "\n" + format + "\n" + divider + "\n", args);
            }
        };
        divider = d;
    }

    /**
     * Closes the input reader and output writer.
     * 
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException {
        br.close();
        pw.close();
    }

    public String getUserInput() throws IOException {
        pw.print(">>>");
        pw.flush();
        return br.readLine();
    }

    /**
     * Prints a generic error message with the detail message string of the exception into the UI.
     * 
     * <p>Informs the user that an unexpected error was encountered.
     * 
     * @param e Exception with detail message string.
     */
    public void showError(Exception e) {
        pw.printf("Error Detected: %s", e.getMessage());
    }

    /**
     * Prints an error message with the user's input into the UI.
     * 
     * <p>Informs the user that the command entered is not a valid command.
     * 
     * @param cmd Invalid user input.
     */
    public void showInvalidCommand(String cmd) {
        pw.printf("I don't understand what you mean by \"%s\"\nPlease request something like:\n"
                + "  bye, list, mark, delete, todo, deadline, event.", cmd);
    }

    /**
     * Prints a greeting with the bot's name.
     * 
     * @param name Name of the bot.
     */
    public void showGreet(String name) {
        pw.printf("Hello! I'm %s.\nWhat can I do for you?", name);
    }

    /**
     * Prints a goodbye message.
     */
    public void showBye() {
        pw.printf("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the tasks in the format:
     * <blockquote><pre>
     *  1.
     *  2.
     *  3.
     * </pre></blockquote><p>
     * if there are tasks in the supplied {@code TaskList}. Else, prints a no tasks message into the UI.
     * 
     * @param tasks {@code TaskList} object to print.
     */
    public void showTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            pw.printf("There are currently no tasks in your list.");
        } else {
            pw.printf("Here are the tasks in your list:\n%s", tasks);
        }
    }

    /**
     * Prints an error message into the UI.
     * 
     * <p>Informs the user that the task selected does not exist.
     * 
     * @param nTasks Number of tasks in the {@code TaskList}.
     */
    public void showIndexOutOfBoundsError(int nTasks) {
        pw.printf("Task selected does not exist.\nTask number must be between 1 to %d.", nTasks);
    }

    /**
     * Prints an error message into the UI.
     * 
     * <p>Informs the user that the task number entered contains illegal characters that could not be parsed.
     */
    public void showIndexParseError() {
        pw.printf("Please enter a valid task number!\nOnly numerical letters [0-9] accepted.");
    }

    public void showMarkDone(Task t) {
        pw.printf("Nice! I've marked this task as done:\n  %s", t);
    }

    public void showUnmarkDone(Task t) {
        pw.printf("OK, I've marked this task as not done yet:\n  %s", t);
    }

    public void showDeleteDone(Task t, int nTasks) {
        pw.printf("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                t, nTasks);
    }

    public void showMissingTaskNameError() {
        pw.printf("Please enter a NAME for your task!");
    }

    public void showAddTaskDone(Task t, int nTasks) {
        pw.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                t, nTasks);
    }

    /**
     * Prints an error message into the UI.
     * 
     * <p> Informs the user that the input date and time in a field is in the wrong format.
     * 
     * @param format Date and time format the user should follow.
     * @param taskType Type of task - Deadline / Event / etc.
     * @param dateType The field the error was encountered in - By / To / From.
     */
    public void showDateTimeParseError(String format, String taskType, String dateType) {
        pw.printf("Please enter a valid DATE and TIME (%s)\nfor your %s task's [%s] date.", format, taskType, dateType);
    }

    public void showAllMatchingTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            pw.printf("No matching tasks found.");
        } else {
            pw.printf("Here are the matching tasks in your list:\n%s", tasks);
        }
    }

    public void showMissingFindArgError() {
        pw.printf("Please enter a TEXT to find the Task!");
    }
}
