package view;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import model.Task;
import model.TaskList;

public class Ui {
    private BufferedReader br;
    private PrintWriter pw;
    private String divider;

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

    public void close() throws IOException {
        br.close();
        pw.close();
    }

    public String getUserInput() throws IOException {
        pw.print(">>>");
        pw.flush();
        return br.readLine();
    }

    public void showError(Exception e) {
        pw.printf("Error Detected: %s", e.getMessage());
    }

    public void showInvalidCommand(String cmd) {
        pw.printf("I don't understand what you mean by \"%s\"\nPlease request something like:\n"
                + "  bye, list, mark, delete, todo, deadline, event.", cmd);
    }

    public void showGreet(String name) {
        pw.printf("Hello! I'm %s.\nWhat can I do for you?", name);
    }

    public void showBye() {
        pw.printf("Bye. Hope to see you again soon!");
    }

    public void showTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            pw.printf("There are currently no tasks in your list.");
        } else {
            pw.printf("Here are the tasks in your list:\n%s", tasks);
        }
    }

    public void showIndexOutOfBoundsError(TaskList tasks) {
        pw.printf("Task selected does not exist.\nTask number must be between 1 to %d.", tasks.size());
    }

    public void showIndexParseError() {
        pw.printf("Please enter a valid task number!\nOnly numerical letters [0-9] accepted.");
    }

    public void showMarkDone(Task t) {
        pw.printf("Nice! I've marked this task as done:\n  %s", t);
    }

    public void showUnmarkDone(Task t) {
        pw.printf("OK, I've marked this task as not done yet:\n  %s", t);
    }

    public void showDeleteDone(Task t, TaskList tasks) {
        pw.printf("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                t, tasks.size());
    }

    public void showMissingTaskNameError() {
        pw.printf("Please enter a NAME for your task!");
    }

    public void showAddTaskDone(Task t, TaskList tasks) {
        pw.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                t, tasks.size());
    }

    public void showDateTimeParseError(String format, String taskType, String dateType) {
        pw.printf("Please enter a valid DATE and TIME (%s)\nfor your %s task's [%s] date.", format, taskType, dateType);
    }

}
