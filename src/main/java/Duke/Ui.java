package Duke;

import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    public Ui() {
        // this.greet(); // uncomment this for CLI
    }

    public void displayLine() {
        System.out.println(gap() + "_".repeat(50));
    }

    public static String gap() {
        return "    ";
    }

    public void wrapInLines(String line) {
        this.displayLine();
        System.out.println(line);
        this.displayLine();
    }

    public void echo(String line) {
        this.wrapInLines(line);
    }

    public void greet() {
        this.wrapInLines(gap() + "Hello! I'm Shirmin" + "\n"
                + gap() + "What can I do for you?");
    }

    public void exit() {
        wrapInLines(gap() + "Bye. Hope to see you again soon!");
    }

    public void displayList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println(this.gap() + "There are no tasks in your list.");
        } else {
            System.out.println(this.gap() + "Here are the tasks in your list:");
            int i = 1;
            for (Task t : list) {
                System.out.println(this.gap() + i + "." + t.toString());
                i++;
            }
        }
        this.displayLine();
    }

    public void outOfRangeError() {
        System.out.println("invalid, out of range");
    }

    public void markTask(Task task) {
        System.out.println(this.gap() + "Nice! I've marked this task as done:");
        System.out.println(this.gap() + this.gap() + task.toString());
        this.displayLine();
    }

    public void unmarkTask(Task task) {
        this.displayLine();
        System.out.println(this.gap() + "OK, I've marked this task as not done yet:");
        System.out.println(this.gap() + this.gap() + task.toString());
        this.displayLine();
    }

    public void deleteTask(String task, ArrayList<Task> taskList) {
        this.displayLine();
        System.out.println(this.gap() + "Ok, I've removed the task:");
        System.out.println(this.gap() + this.gap() + task);
        System.out.println(this.gap() + "You have " + taskList.size() + " tasks remaining in the list.");
        this.displayLine();
    }

    public void unknownCommand() {
        System.out.println("OH NO I'm not sure what that command is. You may use the commands " +
                "todo, deadline, list, event, delete, mark and unmark");
        this.displayLine();
    }

    public <T extends Task> void addMessage(T task, Integer number) {
        // displayLine();
        System.out.println(this.gap() + "Got it. I've added this task:");
        System.out.println(this.gap() + this.gap() + task.toString());
        System.out.println(this.gap() + "Now you have " + number.toString() + " tasks in the list.");
        this.displayLine();
    }

    public void invalidTaskNumber(String number) {
        System.out.println("Invalid task number: " + number);
        this.displayLine();
    }

    public void loadFiles(int num) {
        System.out.println("    I've loaded " + num + " tasks from Duke.txt!");
        this.displayLine();
    }

    public void eventDateError() {
        System.out.println("Error creating event task. Please " +
                "provide a valid from and valid to date, both in the format 'yyyy-MM-dd HHmm'.");
        this.displayLine();
    }

    public void eventFormatError() {
        System.out.println("oopsy doopsy you made a -ucky wucky! The description of a event" +
                " must be in the format 'event [task] /from [time]' /to [time].");
        this.displayLine();
    }

    public void taskOutOfBounds(int num) {
        System.out.println("There are " + num + " numbers, please enter a number from 1 to " + num);
        this.displayLine();
    }

    public void todoFormatError() {
        System.out.println("oopsy doopsy you made a -ucky wucky! The description of a todo cannot be empty.");
        this.displayLine();
    }

    public void deadlineDateError() {
        System.out.println("Error creating deadline task. Please "+
            "provide a valid date in the format 'yyyy-MM-dd HHmm'.");
        this.displayLine();
    }
    public void deadlineFormatError() {
        System.out.println("oopsy doopsy you made a -ucky wucky! The description of a deadline" +
                " must be in the format 'deadline [task] /by [time]'.");
        this.displayLine();
    }
    public void createFileError(IOException e) {
        System.err.println("Error creating 'duke.txt' file: " + e.getMessage());
        this.displayLine();
    }
    public void taskFormatError(String line) {
        System.err.println("Task in file not in correct format or missing parts: " + line);
    }
    public void deadlineMissingBy(String line) {
        System.err.println("Deadline task missing 'by' part: " + line);
    }
    public void eventMissingParam(String line) {
        System.err.println("Event task missing 'from' or 'to' parts: " + line);
    }
    public void unknownTaskType(String type) {
        System.err.println("Unknown task type: " + type);
    }
    public void genericTaskError(Exception e, String line) {
        System.err.println("Error parsing task from file: " + e.getMessage() + " - Line: " + line);
    }
    public void saveError(IOException e) {
        System.err.println("Error saving task to file: " + e.toString());
    }
    public void parseDateError(String dateString) {
        System.err.println("Error parsing date: " + dateString + ". Please use the format 'yyyy-MM-dd HHmm'.");
    }
    public void displayMatchingTasks(ArrayList<Task> matchingTasks) {
        this.displayLine();
        System.out.println("Here are the matching tasks in your list:");
        int taskIndex = 1;
        for (Task task : matchingTasks) {
            System.out.println(taskIndex + "." + task.toString());
            taskIndex++;
        }
        this.displayLine();
    }
    public void findFormatError() {
        System.err.println("Please input command in format 'find ____' e.g. 'find book' ");
    }
}
