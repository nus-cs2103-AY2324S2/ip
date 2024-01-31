import java.util.Scanner;


public class Ui {
    private final Scanner scanner;
    private static final String AvailableCommand = "Now there are 3 ways to add a Task to list (Replace your <task> with your task name:\n\t1.todo <task>\n\t2.deadline <task> by <MM-dd hh:mm am/pm or MM-dd> (where mm is minute and MM is month)\n\t3.event <task>\n\t\t3.1event <task> <mm:hh am/pm> to <mm:hh am/pm> \n\t\t3.2event <task> <MM/dd> <mm:hh am/pm> to <mm:hh am/pm>";
    private static final String AvailableListOperation = "Here is what you can do with the tasks once added to the list:\n\t 1.mark (to make a mark on the task) <the number of task on the list>\n\t 2.unmark (to unmark a task on the list) <the number of task on the list>\n\t 3.remove (to remove a task from the list) <the number of task on the list>\n\t 4.list (to show the current list)";
    private static final String welcome_message = "HASSNT:\n" +
            "Hello! I'm HASSTNT " + "What can I do for you?\n"+"to see what I can do type --commands\n" + "-- our date format starts with month  instead of day (ie. mm-dd) --";
    private static final String goodbye_message = "HASSNT:\n" +
            "Bye. Hope to see you again soon!";

    public Ui() {
        // initialize a scanner
        scanner = new Scanner(System.in);

    }

    public void displayWelcomeMessage(){
        System.out.println(welcome_message);
    }
    public void displayByeMessage(){
        System.out.println(goodbye_message);
    }
    public void displayErrorMessage(String errorMessage){
        System.out.println(errorMessage);
    }
    public void display(String s){
        System.out.println(s);
    }
    public String getUserInput() {
        // Prompt the user to enter input
        System.out.print("Your input: ");
        // Read the user input as a line of text
        return scanner.nextLine().trim().toLowerCase();
    }
    public void displayCommand(){
        System.out.println(AvailableCommand);
        System.out.println(AvailableListOperation);
    }
}
