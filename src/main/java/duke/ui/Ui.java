package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {
    public void echo(Task task, TaskList list) {
        String echo = "Got it. I've added this task:\n" + "  " + task.toString() + "\n"
            + "Now you have " + (list.size() + 1) + " tasks in the list"
            + "\n___________________________________" ;
        System.out.println(echo);
        list.add(task);
    }
    
    public void greeting() {
        String greeting = "___________________________________\n"
            + "Hello! I'm Jinni\n"
            + "What can I do for you?\n"
            + "___________________________________" ;
        System.out.println(greeting);
    }
    
    public void listing(TaskList list) {
        System.out.println("Here are the tasks in your list\n");
        if (list.size() == 0) {
            System.out.println("\n___________________________________");
        } else {
            int num = 1;
            for (Task t : list.getList()) {
                System.out.println(num + "." + t.toString() + "\n");
                num++;
            }
            System.out.println("___________________________________");
        }
    }
    
    public void marking(Task t) {
        System.out.println("Nice! I have marked this task as done\n");
        System.out.println(t.toString());
        System.out.println("___________________________________");
    }
    
    public void unmarking(Task t) {
        System.out.println("Ok, I've marked this task as not done yet\n");
        System.out.println(t.toString());
        System.out.println("___________________________________");
    }
    
    public void deleting(Task t, TaskList list) {
        String toPrint = "Noted. I've removed this task:\n" + "  " + t.toString() + "\n"
            + "Now you have " + (list.size() - 1) + " tasks in  the list"
            + "\n___________________________________" ;
        System.out.println(toPrint);
    }
    
    public void bye() {
        String bye = "___________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "___________________________________";
        System.out.println(bye);
    }
    
    public void loadingError() {
        System.out.println("File not found");
    }
    
    public void changingFileError() {
        System.out.println("File not found");
    }
    
    public void handleMarkError(String inputFromUser, TaskList list) throws DukeException {
        if (Integer.parseInt(inputFromUser.substring(5)) > list.size()) {
            throw new DukeException("You do not have that many tasks");
        }
        if (Integer.parseInt(inputFromUser.substring(5)) < 1) {
            throw new DukeException("No negative task number");
        }
    }
    
    public void handleUnmarkError(String inputFromUser, TaskList list) throws DukeException {
        if (Integer.parseInt(inputFromUser.substring(7)) > list.size()) {
            throw new DukeException("You do not have that many tasks");
        }
        if (Integer.parseInt(inputFromUser.substring(7)) < 1) {
            throw new DukeException("No negative task number");
        }
    }
    
    public void handleTodoError(String inputFromUser) throws DukeException {
        if (!(inputFromUser.substring(4).matches(".*\\S.*"))) {
            throw new DukeException("Description of the task can't be empty");
        }
    }
    
    public void handleDeadlineError(String inputFromUser) throws DukeException {
        if (!(inputFromUser.substring(8).matches(".*\\S.*"))) {
            throw new DukeException("Description of the task can't be empty");
        }
    }
    
    public void handleEventError(String inputFromUser) throws DukeException {
        if (!(inputFromUser.substring(5).matches(".*\\S.*"))) {
            throw new DukeException("Description of the task can't be empty");
        }
    }
    
    public void handleDeleteError(TaskList list, String inputFromUser, int indexOfTaskToDelete) throws DukeException {
        if (list.size() < 1) {
            throw new DukeException("No task at the moment");
        }
        if (indexOfTaskToDelete > list.size() || indexOfTaskToDelete < 1) {
            throw new DukeException("Check you task number");
        }
    }
    
    public void handleInvalidInputDate(String inputDate, DateTimeFormatter formatter, LocalDate date) throws DukeException {
        if (!(inputDate.equals(date.format(formatter)))) {
            throw new DukeException("Did you enter a valid date or is the date entered of format <dd/mm/yyyy>");
        }
    }
    
}
