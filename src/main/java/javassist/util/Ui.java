package javassist.util;

import java.io.InputStream;
import java.util.Scanner;

import javassist.task.Task;


/**
 * Represents the mechanism of a user interface.
 */
public class Ui {
    private static final String LINE = "\t________________________________________________________________";

    private String name;
    private String logo;
    private Scanner scanner;

    /**
     * Constructs a UI object.
     *
     * @param name
     * @param logo
     * @param inputStream
     */
    public Ui(String name, String logo, InputStream inputStream) {
        this.name = name;
        this.logo = logo;
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcome() {
        String instruction = "Try command keywords: todo, deadline, event, list, mark, unmark, delete, find,"
                + " add, total, list_e, deduct.\n";
        String joinInstruction = "To combine instructions in a single command, use ' || '";
        return "Hello! I'm " + this.name + "." + "\nWhat can I do for you?\n\n" + instruction + joinInstruction;
    }

    /**
     * Reads command using Scanner.
     *
     * @return String read from InputStream.
     */
    public String readCommand() {
        System.out.println();
        return scanner.nextLine();
    }

    /**
     * Displays line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns error message.
     *
     * @param errorMessage Details about the error to be displayed.
     * @return Error message.
     */
    public String showError(String errorMessage) {
        return "Uh-oh!!! " + errorMessage;
    }

    /**
     * Returns error message due to reading from file.
     *
     * @return Error message.
     */
    public String showLoadingError() {
        return "Uh-oh!!! Error loading tasks from file.";
    }

    /**
     * Returns notification on successful mark operation.
     *
     * @param task Task that was marked as done.
     * @return Success message.
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:" + "\n\t" + task.printTask();
    }

    /**
     * Returns notification on successful unmark operation.
     *
     * @param task Task that was marked as not done.
     * @return Success message.
     */
    public String showUnmarked(Task task) {
        return "Ok, I've marked this task as not done yet:" + "\n\t" + task.printTask();
    }

    /**
     * Returns notification on successful add operation.
     *
     * @param t Task that was added.
     * @param list Holds all tasks added.
     * @return Success message.
     */
    public String showAdded(Task t, TaskList list) {
        int size = list.getSize();
        return "Got it! I've added this task:\n\t" + t.printTask()
                + "\nNow you have " + size + (size > 1 ? " tasks" : " task") + " in the list.";
    }

    /**
     * Returns all tasks in list.
     * Outputs message if list is empty.
     *
     * @param list Holds all tasks added
     * @return String of tasks.
     */
    public String showTasks(TaskList list) {
        if (list.getSize() == 0) {
            return "Uh-oh!!! No task in list." + "\nYou may add task with keywords: todo, deadline, event.";
        }
        return list.print();
    }

    /**
     * Returns notification on successful delete operation.
     *
     * @param t Task that was deleted.
     * @param list Holds all tasks remaining.
     * @return Success message.
     */
    public String showDeleted(Task t, TaskList list) {
        String multiplicity = list.getSize() > 1 ? " tasks" : " task";
        return "Noted I've removed this task:" + "\n\t" + t.printTask()
                + "\nNow you have " + list.getSize() + multiplicity + " in the list.";
    }


    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns all tasks in list.
     *
     * @param list Holds all tasks that matches search.
     * @return String of tasks.
     */
    public String showMatchedTasks(TaskList list) {
        if (list.getSize() == 0) {
            return "Uh-oh!!! No task matches you search.";
        }
        return list.printFound();
    }

    /**
     * Returns notification on successful addition to expense.
     *
     * @param list List of expenses.
     * @return Success message.
     */
    public String showAddedExpense(ExpenseList list) {
        String message = "Ca-Ching! Expense incremented. Here's the list of updated expenses:\n";
        return message + list.print();
    }

    /**
     * Returns notification on successful deduction from expense.
     *
     * @param list List of expenses.
     * @return Success message.
     */
    public String showDeductedExpense(ExpenseList list) {
        String message = "Ca-Ching! Expense deducted. Here's the list of updated expenses:\n";
        return message + list.print();
    }

    /**
     * Returns sum of expenses.
     *
     * @param list List of expenses.
     * @return String of message and sum.
     */
    public String showSumExpenses(ExpenseList list) {
        String message = "Total expenses: $";
        return message + list.calculateSum();
    }

    /**
     * Returns a list of categories and expenses.
     *
     * @param list List of expenses.
     * @return String of expenses.
     */
    public String printExpenses(ExpenseList list) {
        return "Here are the expenses for each category:\n" + list.print();
    }


}
