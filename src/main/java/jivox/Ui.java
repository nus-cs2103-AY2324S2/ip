package jivox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import jivox.task.Task;
import jivox.task.TaskList;


/**
 * Ui provides a text-based user interface for the todo list application.
 * It handles user input and output.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Gets user input from the command line.
     *
     * @return The next line entered by the user.
     */
    public String input() {
        return this.sc.nextLine();
    }

    /**
     * Closes the Scanner.
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Prints a greeting message.
     */
    public String greet() {
        return "Hello! I'm Jivox \n What can I do for you?";
    }

    /**
     * Prints a message that a task was marked as completed.
     *
     * @param t The task that was marked.
     */
    public String showMark(Task t) {
        return String.format("Nice! , I've marked this task :\n %s \n", t.toString());
    }

    /**
     * Prints a message that a task was deleted.
     *
     * @param t The deleted task.
     * @param tasksLeft The number of tasks remaining.
     */
    public String showDelete(Task t, int tasksLeft) {
        return String.format("Noted. I've removed this task:\n %s \n"
                + " Now you have %d Tasks in the List\n", t.toString(), tasksLeft);
    }

    /**
     * Prints a message that a task was unmarked as completed.
     *
     * @param t The task that was unmarked.
     */
    public String showUnmark(Task t) {
        return String.format("OK, I've Unmarked this task :\n %s \n",
                t.toString());
    }

    /**
     * Prints a message that a new task was added.
     *
     * @param t The new task.
     * @param numOfTasks The new number of tasks.
     */
    public String showAdd(Task t, int numOfTasks) {
        return String.format("Got it. I've added this task:\n %s \n Now you have %d tasks in the list\n",
                t.toString(), numOfTasks);
    }

    /**
     * Prints the list of tasks.
     *
     * @param list The task list.
     */
    public String showTasks(TaskList list) {
        if (list.getLength() == 0) {
            return "You've No task in the List!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.getLength(); i++) {
                sb.append(String.format("%d. %s\n", (i + 1), list.getTask(i)));
            }
            return "You have Following tasks in your List:- \n" + sb;
        }
    }

    /**
     * Prints all the tasks that contains the input(String)
     * in their description
     * @param list current list of tasks.
     * @param input input by the user to query in tasks.
     */

    public String showFind(TaskList list, String input) {
        List<Task> matchedTasks = list.getTasks().stream().filter(t -> t.contains(input)).collect(Collectors.toList());

        if (matchedTasks.isEmpty()) {
            return "No Matching tasks found in the list!";
        } else {
            return "Following are Matching tasks in your list:-\n"
                    + matchedTasks.stream()
                            .map(Task::toString)
                            .collect(Collectors.joining("\n"));
        }
    }

    /**
     * Prints an exit message.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints tasks that are due on the given date.
     *
     * @param list The task list.
     * @param time The date to check for due tasks.
     */
    public String showDeadline(TaskList list, LocalDate time) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.getLength(); i++) {
            LocalDateTime deadline = list.getTask(i).getDeadline();
            if (deadline != null) {
                if (deadline.getMonth() == time.getMonth() && deadline.getYear() == time.getYear()
                        && deadline.getDayOfMonth() == time.getDayOfMonth()) {
                    sb.append(String.format("%s \n", list.getTask(i)));
                }
            }
        }
        return "You have following Task due on "
                + time.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":-\n" + sb;
    }


    public String showTag(Task t, String tag) {
        return String.format("Following task has been tagged with %s :- \n %s", tag , t);
    }

    /**
     * Prints an exception message.
     *
     * @param message The message to print.
     */
    public String showException(String message) {
        return message;
    }
}
