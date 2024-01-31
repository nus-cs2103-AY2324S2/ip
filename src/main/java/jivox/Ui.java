package jivox;
import jivox.task.Task;
import jivox.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Ui provides a text-based user interface for the todo list application.
 * It handles user input and output.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

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
    public void close(){
        this.sc.close();
    }

    /**
     * Prints a greeting message.
     */
    public void greet(){
        addDivider();
        System.out.println("Hello! I'm Jivox");
        System.out.println("What can I do for you?");
        addDivider();
    }

    /**
     * Prints a message that a task was marked as completed.
     *
     * @param t The task that was marked.
     */
    public void showMark(Task t){
        addDivider();
        System.out.println("Nice! , I've marked this task :\n" + t);
        addDivider();
    }

    /**
     * Prints a message that a task was deleted.
     *
     * @param t The deleted task.
     * @param tasksLeft The number of tasks remaining.
     */
    public void showDelete(Task t, int tasksLeft){
        addDivider();
        System.out.println("Noted. I've removed this task:\n" + t);
        System.out.println("Now you have " + tasksLeft +" Tasks in the List");
        addDivider();
    }

    /**
     * Prints a message that a task was unmarked as completed.
     *
     * @param t The task that was unmarked.
     */
    public void showUnmark(Task t){
        addDivider();
        System.out.println("OK, I've Unmarked this task :\n" + t);
        addDivider();
    }

    /**
     * Prints a message that a new task was added.
     *
     * @param t The new task.
     * @param numOfTasks The new number of tasks.
     */
    public void showAdd(Task t, int numOfTasks){
        addDivider();
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + numOfTasks +" tasks in the list.");
        addDivider();
    }

    /**
     * Prints the list of tasks.
     *
     * @param list The task list.
     */
    public void showList(TaskList list){
        if(list.getLength() == 0){
            System.out.println("You've No task in the List!");
        }
        else {
            System.out.println("You have Following tasks in your List:- ");
            addDivider();
            for (int i = 0; i < list.getLength(); i++) {
                System.out.println((i + 1) + ". " + list.getTask(i));
            }
            addDivider();
        }
    }

    /**
     * Prints a divider line.
     */
    public void addDivider(){
        System.out.println("============================================================");
    }

    /**
     * Prints an exit message.
     */
    public void exit(){
        addDivider();
        System.out.println("Bye. Hope to see you again soon!");
        addDivider();
    }

    /**
     * Prints tasks that are due on the given date.
     *
     * @param list The task list.
     * @param time The date to check for due tasks.
     */
    public void showDeadline(TaskList list, LocalDate time){
        System.out.println("You have following Task due on " + time.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":-");
        for(int i = 0; i < list.getLength(); i++){
            LocalDateTime deadline = list.getTask(i).getDeadline();
            if(deadline != null){
                if(deadline.getMonth() == time.getMonth() && deadline.getYear() == time.getYear() && deadline.getDayOfMonth() == time.getDayOfMonth()){
                    addDivider();
                    System.out.println(list.getTask(i));
                }
            }
        }
        addDivider();
    }

    /**
     * Prints an exception message.
     *
     * @param e The exception to print.
     */
    public void showException(Exception e){
        System.out.println(e.getMessage());
    }
}
